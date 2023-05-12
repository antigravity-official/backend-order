package antigravity.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private int id;
    private String name;
    private int point;
}
