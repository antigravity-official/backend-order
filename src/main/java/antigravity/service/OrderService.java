package antigravity.service;

import antigravity.domain.entity.*;
import antigravity.model.request.OrderRequest;
import antigravity.model.response.OrderDetailResponse;
import antigravity.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.UriEncoder;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public String orderCreate(OrderRequest request) {
        System.out.println("주문을 완성 시켜주세요.");
        System.out.println(request.toString());
        // 상품 정보 조회
        Product product = orderRepository.getProduct(request.getProductInfo().getProductId());
        // 유저 정보 조회
        User user = orderRepository.getUser(request.getUserId());
        //쿠폰 정보 조회
        Coupon coupon = orderRepository.getCoupon(request.getCouponId());
        //재고 확인
        Inventory inventory = orderRepository.getInventory(request.getProductInfo().getProductId(),request.getProductInfo().getColor(),request.getProductInfo().getSize());


        if(inventory.getCnt()<=0 || inventory.getCnt()<request.getProductInfo().getProductCount()){
            return "재고가 없습니다";
        }

        //[사용한 포인트] <= [보유포인트] 확인
        if(request.getPoint()>user.getPoint()){
            return "보유포인트가 없습니다";
        }

        // 재고 업데이트
        orderRepository.updateInventory(inventory.getId(), inventory.getCnt()-request.getProductInfo().getProductCount());
        //최소 사용가능 포인트
        if(request.getPoint()<1000){
            return "최소 사용가능 포인트는 1,000P 입니다.";
        }

        if(request.getCouponId()<=0){
            return "사용가능한 쿠폰이 없습니다.";
        }

        // 주문 금액 계산
        int totalPrice = calculatePayment(product.getPrice(), request.getProductInfo().getProductCount(), request.getPoint(), coupon.getDiscountAmount());
        //최소 결제금액
        if(totalPrice<=1000){
            return "최소 결제금액";
        }

        // 정상결제 되었는지 확인
        boolean paymentSuccess = callPG(totalPrice);


        //모든 과정 실패 시 차감된 주문수량 복구
        if (!paymentSuccess) {
            orderRepository.updateInventory(inventory.getId(),inventory.getCnt());
            return "실패";
        }else{
            // 주문 정보 저장
            OrderRequest order = request;
            order.setProductInfo(request.getProductInfo());
            order.setCouponId(request.getCouponId());
            order.setTId(request.getTId());
            order.setPoint(request.getPoint());

            int result = orderRepository.insertPurchase(request.getUserId(),request.getProductInfo().getProductId(),request.getProductInfo().getColor(),
                    request.getProductInfo().getSize(),request.getProductInfo().getProductCount(),
                    String.valueOf(LocalDate.now()),product.getPrice(),totalPrice,coupon.getDiscountAmount());

            if (result>0){
                return "주문정보저장 성공";
            }else{
                return "주문정보저장 실패";
            }
        }

    }
    private int calculatePayment(int productPrice, int productCount, int usedPoints, int couponAmount) {
        // 주문 금액 계산
        return (productPrice * productCount) - usedPoints -couponAmount;
    }

    public boolean callPG(int amount) {
        // PG사 API를 호출하여 결제 진행
        return true;
    }

    public OrderDetailResponse getOrderDetail(String orderId) {

        Purchase purchase = orderRepository.getOrderDetail(Integer.parseInt(orderId));
        Product product = orderRepository.getProduct(purchase.getProductId());

        return OrderDetailResponse.builder()
                .userId(purchase.getUserId())
                .productColor(purchase.getProductColor())
                .productName(UriEncoder.encode(product.getName()))
                .productSize(purchase.getProductSize())
                .count(purchase.getProductCnt())
                .productPrice(product.getPrice())
                .discount(purchase.getDiscountPrice())
                .build();
    }
}
