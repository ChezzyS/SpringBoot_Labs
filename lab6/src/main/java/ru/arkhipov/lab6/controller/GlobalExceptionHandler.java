package ru.arkhipov.lab6.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.arkhipov.lab6.dto.ApiResponse;

/**
 * Любая необработанная ошибка превращается в ответ success = false.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadBody(HttpMessageNotReadableException e) {
        log.error("Некорректное тело запроса", e);
        return ResponseEntity.badRequest().body(ApiResponse.error("Некорректное тело запроса (ожидается JSON)"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadParam(MethodArgumentTypeMismatchException e) {
        log.error("Некорректный параметр запроса {}", e.getName(), e);
        return ResponseEntity.badRequest().body(ApiResponse.error("Некорректное значение параметра: " + e.getName()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleOther(Exception e) {
        // Ошибки самого Spring (404 на неизвестный адрес, 405 и т.п.) сохраняют свой код
        if (e instanceof ErrorResponse errorResponse) {
            return ResponseEntity.status(errorResponse.getStatusCode())
                    .body(ApiResponse.error(errorResponse.getBody().getDetail()));
        }
        log.error("Внутренняя ошибка сервера", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Внутренняя ошибка сервера"));
    }
}
