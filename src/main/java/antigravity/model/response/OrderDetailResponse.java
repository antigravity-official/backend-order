package antigravity.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDetailResponse {

    //상품정보(상품명, 상품컬러, 상품사이즈, 상품 금액), 주문갯수, 실제 결제 금액, 할인금액이 필요합니다.

    @JsonProperty(value = "user")
    private String name;
    @JsonProperty(value = "product")
    private ProductResponse productResponse;
    @JsonProperty(value = "priceInfo")
    private PriceInfoModel priceInfoModel;
}
