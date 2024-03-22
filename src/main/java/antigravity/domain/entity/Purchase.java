package antigravity.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Purchase {
    private int id;
    private int userId;
    private int productId;
    private String productColor;
    private String productSize;
    private int productCnt;
    private String  orderDate;
    private int productPrice;
    private int totalPrice;
    private int discountPrice;


}