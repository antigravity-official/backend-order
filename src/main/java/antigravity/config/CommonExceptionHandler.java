package antigravity.config;

import antigravity.common.exception.CommonApiException;
import antigravity.common.exception.ExceptionCode;
import antigravity.common.exception.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler(CommonApiException.class)
	public ResponseEntity<ExceptionResponse> handleCommonApiException(CommonApiException ex) {
		log.error("handleCommonApiException", ex);
		ResponseEntity responseEntity = ExceptionResponse.of(ex.getExceptionCode());
		return responseEntity;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
		log.error("handleException", ex);
		ResponseEntity responseEntity = ExceptionResponse.of(ExceptionCode.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ConstraintViolationException> handleConstraintViolationExceptions(ConstraintViolationException ex) {
		log.error("handleConstraintViolationExceptions", ex);
		ResponseEntity responseEntity = ExceptionResponse.of(ExceptionCode.BAD_REQUEST);
		return responseEntity;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		log.error("MethodArgumentNotValidException", ex);
		return ResponseEntity
				.status(ExceptionCode.BAD_REQUEST.getStatus())
				.body(ExceptionResponse.builder()
						.status(ExceptionCode.BAD_REQUEST.getStatus())
						.code(ExceptionCode.BAD_REQUEST.getCode())
						.message(errors.toString())
						.build()
				);

	}
}
