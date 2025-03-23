package com.youcode.nowastebackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStats {
    // Common stats
    private double averageRating;
    private int ratingCount;
    private double totalWeight; // in kg
    private double environmentalImpact; // CO2 saved in kg

    // Donor specific stats
    private int totalDonations;
    private int activeListings;
    private int completedDonations;

    // Beneficiary specific stats
    private int totalCollections;
    private int reservations;
    private int completedCollections;
}
