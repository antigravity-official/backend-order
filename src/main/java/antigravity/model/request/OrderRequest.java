package antigravity.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private ProductInfoRequest productInfo;
    private int point;
    private long couponId;
    private long userId;
    private String tId; //PG사로 부터 전달 받은 트렌젝션 아이디

}
