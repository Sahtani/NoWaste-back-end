package com.youcode.nowastebackend.common.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.nowastebackend.entity.Announcement;
import com.youcode.nowastebackend.entity.Rating;
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

import java.time.LocalDateTime;
import java.util.*;

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

    @Column(columnDefinition = "TEXT", nullable = false)
    private String address;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppRole role;

    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL)
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

    @ElementCollection
    @CollectionTable(name = "user_privacy_settings",
            joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyColumn(name = "setting_key")
    @Column(name = "setting_value")
    private Map<String, Boolean> privacySettings = new HashMap<>();

    @OneToMany(mappedBy = "ratedUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Rating> ratings = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        // Default privacy settings
        if (this.privacySettings == null) {
            this.privacySettings = new HashMap<>();
            this.privacySettings.put("showEmail", false);
            this.privacySettings.put("showPhone", false);
            this.privacySettings.put("showAddress", false);
            this.privacySettings.put("receiveNotifications", true);
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
