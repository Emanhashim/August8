package com.bazra.usermanagement.response;

public class SigninErrorResponse {
    private String message;

    public SigninErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
