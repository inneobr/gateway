package org.inneo.gateway.exceptions;

import java.util.List;


import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ApplicationExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public DefaultExceptions constraintViolationException(ConstraintViolationException ex) {
        return DefaultExceptions.construir(
        		HttpStatus.BAD_REQUEST.value(),
                "Não foi possível executar a operação!",
                ex.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DefaultExceptions methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ObjectFieldErrorsException.class)
    public DefaultExceptions classificacaoDetalheException(ObjectFieldErrorsException ex) {
        return DefaultExceptions.construir(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public DefaultExceptions Exception(Exception ex) {	
    	return DefaultExceptions.construir(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getMessage());    
    } 
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ConversionFailedException.class)
    public DefaultExceptions Exception(ConversionFailedException ex) {	
    	return DefaultExceptions.construir(HttpStatus.BAD_REQUEST.value(), "Não foi possivel converter um objetoEnum ou parametro de requisição.", ex.getMessage());    
    }
    

    private DefaultExceptions processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors, String messageEx) {
    	DefaultExceptions exception = DefaultExceptions.construir(HttpStatus.BAD_REQUEST.value(), "Validation error", messageEx);
    	
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
        	exception.getFieldErrors().add(addFieldError(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return exception;
    }

    public FieldErrorItem addFieldError(String field, String message) {
    	FieldErrorItem error = new FieldErrorItem();
    	error.setField(field);
    	error.setMessage(message);
    	return error;
    }
}
