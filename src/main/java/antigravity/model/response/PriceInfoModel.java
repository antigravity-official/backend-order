package antigravity.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceInfoModel {

	public int orderPrice;
	@JsonProperty(value = "usedPoint")
	public int discountPoint;
	@JsonProperty(value = "usedCouponAmount")
	public int discountCouponAmount;
	public int totalDiscountAmount;

}
