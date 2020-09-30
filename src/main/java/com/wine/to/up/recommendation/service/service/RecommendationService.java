package com.wine.to.up.recommendation.service.service;

import com.wine.to.up.recommendation.service.dto.RequestDTO;


public interface RecommendationService {
    RequestDTO findById(Long id);
}
