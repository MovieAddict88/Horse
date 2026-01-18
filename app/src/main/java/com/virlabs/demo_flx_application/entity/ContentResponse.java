package com.virlabs.demo_flx_application.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentResponse {
    
    @SerializedName("Categories")
    @Expose
    private List<ContentCategory> categories;
    
    public List<ContentCategory> getCategories() {
        return categories;
    }
    
    public void setCategories(List<ContentCategory> categories) {
        this.categories = categories;
    }
}
