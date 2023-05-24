package antigravity.domain.entity;

import antigravity.common.exception.CommonApiException;
import antigravity.common.exception.ExceptionCode;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "user")
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "point")
	private int point;

	public int decreasePoint(int point) {
		if (this.point - point < 0) {
			throw new CommonApiException(ExceptionCode.USER_POINT_NOT_ENOUGH);
		}

		return this.point -= point;

	}
}
