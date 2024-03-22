package antigravity.repository;

import antigravity.domain.entity.*;
import antigravity.model.response.OrderDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public User getUser(int id) {
        String query = "SELECT * FROM `user` WHERE id = :id ";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(
                query,
                params,
                (rs, rowNum) -> User.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .point(rs.getInt("point"))
                        .build()
        );
    }

    public Product getProduct(int id) {
        String query = "SELECT * FROM `product` WHERE id = :id ";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(
                query,
                params,
                (rs, rowNum) -> Product.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .price(rs.getInt("price"))
                        .build()
        );
    }

    public int insertPurchase(int userId,int productId, String productColor,String productSize,int productCnt,String orderDate,int productPrice,int totalPrice,int discountPrice) {
        String query = "INSERT INTO `purchase` (user_id, product_id, product_color, product_size, product_cnt, order_date, product_price, total_price, discount_price) " +
                "VALUES(:userId, :productId, :productColor, :productSize, :productCnt, :orderDate, :productPrice, :totalPrice, :discountPrice)";

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("userId", userId);
        params.addValue("productId", productId);
        params.addValue("productColor", productColor);
        params.addValue("productSize", productSize);
        params.addValue("productCnt", productCnt);
        params.addValue("orderDate", orderDate);
        params.addValue("productPrice", productPrice);
        params.addValue("totalPrice", totalPrice);
        params.addValue("discountPrice", discountPrice);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        return namedParameterJdbcTemplate.update(query, params, keyHolder);
    }

    public Coupon getCoupon(int id) {
        String query = "SELECT * FROM `coupon` WHERE id = :id ";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(
                query,
                params,
                (rs, rowNum) -> Coupon.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .discountAmount(rs.getInt("discount_amount"))
                        .build()
        );
    }

    public Inventory getInventory(int productId, String productColor, String productSize) {
        String query = "SELECT * FROM inventory WHERE product_id = :productId AND product_color = :productColor AND product_size = :productSize ";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productId", productId);
        params.addValue("productColor", productColor);
        params.addValue("productSize", productSize);

        return namedParameterJdbcTemplate.queryForObject(
                query,
                params,
                (rs, rowNum) -> Inventory.builder()
                        .id(rs.getInt("id"))
                        .productId(rs.getInt("product_id"))
                        .productColor(rs.getString("product_color"))
                        .productSize(rs.getString("product_size"))
                        .cnt(rs.getInt("cnt"))
                        .build()
        );
    }

    public void updateInventory(int id, int cnt) {
        String query = "UPDATE inventory SET cnt = :cnt WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("cnt", cnt);
        params.addValue("id", id);

        namedParameterJdbcTemplate.update(query, params);
    }

    public Purchase getOrderDetail(int id) {
        String query = "SELECT * FROM `purchase` WHERE id = :id ";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(
                query,
                params,
                (rs, rowNum) -> Purchase.builder()
                        .id(rs.getInt("id"))
                        .userId(rs.getInt("user_id"))
                        .productId(rs.getInt("product_id"))
                        .productColor(rs.getString("product_color"))
                        .productSize(rs.getString("product_size"))
                        .productCnt(rs.getInt("product_cnt"))
                        .orderDate(rs.getString("order_date"))
                        .productPrice(rs.getInt("product_price"))
                        .totalPrice(rs.getInt("total_price"))
                        .discountPrice(rs.getInt("discount_price"))
                        .build()
        );
    }
}
