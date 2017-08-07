package com.jennbowers.secretkeeper.repositories;

import com.jennbowers.secretkeeper.models.Secret;
import com.jennbowers.secretkeeper.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecretRepository extends CrudRepository<Secret, Long>{
    List<Secret> findAllByUser(User user);
}
