package antigravity.domain.entity;

import antigravity.common.exception.CommonApiException;
import antigravity.common.exception.ExceptionCode;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "stock")
public class Stock {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(optional = false)
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "quantity")
	private Long quantity;

	public Long decreaseQuantity(int count) {
		if (this.quantity - count < 0) {
			throw new CommonApiException(ExceptionCode.STOCK_IS_NOT_ENOUGH);
		}

		return this.quantity -= count;
	}

}
