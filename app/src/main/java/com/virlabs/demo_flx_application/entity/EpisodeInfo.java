package com.virlabs.demo_flx_application.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EpisodeInfo implements Parcelable {
    
    @SerializedName("Episode")
    @Expose
    private int episode;
    
    @SerializedName("Title")
    @Expose
    private String title;
    
    @SerializedName("Duration")
    @Expose
    private String duration;
    
    @SerializedName("Description")
    @Expose
    private String description;
    
    @SerializedName("Thumbnail")
    @Expose
    private String thumbnail;
    
    @SerializedName("Servers")
    @Expose
    private List<ServerInfo> servers;
    
    public EpisodeInfo() {
        servers = new ArrayList<>();
    }
    
    protected EpisodeInfo(Parcel in) {
        episode = in.readInt();
        title = in.readString();
        duration = in.readString();
        description = in.readString();
        thumbnail = in.readString();
        servers = in.createTypedArrayList(ServerInfo.CREATOR);
    }
    
    public static final Creator<EpisodeInfo> CREATOR = new Creator<EpisodeInfo>() {
        @Override
        public EpisodeInfo createFromParcel(Parcel in) {
            return new EpisodeInfo(in);
        }
        
        @Override
        public EpisodeInfo[] newArray(int size) {
            return new EpisodeInfo[size];
        }
    };
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(episode);
        dest.writeString(title);
        dest.writeString(duration);
        dest.writeString(description);
        dest.writeString(thumbnail);
        dest.writeTypedList(servers);
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    public int getEpisode() {
        return episode;
    }
    
    public void setEpisode(int episode) {
        this.episode = episode;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getThumbnail() {
        return thumbnail;
    }
    
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    public List<ServerInfo> getServers() {
        return servers;
    }
    
    public void setServers(List<ServerInfo> servers) {
        this.servers = servers;
    }
}
