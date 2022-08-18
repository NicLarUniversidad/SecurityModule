package com.cl.evaluacion.register.repositories;

import com.cl.evaluacion.register.entities.PhoneEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends CrudRepository<PhoneEntity, String> {
}
