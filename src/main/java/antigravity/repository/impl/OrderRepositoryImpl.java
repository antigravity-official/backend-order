package antigravity.repository.impl;

import antigravity.domain.entity.Order;
import antigravity.model.response.OrderDetailResponse;
import antigravity.model.response.PriceInfoModel;
import antigravity.model.response.ProductResponse;
import antigravity.repository.CustomOrderRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static antigravity.domain.entity.QOrder.order;
import static antigravity.domain.entity.QOrderProduct.orderProduct;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements CustomOrderRepository {

	public OrderRepositoryImpl() {
		super(Order.class);
	}

	@PersistenceContext
	@Override
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override public List<OrderDetailResponse> findByOrderId(Long orderId) {
		JPQLQuery query = from(orderProduct)
				.select(
						Projections.fields(
								OrderDetailResponse.class,
								order.user.name.as("name"),
								Projections.fields(
										ProductResponse.class,
										orderProduct.product.name,
										orderProduct.color,
										orderProduct.size,
										orderProduct.orderCount
								).as("productResponse"),
								Projections.fields(
										PriceInfoModel.class,
										orderProduct.orderPrice,
										orderProduct.discountPoint,
										orderProduct.discountCouponAmount,
										orderProduct.discountPoint.add(orderProduct.discountCouponAmount).as("totalDiscountAmount")
								).as("priceInfoModel")
						)

				)
				.innerJoin(order)
				.on(orderProduct.order.id.eq(order.id))
				.where(orderProduct.id.eq(orderId));

		return query.fetch();
	}
}
