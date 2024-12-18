package com.cloudthat.bankingapp.model;

public class ApiResponse {
    private boolean success;
    private String message;
    private Object data;

    // Constructor with success, message, and data
    public ApiResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Constructor with success and message (for cases without data)
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.data = null; // or you can omit this line if you don't want to set data explicitly
    }

    public ApiResponse() {
        // Default constructor if needed
    }

    // Getter and Setter methods
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
