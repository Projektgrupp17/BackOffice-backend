package com.example.demos.dto;

import java.util.Optional;

import com.example.demos.model.Advertisement_video;

public class PlayedDTO {
    private Optional<Advertisement_video> video;
    private boolean played;
    private Integer count;


    public PlayedDTO(Optional<Advertisement_video> video,Integer count,boolean played){
        this.count = count;
        this.played = played;
        this.video = video;
    }
    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }/**
     * @return the video
     */
    public Optional<Advertisement_video> getVideo() {
        return video;
    }

    public boolean getPlayed(){
        return played;
    }
}

