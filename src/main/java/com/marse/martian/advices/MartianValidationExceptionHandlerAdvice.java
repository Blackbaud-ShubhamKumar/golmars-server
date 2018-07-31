package com.marse.martian.advices;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.marse.martian.controllers.base.MartianController;

@ControllerAdvice
public class MartianValidationExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
	
	private static final Logger ERROR_LOGGER = LogManager.getLogger(MartianValidationExceptionHandlerAdvice.class);


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ERROR_LOGGER.error("Server throw an validation error occoured - ",ex);
		ex.printStackTrace();
		BindingResult bindingResult = ex.getBindingResult();
		List<MartianFieldError> apiFieldErrors = bindingResult.getFieldErrors().stream()
				.map(fieldError -> new MartianFieldError(fieldError.getField(), fieldError.getCode(),
						fieldError.getRejectedValue()))
				.collect(Collectors.toList());

		List<MartianGlobalError> apiGlobalErrors = bindingResult.getGlobalErrors().stream()
				.map(globalError -> new MartianGlobalError(globalError.getCode())).collect(Collectors.toList());

		MartianErrorsView apiErrorsView = new MartianErrorsView(apiFieldErrors, apiGlobalErrors);

		return new ResponseEntity<>(apiErrorsView, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		 ERROR_LOGGER.error("Server throw common error occoured - ",ex);
  		 ex.printStackTrace();
		 MartianGlobalError apiGlobalErrors =new MartianGlobalError(ex.getMessage());
		 MartianErrorsView apiErrorsView = new MartianErrorsView(null, Arrays.asList(apiGlobalErrors));
	  return new ResponseEntity<>(apiErrorsView, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public final ResponseEntity<Object> handleAllExceptions(InvalidDataAccessResourceUsageException ex, WebRequest request) {
		 ERROR_LOGGER.error("Server throw invalid data error occoured - ",ex);
  		 ex.printStackTrace();
		 MartianGlobalError apiGlobalErrors =new MartianGlobalError("Internal Server Error.");
		 MartianErrorsView apiErrorsView = new MartianErrorsView(null, Arrays.asList(apiGlobalErrors));
	  return new ResponseEntity<>(apiErrorsView, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
