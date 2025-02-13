package com.youcode.nowastebackend.entity;

import com.youcode.nowastebackend.entity.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String description;

    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDateTime expirationDate;


    @Column(nullable = false)
    private String location;

    private String image;

    private ProductStatus status;
    // Relations
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
