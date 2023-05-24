package antigravity.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceInfoModel {

	public int orderPrice;
	@JsonProperty(value = "usedPoint")
	public int discountPoint;
	@JsonProperty(value = "usedCouponAmount")
	public int discountCouponAmount;
	public int totalDiscountAmount;

}
