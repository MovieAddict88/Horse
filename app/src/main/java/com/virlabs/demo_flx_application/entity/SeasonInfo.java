package com.virlabs.demo_flx_application.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SeasonInfo implements Parcelable {
    
    @SerializedName("Season")
    @Expose
    private int season;
    
    @SerializedName("SeasonPoster")
    @Expose
    private String seasonPoster;
    
    @SerializedName("Episodes")
    @Expose
    private List<EpisodeInfo> episodes;
    
    public SeasonInfo() {
        episodes = new ArrayList<>();
    }
    
    protected SeasonInfo(Parcel in) {
        season = in.readInt();
        seasonPoster = in.readString();
        episodes = in.createTypedArrayList(EpisodeInfo.CREATOR);
    }
    
    public static final Creator<SeasonInfo> CREATOR = new Creator<SeasonInfo>() {
        @Override
        public SeasonInfo createFromParcel(Parcel in) {
            return new SeasonInfo(in);
        }
        
        @Override
        public SeasonInfo[] newArray(int size) {
            return new SeasonInfo[size];
        }
    };
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(season);
        dest.writeString(seasonPoster);
        dest.writeTypedList(episodes);
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    public int getSeason() {
        return season;
    }
    
    public void setSeason(int season) {
        this.season = season;
    }
    
    public String getSeasonPoster() {
        return seasonPoster;
    }
    
    public void setSeasonPoster(String seasonPoster) {
        this.seasonPoster = seasonPoster;
    }
    
    public List<EpisodeInfo> getEpisodes() {
        return episodes;
    }
    
    public void setEpisodes(List<EpisodeInfo> episodes) {
        this.episodes = episodes;
    }
}
