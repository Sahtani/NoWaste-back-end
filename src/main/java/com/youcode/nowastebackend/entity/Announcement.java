package com.youcode.nowastebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.entity.enums.Status;
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

    @Column(columnDefinition = "TEXT")
    private String location;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Column(length = 255)
    private String rejectionReason;

    @ManyToOne
    private AppUser donor;

    @ManyToOne
    private AppUser beneficiary;

    @OneToMany(mappedBy = "announcement", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "announcement", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "announcement", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "announcement_interests",
            joinColumns = @JoinColumn(name = "announcement_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private Set<AppUser> interestedUsers = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


}
