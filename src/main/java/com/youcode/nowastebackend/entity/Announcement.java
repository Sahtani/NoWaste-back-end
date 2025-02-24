package com.youcode.nowastebackend.entity;

import com.youcode.nowastebackend.common.security.entity.AppUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "announcements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDateTime postedDate;

    private LocalDate createdAt;

    @OneToOne
    private Product product;

    @ManyToOne
    private AppUser user;

    @OneToMany(mappedBy = "announcement")
    private List<Reservation> reservations;




}
