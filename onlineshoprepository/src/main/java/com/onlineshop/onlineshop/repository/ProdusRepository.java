package com.onlineshop.onlineshop.repository;

import com.onlineshop.onlineshop.entity.ProdusEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ProdusRepository extends CrudRepository<ProdusEntity,Long> {

    ProdusEntity getById(Long id);

    @Query("select p from ProdusEntity p order by p.cantitate asc ")
    List<ProdusEntity> getProductsOrderedByQuantity();

    List<ProdusEntity> getProdusEntitiesByCategorie(String categorie);
}
