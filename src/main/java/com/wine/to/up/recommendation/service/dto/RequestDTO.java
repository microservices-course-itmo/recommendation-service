package com.wine.to.up.recommendation.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class RequestDTO {
    private final List<Long> recommendedIds;
}
