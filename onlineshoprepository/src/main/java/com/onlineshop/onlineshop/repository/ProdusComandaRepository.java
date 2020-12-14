package com.onlineshop.onlineshop.repository;

import com.onlineshop.onlineshop.entity.ProdusComandaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdusComandaRepository extends CrudRepository<ProdusComandaEntity,Long> {

}
