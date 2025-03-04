package com.youcode.nowastebackend.common.security.entity;

import com.youcode.nowastebackend.entity.Announcement;
import com.youcode.nowastebackend.entity.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@SuperBuilder
@SoftDelete
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser implements UserDetails {
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "beneficiary")
    private List<Reservation> reservations;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(getRole().toString())
        );
    }

    @Override
    public String getUsername() {
        return "";
    }
}
