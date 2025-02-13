package com.youcode.nowastebackend.entity;

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

    @ManyToOne
    @JoinColumn(name = "beneficiary_id", nullable = false)
    private User beneficiary;


}
