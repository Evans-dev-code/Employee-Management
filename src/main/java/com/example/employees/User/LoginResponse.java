package com.example.employees.User;

public class LoginResponse {

    private String token;
    private int statusCode;
    private String message;
    private UserEntity user;  // This will hold user details

    // Constructor for token response
    public LoginResponse(String token) {
        this.token = token;
        this.statusCode = 200;
        this.message = "Login successful";
    }

    // Constructor for error or status response
    public LoginResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    // Constructor for user data response (without password)
    public LoginResponse(UserEntity user) {
        // Remove sensitive information from the user entity (e.g., password)
        user.setPassword(null);  // Ensure password is not included in the response
        this.user = user;
        this.statusCode = 200;
        this.message = "User details fetched successfully";
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    // Optional helper methods
    public static LoginResponse ok(String token) {
        return new LoginResponse(token);
    }

    public static LoginResponse error(int statusCode, String message) {
        return new LoginResponse(statusCode, message);
    }
}
