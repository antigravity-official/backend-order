package antigravity.domain.entity;

import antigravity.model.request.OrderRequest;
import antigravity.model.response.OrderDetailResponse;
import antigravity.model.response.PriceInfoModel;
import antigravity.model.response.ProductResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "order_product")
public class OrderProduct {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	private int orderPrice;
	private int orderCount;

	private int discountPoint;

	private int discountCouponAmount;

	private String color;
	private String size;

	public static OrderProduct createOrderProduct(Product product, Coupon coupon, OrderRequest request) {

		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setProduct(product);
		orderProduct.setOrderCount(Integer.valueOf(request.getProductInfo().getProductCount()));
		orderProduct.setDiscountPoint(Integer.valueOf(request.getPoint()));
		orderProduct.setDiscountCouponAmount(coupon.getDiscountAmount());
		orderProduct.setColor(request.getProductInfo().getColor());
		orderProduct.setSize(request.getProductInfo().getSize());
		orderProduct.calculateOrderPrice();

		Assert.isTrue(orderProduct.getOrderPrice() > 0, "주문금액은 0보다 작을수 없습니다");
		return orderProduct;

	}

	public void calculateOrderPrice() {
		this.orderPrice = (this.product.getPrice() * this.orderCount) - this.discountPoint - this.discountCouponAmount;
	}

	public OrderDetailResponse toOrderDetailResponse() {

		ProductResponse productResponse = new ProductResponse(this.product.getName(), this.color, this.size, this.orderCount);
		PriceInfoModel priceInfoModel = new PriceInfoModel(this.orderPrice, this.discountPoint, this.discountCouponAmount,
				this.discountPoint + this.discountCouponAmount);

		return new OrderDetailResponse(this.order.user.getName(), productResponse, priceInfoModel);

	}
}
