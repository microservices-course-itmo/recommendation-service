package com.wine.to.up.recommendation.service.logging;

import com.wine.to.up.commonlib.logging.NotableEvent;


public enum RecommendationServiceNotableEvents implements NotableEvent {
    RECOMMENDATION_EVENT("Something happened"),
    ANOTHER_RECOMMENDATION_EVENT("Something else happened");

    private final String template;

    RecommendationServiceNotableEvents(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }

    @Override
    public String getName() {
        return name();
    }


}
