package xyz.mhmm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	@RequestMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> handle(NoHandlerFoundException e) {
		return new ResponseEntity<>(ErrorResponse.of(ErrorCode.NOT_FOUND_EXCEPTION), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		return new ResponseEntity<>(ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
		return new ResponseEntity<>(ErrorResponse.of(ErrorCode.UNSUPPORTED_MEDIA_TYPE), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleUserNotExistException(BusinessException e) {
		return new ResponseEntity<>(ErrorResponse.of(e), HttpStatus.valueOf(e.getErrorCode().getStatus()));
	}
}
