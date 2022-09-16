package com.sprint1.assetmanagementsystem.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {
@ExceptionHandler(NoAccessException.class)
public @ResponseBody ErrorInfo noAccessExceptionHandler(NoAccessException n,HttpServletRequest req) {
	
	ErrorInfo er=new ErrorInfo(LocalDateTime.now(),n.getMessage(),req.getRequestURI());
	return er;
	
}
@ExceptionHandler(LoginException.class)
public @ResponseBody ErrorInfo loginExceptionHandler(LoginException n,HttpServletRequest req) {
	
	ErrorInfo er=new ErrorInfo(LocalDateTime.now(),n.getMessage(),req.getRequestURI());
	return er;
}
@ExceptionHandler(NotFoundException.class)
public @ResponseBody ErrorInfo userNotFoundExceptionHandler(NotFoundException n,HttpServletRequest req) {
	ErrorInfo er=new ErrorInfo(LocalDateTime.now(),n.getMessage(),req.getRequestURI());
	return er;
}
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Object> checkValidation(MethodArgumentNotValidException e,HttpServletRequest req) {
	 ErrorInfo erinfo = new ErrorInfo(LocalDateTime.now(),e.getBindingResult().toString(),req.getRequestURI());
	 System.out.println(e.getBindingResult().toString());
	 return new ResponseEntity<Object>(erinfo,HttpStatus.BAD_REQUEST);
	 

}
}