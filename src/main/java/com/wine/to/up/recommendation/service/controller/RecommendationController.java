package com.wine.to.up.recommendation.service.controller;

import com.wine.to.up.recommendation.service.api.recommendation.vo.RecommendationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RecommendationController {

    @GetMapping(path = "/recommendation/{userId:[\\d]+}")
    public RecommendationResponse getByRequest(@PathVariable long userId) {
        return RecommendationResponse.builder().id(userId).build();
    }

}
