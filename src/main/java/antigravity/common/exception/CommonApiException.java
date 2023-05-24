package antigravity.common.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonApiException extends RuntimeException {

	private ExceptionCode exceptionCode;

	public CommonApiException(ExceptionCode exceptionCode) {
		super(exceptionCode.getMessage());
		this.exceptionCode = exceptionCode;
	}

	public CommonApiException(Throwable cause) {
		super(cause);
	}
}
