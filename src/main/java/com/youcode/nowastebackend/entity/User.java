package com.youcode.nowastebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // Enum: DONOR, BENEFICIARY, ASSOCIATION

    // Relations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Donation> donations;

    // Getters and Setters
}
