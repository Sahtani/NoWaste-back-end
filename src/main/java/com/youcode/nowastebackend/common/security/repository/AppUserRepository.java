package com.youcode.nowastebackend.common.security.repository;

import com.youcode.nowastebackend.common.security.entity.AppRole;
import com.youcode.nowastebackend.common.security.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByName(String name);

    List<AppUser> findByRole(AppRole role);

    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.ratedUser.id = :userId")
    double findAverageRatingByUserId(@Param("userId") Long userId);

}
