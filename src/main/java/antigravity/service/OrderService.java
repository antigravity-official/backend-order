package antigravity.service;

import antigravity.domain.entity.*;
import antigravity.model.request.OrderRequest;
import antigravity.model.response.OrderDetailResponse;
import antigravity.repository.CouponRepository;
import antigravity.repository.OrderRepository;
import antigravity.repository.ProductRepository;
import antigravity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;
    private final PaymentGatewayService paymentGatewayService;
    private final StockService stockService;

    public void orderCreate(OrderRequest request) {

        try {
            //정상결제 확인
            if (!paymentGatewayService.checkPaymentStatus(request.getTId())) {
                throw new RuntimeException();
            }

            //재고 확인
            stockService.decreaseAndGet(request.getProductInfo().getProductId(), request.getProductInfo().getProductCount());

            //user 조회
            User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException());
            user.decreasePoint(request.getPoint());

            //상품 조회
            Product product = productRepository.findById(request.getProductInfo().getProductId()).orElseThrow(() -> new RuntimeException());

            //쿠폰 조회
            Coupon coupon = couponRepository.findById(request.getCouponId()).orElse(new Coupon());

            OrderProduct orderProduct = OrderProduct.createOrderProduct(product, coupon, request);

            Order order = Order.createOrder(user, orderProduct);

            orderRepository.save(order);

        } catch (Exception exception) {
            paymentGatewayService.cancelPayment(request.getTId());
            throw exception;
        }

    }

    public List<OrderDetailResponse> getOrderDetail(long orderId) {
        return orderRepository.findByOrderId(orderId);
    }
}
