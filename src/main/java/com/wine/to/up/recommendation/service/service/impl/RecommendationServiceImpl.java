package com.wine.to.up.recommendation.service.service.impl;

import com.wine.to.up.recommendation.service.dto.RequestDTO;
import com.wine.to.up.recommendation.service.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service("rcm.recommendationService")
public class RecommendationServiceImpl implements RecommendationService {
    private static final RequestDTO STUB_ANSWER = RequestDTO.builder()
            .recommendedIds(Arrays.asList(1L, 2L))
            .build();

    @Override
    public RequestDTO findById(final Long id) {
        return STUB_ANSWER;
    }
}
