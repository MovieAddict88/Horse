package com.virlabs.demo_flx_application.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerInfo implements Parcelable {
    
    @SerializedName("name")
    @Expose
    private String name;
    
    @SerializedName("url")
    @Expose
    private String url;
    
    @SerializedName("drm")
    @Expose
    private boolean drm;
    
    @SerializedName("license")
    @Expose
    private String license;
    
    public ServerInfo() {
    }
    
    protected ServerInfo(Parcel in) {
        name = in.readString();
        url = in.readString();
        drm = in.readByte() != 0;
        license = in.readString();
    }
    
    public static final Creator<ServerInfo> CREATOR = new Creator<ServerInfo>() {
        @Override
        public ServerInfo createFromParcel(Parcel in) {
            return new ServerInfo(in);
        }
        
        @Override
        public ServerInfo[] newArray(int size) {
            return new ServerInfo[size];
        }
    };
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
        dest.writeByte((byte) (drm ? 1 : 0));
        dest.writeString(license);
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public boolean isDrm() {
        return drm;
    }
    
    public void setDrm(boolean drm) {
        this.drm = drm;
    }
    
    public String getLicense() {
        return license;
    }
    
    public void setLicense(String license) {
        this.license = license;
    }
}
