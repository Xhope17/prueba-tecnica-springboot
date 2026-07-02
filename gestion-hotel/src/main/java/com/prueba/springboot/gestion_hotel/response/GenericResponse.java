package com.prueba.springboot.gestion_hotel.response;

import java.time.LocalDateTime;
import java.util.List;

public class GenericResponse<T> {

    private String message;
    private List<String> errors;
    private LocalDateTime timestamp;
    private int count;
    private T data;

    public GenericResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}
