package dcode.service;

import dcode.domain.entity.User;
import dcode.model.request.OrderRequest;
import dcode.model.response.OrderDetailResponse;
import dcode.repository.OrderRepository;
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
