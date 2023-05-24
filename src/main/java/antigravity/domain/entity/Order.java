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
	private long id;

	//주문회원
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String transactionId;

	//주문시간
	@Column(name = "payment_date")
	private LocalDateTime paymentDate;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderProduct> orderProducts = new ArrayList<>();

	public static Order createOrder(User user, String transactionId, OrderProduct... orderProducts) {

		Order order = new Order();
		order.setUser(user);
		order.setTransactionId(transactionId);
		Arrays.stream(orderProducts).forEach(order::addOrderProduct);
		order.setPaymentDate(LocalDateTime.now());

		return order;
	}

	public void addOrderProduct(OrderProduct orderProduct) {
		orderProduct.setOrder(this);
		this.orderProducts.add(orderProduct);
	}

}
