package ru.arkhipov.lab6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Единый формат ответа REST-сервиса: успешно операция завершилась или нет.
 */
@Data
@AllArgsConstructor
public class ApiResponse<T> {

    // true - операция выполнена, false - ошибка
    private boolean success;

    // Пояснение к результату
    private String message;

    // Данные (студент, список студентов и т.д.), при ошибке null
    private T data;

    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
}
