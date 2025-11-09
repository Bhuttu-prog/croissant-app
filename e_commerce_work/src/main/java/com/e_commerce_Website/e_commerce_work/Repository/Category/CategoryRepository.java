package com.e_commerce_Website.e_commerce_work.Repository.Category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.e_commerce_Website.e_commerce_work.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>    {

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    Optional<Category> findByName(@Param("name") String name);

}
