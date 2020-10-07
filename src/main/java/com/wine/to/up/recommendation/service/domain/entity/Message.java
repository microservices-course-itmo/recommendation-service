package com.wine.to.up.recommendation.service.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
public class Message {

    private UUID id = UUID.randomUUID();
    private String content;

    public Message(String content) {
        this.content = content;
    }
}
