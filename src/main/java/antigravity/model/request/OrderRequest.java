package antigravity.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

	@Valid
	@NotNull(message = "상품정보는 필수값입니다")
	private ProductInfoRequest productInfo;
	@Pattern(regexp = "^[0-9]{1,20}$", message = "포인트는 숫자만 허용됩니다")
	private String point;
	private String couponId;
	@NotBlank(message = "UserId는 필수값입니다")
	@Pattern(regexp = "^[0-9]{1,20}$", message = "UserId는 숫자만 허용됩니다")
	private String userId;
	@NotBlank(message = "TransactionId는 필수값입니다")
	private String transactionId; //PG사로 부터 전달 받은 트렌젝션 아이디

}
