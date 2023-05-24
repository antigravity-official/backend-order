package antigravity.repository;

import antigravity.model.response.OrderDetailResponse;

import java.util.List;

public interface CustomOrderRepository {

	List<OrderDetailResponse> findByOrderId(Long orderId);
}
