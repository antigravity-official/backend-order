package antigravity.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

	USER_INFO_NOTFOUND(404, "USER_INFO_NOTFOUND", "회원 정보를 찾을 수 없습니다"),
	PRODUCT_INFO_NOTFOUND(404, "PRODUCT_INFO_NOTFOUND", "상품 정보를 찾을 수 없습니다"),
	STOCK_INFO_NOTFOUND(404, "STOCK_INFO_NOTFOUND", "재고정보를 찾을 수 없습니다"),
	STOCK_IS_NOT_ENOUGH(500, "STOCK_IS_NOT_ENOUGH", "재고가 부족합니다"),
	INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "INTERNAL SERVER ERROR"),
	USER_POINT_NOT_ENOUGH(400, "USER_POINT_NOT_ENOUGH", "차감할 포인트가 보유 포인트보다 많습니다"),
	BAD_REQUEST(400, "BAD_REQUEST", "%s");

	private int status;
	private String code;
	private String message;

}