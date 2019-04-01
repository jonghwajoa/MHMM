package xyz.mhmm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import xyz.mhmm.utils.ErrorCode;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<?> handle(NoHandlerFoundException e) {
		return new ResponseEntity<>(ErrorResponse.of(ErrorCode.NOT_FOUND_EXCEPTION), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		return new ResponseEntity<>(ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
		return new ResponseEntity<>(ErrorResponse.of(ErrorCode.UNSUPPORTED_MEDIA_TYPE), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleUserNotExistException(BusinessException e) {
		return new ResponseEntity<>(ErrorResponse.of(e), HttpStatus.valueOf(e.getErrorCode().getStatus()));
	}
}
