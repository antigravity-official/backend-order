package antigravity.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ExceptionResponse {

	private final LocalDateTime timestamp = LocalDateTime.now();
	private final int status;
	private final String code;
	private final String message;

	public static ResponseEntity<ExceptionResponse> of(ExceptionCode exceptionCode) {
		return ResponseEntity
				.status(exceptionCode.getStatus())
				.body(ExceptionResponse.builder()
						.status(exceptionCode.getStatus())
						.code(exceptionCode.getCode())
						.message(exceptionCode.getMessage())
						.build()
				);
	}
}
