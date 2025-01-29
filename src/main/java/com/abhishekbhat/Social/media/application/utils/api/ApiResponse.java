package com.abhishekbhat.Social.media.application.utils.api;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse<T> {
    // Getters and Setters
    private String status;
    private String message;
    private T data;
    private Object metadata;

    public ApiResponse(String status, String message, T data, Object metadata) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.metadata = metadata;
    }

}
