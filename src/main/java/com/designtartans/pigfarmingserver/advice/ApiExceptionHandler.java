package com.designtartans.pigfarmingserver.advice;

import com.designtartans.pigfarmingserver.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.designtartans.pigfarmingserver.dto.BodyResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhoneNumberAlreadyExistException.class)
    public BodyResponse handlePhoneNumberAlreadyExistException(PhoneNumberAlreadyExistException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public BodyResponse handleUsernameNotFoundException(UsernameNotFoundException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(BadCredentialsException.class)
    public BodyResponse handleBadCredentialsException(BadCredentialsException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalArgumentException.class)
    public BodyResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidTokenException.class)
    public BodyResponse handleInvalidTokenException(InvalidTokenException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExistingVetShopNameException.class)
    public BodyResponse handleExistingVetShopNameException(ExistingVetShopNameException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidShopIdException.class)
    public BodyResponse handleInvalidShopIdException(InvalidShopIdException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public BodyResponse handleAccessDeniedException(AccessDeniedException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PigNotFoundException.class)
    public BodyResponse handlePigNotFoundException(PigNotFoundException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FarmNotFoundException.class)
    public BodyResponse handlePigNotFoundException(FarmNotFoundException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(SignatureException.class)
    public BodyResponse handleSignatureException(SignatureException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ExpiredJwtException.class)
    public BodyResponse handleExpiredTokenException(ExpiredJwtException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TagNotFoundException.class)
    public BodyResponse handleTagNotFoundException(TagNotFoundException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NoTokenProvidedException.class)
    public BodyResponse handleNoTokenProvidedException(NoTokenProvidedException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VetNotFoundException.class)
    public BodyResponse handleVetNotFoundException(VetNotFoundException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NoResourceFoundException.class)
    public BodyResponse handleResourceNotFoundException(NoResourceFoundException ex) {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.setProcessed(false);
        response.setResult(ex.getMessage());
        return response;
    }

}
