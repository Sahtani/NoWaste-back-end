package com.youcode.nowastebackend.entity;

import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.entity.enums.AnnouncementStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @NotNull private String title;

    @NotNull
    private LocalDateTime postedDate;

    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private AnnouncementStatus status = AnnouncementStatus.PENDING;

    @Column(length = 500)
    private String rejectionReason;

    @OneToMany(mappedBy = "announcement", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    private AppUser user;

    @OneToMany(mappedBy = "announcement", cascade = CascadeType.ALL)
    private List<Reservation> reservations;




}
