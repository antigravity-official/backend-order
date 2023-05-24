package antigravity.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductInfoRequest {

	private long productId;
	private int productCount;
	private String color;
	private String size;
}
