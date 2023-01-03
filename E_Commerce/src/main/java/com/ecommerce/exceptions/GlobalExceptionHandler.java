package com.ecommerce.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception e, WebRequest we) {

		ErrorDetails err = new ErrorDetails();

		err.setMsg(e.getMessage());
		err.setDetails(we.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AddressException.class)
	public ResponseEntity<ErrorDetails> addressExceptionHandler(AddressException ae, WebRequest we) {
		
		ErrorDetails err = new ErrorDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMsg(ae.getMessage());
		err.setDetails(we.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<ErrorDetails> adminExceptionHandler(AdminException ae, WebRequest we) {
		
		ErrorDetails err = new ErrorDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMsg(ae.getMessage());
		err.setDetails(we.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<ErrorDetails> cartExceptionHandler(CartException ce, WebRequest we) {
		
		ErrorDetails err = new ErrorDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMsg(ce.getMessage());
		err.setDetails(we.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<ErrorDetails> categoryExceptionHandler(CategoryException ce, WebRequest we) {
		
		ErrorDetails err = new ErrorDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMsg(ce.getMessage());
		err.setDetails(we.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorDetails> customerExceptionHandler(CustomerException ce, WebRequest we) {
		
		ErrorDetails err = new ErrorDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMsg(ce.getMessage());
		err.setDetails(we.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ErrorDetails> loginExceptionHandler(LoginException ce, WebRequest we) {
		
		ErrorDetails err = new ErrorDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMsg(ce.getMessage());
		err.setDetails(we.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrdersException.class)
	public ResponseEntity<ErrorDetails> ordersExceptionHandler(OrdersException oe, WebRequest we) {
		
		ErrorDetails err = new ErrorDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMsg(oe.getMessage());
		err.setDetails(we.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorDetails> productExceptionHandler(ProductException pe, WebRequest we) {
		
		ErrorDetails err = new ErrorDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMsg(pe.getMessage());
		err.setDetails(we.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> invalidArgumentExceptionHandler(MethodArgumentNotValidException ivae) {
		
		ErrorDetails err = new ErrorDetails();
		
		err.setMsg("Validation error");
		err.setTimestamp(LocalDateTime.now());
		err.setDetails(ivae.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> noHandlerFoundHandler(NoHandlerFoundException ne, WebRequest we) {
		
		ErrorDetails err = new ErrorDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMsg(ne.getMessage());
		err.setDetails(we.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
}
