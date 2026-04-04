package com.securecommerce.app.dto;

public class ErrorResponse {

    private boolean success;
    private String message;
    private String errorCode;

    public ErrorResponse(boolean success, String message, String errorCode) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }
}