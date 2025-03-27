package com.youcode.nowastebackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStats {

    private double averageRating;
    private int ratingCount;

    private int totalDonations;
    private int activeListings;
    private int completedDonations;

    private int totalCollections;
    private int reservations;
    private int activeReservations;
    private int completedReservations;
}
