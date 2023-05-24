package antigravity.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	long id;

	//주문회원
	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;

	//주문시간
	@Column(name = "payment_date")
	LocalDateTime paymentDate;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	List<OrderProduct> orderProducts = new ArrayList<>();

	public static Order createOrder(User user, OrderProduct... orderProducts) {

		Order order = new Order();
		order.setUser(user);
		Arrays.stream(orderProducts).forEach(order::addOrderProduct);
		order.setPaymentDate(LocalDateTime.now());

		return order;
	}

	public void addOrderProduct(OrderProduct orderProduct) {
		orderProduct.setOrder(this);
		this.orderProducts.add(orderProduct);
	}

}
