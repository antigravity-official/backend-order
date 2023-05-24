package antigravity.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoRequest {

	@NotBlank(message = "상품아이디는 필수 값입니다")
	private String productId;
	@NotBlank(message = "주문수량은 필수 값입니다")
	private String productCount;
	@NotBlank(message = "컬러옵션은 필수 값입니다")
	private String color;
	@NotBlank(message = "상품사이즈는 필수 값입니다")
	private String size;
}
