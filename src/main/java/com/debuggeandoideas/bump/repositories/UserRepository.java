package com.debuggeandoideas.bump.repositories;

import com.debuggeandoideas.bump.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //Select * FROM users WHERE mail = :mail
    Optional<UserEntity> findByMail(String mail);
}
