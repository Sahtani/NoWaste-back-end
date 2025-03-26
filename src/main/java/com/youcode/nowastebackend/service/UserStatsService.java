package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.entity.UserStats;

/**
 * Service interface for managing user statistics in the NoWaste application.
 */
public interface UserStatsService {

    /**
     * Get comprehensive statistics for a user (both donor and beneficiary stats)
     *
     * @param userId the ID of the user
     * @return UserStats object containing all statistics
     */
    UserStats getUserStats(Long userId);

    /**
     * Get donor-specific statistics for a user
     *
     * @param userId the ID of the user
     * @return UserStats object with donor-related statistics
     */
    UserStats getDonorStats(Long userId);

    /**
     * Get beneficiary-specific statistics for a user
     *
     * @param userId the ID of the user
     * @return UserStats object with beneficiary-related statistics
     */
    UserStats getBeneficiaryStats(Long userId);

    /**
     * Get overall platform statistics
     *
     * @return UserStats object with platform-wide statistics
     */
    UserStats getOverallStats();
}