package com.delivery.app.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.delivery.app.dto.ExceptionResponseDTO;
import com.delivery.app.exception.NotFoundException;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<Object> trataNotFoundException(NotFoundException ex, WebRequest request){
		
		ExceptionResponseDTO dto = new ExceptionResponseDTO();
		dto.setStatus(HttpStatus.NOT_FOUND.value());
		dto.setMensagem(ex.getLocalizedMessage());
		
		return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> trataArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request){
		
		ExceptionResponseDTO dto = new ExceptionResponseDTO();
		dto.setStatus(HttpStatus.BAD_REQUEST.value());
		dto.setMensagem("Argumento do tipo errado");
		
		return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
	}
}
