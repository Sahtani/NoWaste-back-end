package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.common.exception.ResourceNotFoundException;
import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.common.security.repository.AppUserRepository;
import com.youcode.nowastebackend.entity.Announcement;
import com.youcode.nowastebackend.entity.UserStats;
import com.youcode.nowastebackend.entity.Reservation;
import com.youcode.nowastebackend.entity.Rating;
import com.youcode.nowastebackend.repository.AnnouncementRepository;
import com.youcode.nowastebackend.repository.ReservationRepository;
import com.youcode.nowastebackend.repository.RatingRepository;
import com.youcode.nowastebackend.service.UserStatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserStatsServiceImpl implements UserStatsService {

    private final AppUserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final ReservationRepository reservationRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public UserStatsServiceImpl(
            AppUserRepository userRepository,
            AnnouncementRepository announcementRepository,
            ReservationRepository reservationRepository,
            RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.announcementRepository = announcementRepository;
        this.reservationRepository = reservationRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserStats getUserStats(Long userId) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Rating> ratings = ratingRepository.findByRatedUserId(userId);
        List<Announcement> donations = announcementRepository.findByDonorId(userId);
        List<Reservation> reservations = reservationRepository.findByBeneficiaryId(userId);

        UserStats stats = new UserStats();

        stats.setAverageRating(
                ratings.stream()
                        .mapToInt(Rating::getRating)
                        .average()
                        .orElse(0.0)
        );
        stats.setRatingCount(ratings.size());


        long activeCount = donations.stream()
                .filter(d -> "ACTIVE".equals(d.getStatus().toString()))
                .count();
        stats.setActiveListings((int) activeCount);

        long completedCount = donations.stream()
                .filter(d -> "COMPLETED".equals(d.getStatus().toString()))
                .count();

        stats.setCompletedDonations((int) completedCount);

        stats.setTotalCollections(reservations.size());

        long reservedCount = reservations.stream()
                .filter(r -> "RESERVED".equals(r.getStatus().toString()))
                .count();
        stats.setReservations((int) reservedCount);

        long completedCollectionsCount = reservations.stream()
                .filter(r -> "COMPLETED".equals(r.getStatus().toString()))
                .count();
        stats.setCompletedCollections((int) completedCollectionsCount);

        return stats;
    }

    @Override
    @Transactional(readOnly = true)
    public UserStats getDonorStats(Long userId) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Rating> ratings = ratingRepository.findByRatedUserId(userId);
        List<Announcement> donations = announcementRepository.findByDonorId(userId);

        UserStats stats = new UserStats();

        stats.setAverageRating(
                ratings.stream()
                        .mapToInt(Rating::getRating)
                        .average()
                        .orElse(0.0)
        );
        stats.setRatingCount(ratings.size());

        stats.setTotalDonations(donations.size());

        long activeCount = donations.stream()
                .filter(d -> "ACTIVE".equals(d.getStatus().toString()))
                .count();
        stats.setActiveListings((int) activeCount);

        long completedCount = donations.stream()
                .filter(d -> "COMPLETED".equals(d.getStatus().toString()))
                .count();
        stats.setCompletedDonations((int) completedCount);

        stats.setTotalCollections(0);
        stats.setReservations(0);
        stats.setCompletedCollections(0);

        return stats;
    }

    @Override
    @Transactional(readOnly = true)
    public UserStats getBeneficiaryStats(Long userId) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Rating> ratings = ratingRepository.findByRatedUserId(userId);
        List<Reservation> reservations = reservationRepository.findByBeneficiaryId(userId);

        UserStats stats = new UserStats();

        stats.setAverageRating(
                ratings.stream()
                        .mapToInt(Rating::getRating)
                        .average()
                        .orElse(0.0)
        );
        stats.setRatingCount(ratings.size());

        stats.setTotalDonations(0);
        stats.setActiveListings(0);
        stats.setCompletedDonations(0);

        stats.setTotalCollections(reservations.size());

        long reservedCount = reservations.stream()
                .filter(r -> "RESERVED".equals(r.getStatus().toString()))
                .count();
        stats.setReservations((int) reservedCount);

        long completedCount = reservations.stream()
                .filter(r -> "COMPLETED".equals(r.getStatus().toString()))
                .count();
        stats.setCompletedCollections((int) completedCount);

        return stats;
    }

    @Override
    @Transactional(readOnly = true)
    public UserStats getOverallStats() {
        List<Rating> allRatings = ratingRepository.findAll();
        List<Announcement> allDonations = announcementRepository.findAll();
        List<Reservation> allReservations = reservationRepository.findAll();

        UserStats stats = new UserStats();

        stats.setAverageRating(
                allRatings.stream()
                        .mapToInt(Rating::getRating)
                        .average()
                        .orElse(0.0)
        );
        stats.setRatingCount(allRatings.size());

        stats.setTotalDonations(allDonations.size());

        long activeCount = allDonations.stream()
                .filter(d -> "ACTIVE".equals(d.getStatus().toString()))
                .count();
        stats.setActiveListings((int) activeCount);

        long completedCount = allDonations.stream()
                .filter(d -> "COMPLETED".equals(d.getStatus().toString()))
                .count();
        stats.setCompletedDonations((int) completedCount);

        stats.setTotalCollections(allReservations.size());

        long reservedCount = allReservations.stream()
                .filter(r -> "RESERVED".equals(r.getStatus().toString()))
                .count();
        stats.setReservations((int) reservedCount);

        long completedCollectionsCount = allReservations.stream()
                .filter(r -> "COMPLETED".equals(r.getStatus().toString()))
                .count();
        stats.setCompletedCollections((int) completedCollectionsCount);

        return stats;
    }

   /* public List<UserStats> getTopDonors(int limit) {
        List<AppUser> users = userRepository.findAll();

        return users.stream()
                .map(user -> {
                    List<Announcement> userDonations = announcementRepository.findByDonorId(user.getId());
                    List<Rating> userRatings = ratingRepository.findByRatedUserId(user.getId());

                    UserStats stats = new UserStats();
                    stats.setAverageRating(
                            userRatings.stream()
                                    .mapToInt(Rating::getRating)
                                    .average()
                                    .orElse(0.0)
                    );
                    stats.setRatingCount(userRatings.size());
                    stats.setTotalDonations(userDonations.size());
                    stats.setCompletedDonations(
                            userDonations.stream()
                                    .filter(d -> "COMPLETED".equals(d.getStatus().toString()))
                                    .count()
                                    .intValue()
                    );

                    return stats;
                })
                .sorted((s1, s2) -> Integer.compare(s2.getTotalDonations(), s1.getTotalDonations()))
                .limit(limit)
                .collect(Collectors.toList());
    }*/
}