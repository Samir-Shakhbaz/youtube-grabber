package com.sash.youtube_grabber.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeApiResponse {
    private List<YouTubeVideoItem> items;

    public List<YouTubeVideoItem> getItems() {
        return items;
    }

    public void setItems(List<YouTubeVideoItem> items) {
        this.items = items;
    }
}
