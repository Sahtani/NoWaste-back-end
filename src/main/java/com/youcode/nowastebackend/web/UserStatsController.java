package com.youcode.nowastebackend.web;

import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.entity.UserStats;
import com.youcode.nowastebackend.service.UserStatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users/stats")
@Slf4j
public class UserStatsController {

    private final UserStatsService userStatsService;

    @Autowired
    public UserStatsController(UserStatsService userStatsService) {
        this.userStatsService = userStatsService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserStats> getCurrentUserStats(@AuthenticationPrincipal AppUser currentUser) {
        UserStats stats = userStatsService.getUserStats(currentUser.getId());
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserStats> getUserStats(@PathVariable Long userId) {
        UserStats stats = userStatsService.getUserStats(userId);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/donors/{userId}")
    public ResponseEntity<UserStats> getDonorStats(@PathVariable Long userId) {
        UserStats stats = userStatsService.getDonorStats(userId);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/beneficiaries/{userId}")
    public ResponseEntity<UserStats> getBeneficiaryStats(@PathVariable Long userId) {
        UserStats stats = userStatsService.getBeneficiaryStats(userId);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/overall")
    public ResponseEntity<UserStats> getOverallStats() {
        UserStats stats = userStatsService.getOverallStats();
        return ResponseEntity.ok(stats);
    }

   /* @GetMapping("/top-donors")
    @Operation(summary = "Récupérer les meilleurs donateurs")*/
    /*public ResponseEntity<?> getTopDonors(@RequestParam(defaultValue = "5") int limit) {
        log.info("Récupération des {} meilleurs donateurs", limit);
        try {
            return ResponseEntity.ok(userStatsService.getTopDonors(limit));
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des meilleurs donateurs", e);
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Erreur lors de la récupération des statistiques"));
        }
    }

    @GetMapping("/top-beneficiaries")
    @Operation(summary = "Récupérer les bénéficiaires les plus actifs")
    public ResponseEntity<?> getTopBeneficiaries(@RequestParam(defaultValue = "5") int limit) {
        log.info("Récupération des {} bénéficiaires les plus actifs", limit);
        try {
            return ResponseEntity.ok(userStatsService.getTopBeneficiaries(limit));
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des bénéficiaires les plus actifs", e);
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Erreur lors de la récupération des statistiques"));
        }
    }*/
}