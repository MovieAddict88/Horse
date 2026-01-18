package com.virlabs.demo_flx_application.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    
    @SerializedName("success")
    @Expose
    private boolean success;
    
    @SerializedName("message")
    @Expose
    private String message;
    
    @SerializedName("error")
    @Expose
    private String error;
    
    @SerializedName("user")
    @Expose
    private UserInfo user;
    
    @SerializedName("logged_in")
    @Expose
    private boolean loggedIn;
    
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
    
    public String getError() {
        return error;
    }
    
    public void setError(String error) {
        this.error = error;
    }
    
    public UserInfo getUser() {
        return user;
    }
    
    public void setUser(UserInfo user) {
        this.user = user;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
