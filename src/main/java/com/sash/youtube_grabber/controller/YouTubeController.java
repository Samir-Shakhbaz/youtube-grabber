package com.sash.youtube_grabber.controller;

import com.sash.youtube_grabber.model.YouTubeVideo;
import com.sash.youtube_grabber.service.YouTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/youtube")
public class YouTubeController {

    @Autowired
    private YouTubeService youTubeService;

    @GetMapping("/videos")
    public List<YouTubeVideo> searchVideos(@RequestParam String query, @RequestParam(required = false) String pageToken) {
        return youTubeService.searchVideos(query, pageToken);
    }
}
