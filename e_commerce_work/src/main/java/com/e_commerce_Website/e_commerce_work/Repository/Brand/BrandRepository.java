package com.e_commerce_Website.e_commerce_work.Repository.Brand;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.e_commerce_Website.e_commerce_work.Model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("SELECT b FROM Brand b WHERE b.name = :name")
    Optional<Brand> findByName(@Param("name") String name);

}
