package antigravity.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "product")
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	//상품명
	@Column(name = "name")
	private String name;

	//상품가격
	@Column(name = "price")
	private int price;
}
