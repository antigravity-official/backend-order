package dcode.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coupon {

    private int id;
    private String name;
    private int discountAmount;
}
