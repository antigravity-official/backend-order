package antigravity.service;

import antigravity.domain.entity.User;
import antigravity.model.request.OrderRequest;
import antigravity.model.response.OrderDetailResponse;
import antigravity.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public void orderCreate(OrderRequest request) {
        System.out.println("주문을 완성 시켜주세요.");
        System.out.println(request.toString());

        User user = orderRepository.getUser(request.getUserId());
    }

    public OrderDetailResponse getOrderDetail() {
        System.out.println("주문내역 api를 완성 시켜주세요.");
        return OrderDetailResponse.builder().userId(1).build();
    }
}
