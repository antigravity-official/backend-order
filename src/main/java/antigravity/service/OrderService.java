package antigravity.service;

import antigravity.common.exception.CommonApiException;
import antigravity.common.exception.ExceptionCode;
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

    public OrderDetailResponse orderCreate(OrderRequest request) {

        try {
            //정상결제 확인
            if (!paymentGatewayService.checkPaymentStatus(request.getTransactionId())) {
                throw new CommonApiException(ExceptionCode.BAD_REQUEST);
            }

            //재고 확인
            long quantity = stockService.decreaseAndGet(Long.valueOf(request.getProductInfo().getProductId()),
                    Integer.valueOf(request.getProductInfo().getProductCount()));
            log.error("quantity:{}", quantity);
            //user 조회
            User user = userRepository.findById(Long.valueOf(request.getUserId())).orElseThrow(() -> new CommonApiException(ExceptionCode.USER_INFO_NOTFOUND));
            user.decreasePoint(Integer.valueOf(request.getPoint()));

            //상품 조회
            Product product = productRepository.findById(Long.valueOf(request.getProductInfo().getProductId()))
                    .orElseThrow(() -> new CommonApiException(ExceptionCode.PRODUCT_INFO_NOTFOUND));

            //쿠폰 조회
            Coupon coupon = couponRepository.findById(Long.valueOf(request.getCouponId())).orElse(new Coupon());

            OrderProduct orderProduct = OrderProduct.createOrderProduct(product, coupon, request);

            Order order = Order.createOrder(user, request.getTransactionId(), orderProduct);

            orderRepository.save(order);

            return orderProduct.toOrderDetailResponse();

        } catch (Exception exception) {
            paymentGatewayService.cancelPayment(request.getTransactionId());
            paymentGatewayService.sendNotification();
            throw exception;
        }

    }

    public List<OrderDetailResponse> getOrderDetail(long orderId) {
        return orderRepository.findByOrderId(orderId);
    }
}
