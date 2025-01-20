package com.youcode.nowastebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Announcement announcement;

    @ManyToOne
    private User beneficiary;

    private LocalDate reservationDate;

}
