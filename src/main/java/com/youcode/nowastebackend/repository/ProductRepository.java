package com.youcode.nowastebackend.repository;

import com.youcode.nowastebackend.entity.Product;
import com.youcode.nowastebackend.entity.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByStatus(ProductStatus status);

    List<Product> findByExpirationDateBefore(LocalDate date);
    @Query("SELECT p FROM Product p WHERE p.status = :status AND p.expirationDate < :date")
    List<Product> findProductsByStatusAndExpirationDate(
            @Param("status") ProductStatus status,
            @Param("date") LocalDate date
    );

}
