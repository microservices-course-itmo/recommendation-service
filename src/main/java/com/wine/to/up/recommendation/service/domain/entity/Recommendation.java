package com.wine.to.up.recommendation.service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Recommendation {

    private final Long id;
    private final Long userId;
    private final Long recommendationId;

}
