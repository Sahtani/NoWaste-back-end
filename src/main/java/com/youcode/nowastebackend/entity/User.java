package com.youcode.nowastebackend.entity;

import com.youcode.nowastebackend.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Relations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Product> products;

  /*  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Donation> donations;*/

    // Getters and Setters
}
