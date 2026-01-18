package com.virlabs.demo_flx_application.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrailerInfo implements Parcelable {
    
    @SerializedName("url")
    @Expose
    private String url;
    
    @SerializedName("type")
    @Expose
    private String type;
    
    public TrailerInfo() {
    }
    
    protected TrailerInfo(Parcel in) {
        url = in.readString();
        type = in.readString();
    }
    
    public static final Creator<TrailerInfo> CREATOR = new Creator<TrailerInfo>() {
        @Override
        public TrailerInfo createFromParcel(Parcel in) {
            return new TrailerInfo(in);
        }
        
        @Override
        public TrailerInfo[] newArray(int size) {
            return new TrailerInfo[size];
        }
    };
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(type);
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
}
