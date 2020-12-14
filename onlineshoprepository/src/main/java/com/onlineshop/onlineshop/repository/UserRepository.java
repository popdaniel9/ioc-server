package com.onlineshop.onlineshop.repository;

import com.onlineshop.onlineshop.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {

    UserEntity getUserEntityByUsername(String username);
}
