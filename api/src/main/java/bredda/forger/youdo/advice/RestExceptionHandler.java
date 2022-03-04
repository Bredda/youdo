package bredda.forger.youdo.advice;

import bredda.forger.youdo.exception.UserAlreadyExistsException;
import bredda.forger.youdo.exception.UnauthorizedException;
import bredda.forger.youdo.payload.response.ErrorResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex){
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException ex){
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, "Request with bad arguments", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    private ResponseEntity<ErrorResponse> handleUnauthorizedResourceException(UnauthorizedException ex){
        ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Unauthorized", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, "Request with bad arguments", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
