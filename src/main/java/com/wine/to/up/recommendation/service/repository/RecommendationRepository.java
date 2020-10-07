package com.wine.to.up.recommendation.service.repository;


import com.wine.to.up.recommendation.service.domain.entity.Recommendation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecommendationRepository extends CrudRepository<Recommendation, Long> {

}
