package com.youcode.nowastebackend.repository;

import com.youcode.nowastebackend.entity.Rating;
import com.youcode.nowastebackend.common.security.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    /**
     * Find all ratings for a specific user
     *
     * @param ratedUser the user who was rated
     * @return list of ratings for the user
     */
    List<Rating> findByRatedUser(AppUser ratedUser);

    /**
     * Find all ratings for a user by their ID
     *
     * @param userId the ID of the user who was rated
     * @return list of ratings for the user
     */
    @Query("SELECT r FROM Rating r WHERE r.ratedUser.id = :userId")
    List<Rating> findByRatedUserId(@Param("userId") Long userId);

    /**
     * Count ratings for a specific user
     *
     * @param ratedUser the user who was rated
     * @return count of ratings
     */
    int countByRatedUser(AppUser ratedUser);

    /**
     * Count ratings for a user by their ID
     *
     * @param userId the ID of the user who was rated
     * @return count of ratings
     */
    @Query("SELECT COUNT(r) FROM Rating r WHERE r.ratedUser.id = :userId")
    int countByRatedUserId(@Param("userId") Long userId);

    /**
     * Calculate average rating for a user
     *
     * @param ratedUser the user who was rated
     * @return average rating value
     */
    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.ratedUser = :ratedUser")
    double findAverageRatingByRatedUser(@Param("ratedUser") AppUser ratedUser);

    /**
     * Calculate average rating for a user by their ID
     *
     * @param userId the ID of the user who was rated
     * @return average rating value
     */
    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.ratedUser.id = :userId")
    double findAverageRatingByRatedUserId(@Param("userId") Long userId);

    /**
     * Calculate overall average rating across all users
     *
     * @return average rating value
     */
    @Query("SELECT AVG(r.rating) FROM Rating r")
    double findAverageRating();

    /**
     * Find recent ratings for a user
     *
     * @param userId the ID of the user who was rated
     * @param limit maximum number of ratings to return
     * @return list of recent ratings
     */
    @Query(value = "SELECT * FROM ratings WHERE rated_user_id = :userId ORDER BY created_at DESC LIMIT :limit",
            nativeQuery = true)
    List<Rating> findRecentRatingsByRatedUserId(@Param("userId") Long userId, @Param("limit") int limit);

    /**
     * Find ratings for a specific announcement
     *
     * @param announcementId the ID of the announcement
     * @return list of ratings for the announcement
     */
    @Query("SELECT r FROM Rating r WHERE r.announcement.id = :announcementId")
    List<Rating> findByAnnouncementId(@Param("announcementId") Long announcementId);
}