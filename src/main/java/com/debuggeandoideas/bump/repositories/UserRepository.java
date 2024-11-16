package com.debuggeandoideas.bump.repositories;

import com.debuggeandoideas.bump.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
