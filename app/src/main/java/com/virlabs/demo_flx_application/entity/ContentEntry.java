package com.virlabs.demo_flx_application.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ContentEntry implements Parcelable {
    
    @SerializedName("Title")
    @Expose
    private String title;
    
    @SerializedName("Description")
    @Expose
    private String description;
    
    @SerializedName("Poster")
    @Expose
    private String poster;
    
    @SerializedName("Thumbnail")
    @Expose
    private String thumbnail;
    
    @SerializedName("Rating")
    @Expose
    private String rating;
    
    @SerializedName("Duration")
    @Expose
    private String duration;
    
    @SerializedName("Year")
    @Expose
    private String year;
    
    @SerializedName("parentalRating")
    @Expose
    private String parentalRating;
    
    @SerializedName("Trailer")
    @Expose
    private TrailerInfo trailer;
    
    @SerializedName("Servers")
    @Expose
    private List<ServerInfo> servers;
    
    @SerializedName("Seasons")
    @Expose
    private List<SeasonInfo> seasons;
    
    public ContentEntry() {
        servers = new ArrayList<>();
        seasons = new ArrayList<>();
    }
    
    protected ContentEntry(Parcel in) {
        title = in.readString();
        description = in.readString();
        poster = in.readString();
        thumbnail = in.readString();
        rating = in.readString();
        duration = in.readString();
        year = in.readString();
        parentalRating = in.readString();
        trailer = in.readParcelable(TrailerInfo.class.getClassLoader());
        servers = in.createTypedArrayList(ServerInfo.CREATOR);
        seasons = in.createTypedArrayList(SeasonInfo.CREATOR);
    }
    
    public static final Creator<ContentEntry> CREATOR = new Creator<ContentEntry>() {
        @Override
        public ContentEntry createFromParcel(Parcel in) {
            return new ContentEntry(in);
        }
        
        @Override
        public ContentEntry[] newArray(int size) {
            return new ContentEntry[size];
        }
    };
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(poster);
        dest.writeString(thumbnail);
        dest.writeString(rating);
        dest.writeString(duration);
        dest.writeString(year);
        dest.writeString(parentalRating);
        dest.writeParcelable(trailer, flags);
        dest.writeTypedList(servers);
        dest.writeTypedList(seasons);
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    // Getters and Setters
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getPoster() {
        return poster;
    }
    
    public void setPoster(String poster) {
        this.poster = poster;
    }
    
    public String getThumbnail() {
        return thumbnail;
    }
    
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    public String getRating() {
        return rating;
    }
    
    public void setRating(String rating) {
        this.rating = rating;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getYear() {
        return year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }
    
    public String getParentalRating() {
        return parentalRating;
    }
    
    public void setParentalRating(String parentalRating) {
        this.parentalRating = parentalRating;
    }
    
    public TrailerInfo getTrailer() {
        return trailer;
    }
    
    public void setTrailer(TrailerInfo trailer) {
        this.trailer = trailer;
    }
    
    public List<ServerInfo> getServers() {
        return servers;
    }
    
    public void setServers(List<ServerInfo> servers) {
        this.servers = servers;
    }
    
    public List<SeasonInfo> getSeasons() {
        return seasons;
    }
    
    public void setSeasons(List<SeasonInfo> seasons) {
        this.seasons = seasons;
    }
}
