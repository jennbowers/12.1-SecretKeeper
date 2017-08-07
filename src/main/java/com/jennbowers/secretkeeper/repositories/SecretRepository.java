package com.jennbowers.secretkeeper.repositories;

import com.jennbowers.secretkeeper.models.Secret;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretRepository extends CrudRepository<Secret, Long>{
}
