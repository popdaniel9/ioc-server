package com.onlineshop.onlineshop.repository;

import com.onlineshop.onlineshop.entity.ProducatorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducatorRepository extends CrudRepository<ProducatorEntity, Long> {

}
