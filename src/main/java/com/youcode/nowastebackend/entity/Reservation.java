package com.youcode.nowastebackend.entity;

import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.entity.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private LocalDate reservationDate;

    @ManyToOne
    private Announcement announcement;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "beneficiary_id", nullable = false)
    private AppUser beneficiary;


}
