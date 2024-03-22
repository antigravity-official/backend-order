package antigravity.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Inventory {
    private int id;
    private int productId;
    private String productColor;
    private String productSize;
    private int cnt;
}