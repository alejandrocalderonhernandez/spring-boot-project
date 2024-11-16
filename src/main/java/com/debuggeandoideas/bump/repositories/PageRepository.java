package com.debuggeandoideas.bump.repositories;

import com.debuggeandoideas.bump.entities.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PageRepository extends JpaRepository<PageEntity, Long> {

    //SELECT * FROM page Where title = :title  SQL
    //@Query("from PageEntity where title=:title")//JPQL
    Optional<PageEntity> findByTitle(String title);

    //If exist return true if not return false
    Boolean existsByTitle(String title);

    @Modifying
    @Query("DELETE FROM PageEntity WHERE  title=:title")
    void deleteByTitle(String title);
}
