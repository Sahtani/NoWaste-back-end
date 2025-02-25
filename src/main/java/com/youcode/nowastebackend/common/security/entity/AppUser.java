package com.youcode.nowastebackend.common.security.entity;

import com.youcode.nowastebackend.entity.Announcement;
import com.youcode.nowastebackend.entity.Reservation;
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
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column( nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppRole role;


    // Relations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "beneficiary")
    private List<Reservation> reservations;

    /*@OneToMany(mappedBy = "user")
    private List<Notification> notifications;*/

}
