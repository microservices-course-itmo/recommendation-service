package com.wine.to.up.recommendation.service.domain.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "recommendation")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "recommendation_id")
    private Long recommendationId;

}
