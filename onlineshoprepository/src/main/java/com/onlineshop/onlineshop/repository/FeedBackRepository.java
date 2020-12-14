package com.onlineshop.onlineshop.repository;

import com.onlineshop.onlineshop.entity.FeedBackEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends CrudRepository<FeedBackEntity,Long> {
    Iterable<FeedBackEntity> getFeedBackEntitiesByProdusEntity_Id(Long id);
}