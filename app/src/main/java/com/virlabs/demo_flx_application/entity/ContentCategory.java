package com.virlabs.demo_flx_application.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentCategory {
    
    @SerializedName("MainCategory")
    @Expose
    private String mainCategory;
    
    @SerializedName("SubCategories")
    @Expose
    private List<String> subCategories;
    
    @SerializedName("Entries")
    @Expose
    private List<ContentEntry> entries;
    
    public String getMainCategory() {
        return mainCategory;
    }
    
    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }
    
    public List<String> getSubCategories() {
        return subCategories;
    }
    
    public void setSubCategories(List<String> subCategories) {
        this.subCategories = subCategories;
    }
    
    public List<ContentEntry> getEntries() {
        return entries;
    }
    
    public void setEntries(List<ContentEntry> entries) {
        this.entries = entries;
    }
}
