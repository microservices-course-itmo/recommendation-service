package com.wine.to.up.demo.service.repository;

import com.wine.to.up.demo.service.domain.entity.Message;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends CrudRepository<Message, UUID> {
    /**
     * Define our custom method using HQL language
     * @return list of distinct message stored in DB
     */
    @Query("SELECT DISTINCT m.content FROM Message m")
    List<String> findDistinctContent(Pageable pageable);

    default List<String> findDistinctContent() {
        return findDistinctContent(PageRequest.of(0, 5));
    }
}
