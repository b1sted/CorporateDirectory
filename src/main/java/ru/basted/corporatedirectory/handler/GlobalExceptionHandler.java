package ru.basted.corporatedirectory.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import ru.basted.corporatedirectory.dto.ErrorResponseDto;
import ru.basted.corporatedirectory.exception.EmailAlreadyExistsException;
import ru.basted.corporatedirectory.exception.IdenticalRoleException;
import ru.basted.corporatedirectory.exception.UserNotFoundException;
import ru.basted.corporatedirectory.exception.UsernameAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return buildBadRequestResponse("Ошибка валидации параметров запроса", errors);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodValidation(HandlerMethodValidationException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getParameterValidationResults().forEach(paramResult ->
                paramResult.getResolvableErrors().forEach(error -> {
                    String key;

                    if (error instanceof FieldError fieldError) {
                        key = fieldError.getField();
                    } else {
                        key = paramResult.getMethodParameter().getParameterName();
                    }

                    errors.put(key, error.getDefaultMessage());
                })
        );

        return buildBadRequestResponse("Ошибка валидации параметров запроса", errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleJsonParseError(IllegalArgumentException ex) {
        String message = ex.getMessage();
        if (message != null && message.contains("ru.basted.corporatedirectory.model.Role")) {
            message = "Передана неизвестная роль пользователя: " + message.substring(message.lastIndexOf('.') + 1);
        }

        return buildBadRequestResponse(message);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleAccessDenied(HttpServletRequest request) {
        return buildNotFoundResponse(request);
    }

    @ExceptionHandler({BadCredentialsException.class, InsufficientAuthenticationException.class})
    public ResponseEntity<ErrorResponseDto> handleAuthenticationException(HttpServletRequest request) {
        return buildNotFoundResponse(request);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNoResourceFound(HttpServletRequest request) {
        return buildNotFoundResponse(request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex) {
        return buildNotFoundResponse(ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        return buildConflictResponse(ex.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex) {
        return buildConflictResponse(ex.getMessage());
    }

    @ExceptionHandler(IdenticalRoleException.class)
    public ResponseEntity<ErrorResponseDto> handleIdenticalRole(IdenticalRoleException ex) {
        return buildConflictResponse(ex.getMessage());
    }

    private ResponseEntity<ErrorResponseDto> buildBadRequestResponse(String message) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now().withNano(0))
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(message)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private ResponseEntity<ErrorResponseDto> buildBadRequestResponse(String message, Map<String, String> errors) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now().withNano(0))
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(message)
                .fieldErrors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private ResponseEntity<ErrorResponseDto> buildNotFoundResponse(String message) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now().withNano(0))
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(message)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    private ResponseEntity<ErrorResponseDto> buildNotFoundResponse(HttpServletRequest request) {
        String path = request.getRequestURI();
        if (path.equals("/error")) {
            path = (String) request.getAttribute("jakarta.servlet.forward.request_uri");
        }

        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now().withNano(0))
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .path(path)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    private ResponseEntity<ErrorResponseDto> buildConflictResponse(String message) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now().withNano(0))
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(message)
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
