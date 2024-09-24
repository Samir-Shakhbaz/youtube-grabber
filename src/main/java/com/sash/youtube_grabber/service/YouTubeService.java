package com.sash.youtube_grabber.service;

import com.sash.youtube_grabber.model.YouTubeApiResponse;
import com.sash.youtube_grabber.model.YouTubeVideo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class YouTubeService {

    @Value("${youtube.api.key}")
    private String apiKey;

    private final String SEARCH_API_URL = "https://www.googleapis.com/youtube/v3/search";

    @Cacheable("videos")
    public List<YouTubeVideo> searchVideos(String query, String pageToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?part=snippet&q=%s&type=video&key=%s&pageToken=%s", SEARCH_API_URL, query, apiKey, pageToken != null ? pageToken : "");

        try {
            YouTubeApiResponse response = restTemplate.getForObject(url, YouTubeApiResponse.class);
            List<YouTubeVideo> videos = new ArrayList<>();
            if (response != null) {
                response.getItems().forEach(item -> {
                    YouTubeVideo video = new YouTubeVideo();
                    video.setTitle(item.getSnippet().getTitle());
                    video.setDescription(item.getSnippet().getDescription());
                    video.setVideoId(item.getId().getVideoId());
                    videos.add(video);
                });
            }
            return videos;
        } catch (RestClientException e) {
            System.err.println("Error fetching videos from YouTube API: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
