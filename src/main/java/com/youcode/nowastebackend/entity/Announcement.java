package com.youcode.nowastebackend.entity;

import com.youcode.nowastebackend.entity.Product;
import com.youcode.nowastebackend.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "announcements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Product product;


    @NotNull
    private LocalDate createdAt;

    @ManyToOne
    private User user;

    private LocalDate postedDate;

}
