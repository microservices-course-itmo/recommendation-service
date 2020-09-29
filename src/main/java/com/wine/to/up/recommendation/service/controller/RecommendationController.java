package com.wine.to.up.recommendation.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RecommendationController {

    @GetMapping(path="/recommendation/{userId:[\\d]+}")
    public List<String> getByUserId(@PathVariable long userId) {
        return Arrays.asList("thisOne", "andThatOne", String.format("for %d", userId));
    }

}
