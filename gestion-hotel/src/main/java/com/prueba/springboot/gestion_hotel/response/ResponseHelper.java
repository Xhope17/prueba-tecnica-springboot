package com.prueba.springboot.gestion_hotel.response;

import java.time.LocalDateTime;
import java.util.List;

public class ResponseHelper {

    public static <T> GenericResponse<T> create(T data, List<String> errors, int count, String message) {
        GenericResponse<T> response = new GenericResponse<>();
        response.setData(data);
        response.setMessage(message != null ? message : "Solicitud realizada correctamente");
        response.setErrors(errors != null ? errors : List.of());
        response.setCount(count);
        response.setTimestamp(LocalDateTime.now());
        return response;
    }

    public static <T> GenericResponse<T> success(T data) {
        return create(data, null, 0, null);
    }

    public static <T> GenericResponse<T> success(T data, String message) {
        return create(data, null, 0, message);
    }

    public static <T> GenericResponse<T> success(T data, String message, int count) {
        return create(data, null, count, message);
    }

    public static <T> GenericResponse<T> error(List<String> errors) {
        return create(null, errors, 0, "Error al procesar la solicitud");
    }

    public static <T> GenericResponse<T> error(List<String> errors, String message) {
        return create(null, errors, 0, message);
    }
}
