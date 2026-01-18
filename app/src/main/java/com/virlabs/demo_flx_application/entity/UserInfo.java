package com.virlabs.demo_flx_application.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {
    
    @SerializedName("id")
    @Expose
    private int id;
    
    @SerializedName("username")
    @Expose
    private String username;
    
    @SerializedName("email")
    @Expose
    private String email;
    
    @SerializedName("is_admin")
    @Expose
    private int isAdmin;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getIsAdmin() {
        return isAdmin;
    }
    
    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }
}
