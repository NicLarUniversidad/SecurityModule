package com.cl.evaluation.register.repositories;

import com.cl.evaluation.register.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
    UserEntity findFirstByEmail(String email);
}
