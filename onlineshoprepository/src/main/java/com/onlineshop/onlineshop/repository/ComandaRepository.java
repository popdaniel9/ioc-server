package com.onlineshop.onlineshop.repository;

import com.onlineshop.onlineshop.entity.ComandaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComandaRepository extends CrudRepository<ComandaEntity,Long> {

}
