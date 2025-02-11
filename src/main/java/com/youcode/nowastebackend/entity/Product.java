package com.youcode.nowastebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private LocalDate expirationDate;

    @Column(nullable = false)
    private boolean isAvailable;

    private String location;

    private String image;

    private ProductStatus status;
    // Relations
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

  /*  @ManyToOne
    @JoinColumn(name = "donation_id")
    private Donation donation;*/

}
