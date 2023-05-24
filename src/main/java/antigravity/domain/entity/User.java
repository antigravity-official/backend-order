package antigravity.domain.entity;

import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "point")
    private int point;

    public int decreasePoint(int point) {
       /* if(this.point - point < 0){
            throw new RuntimeException();
        }*/
        this.point -= point;
        Assert.isTrue(this.point >= 0, "차감할 포인트가 보유포인트보다 많습니다");

        return this.point;

    }
}
