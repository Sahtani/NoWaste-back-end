package com.youcode.nowastebackend.web;

import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.entity.UserStats;
import com.youcode.nowastebackend.service.UserStatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users/stats")
@Tag(name = "User Statistics", description = "Endpoints pour récupérer les statistiques utilisateurs")
@Slf4j
public class UserStatsController {

    private final UserStatsService userStatsService;

    @Autowired
    public UserStatsController(UserStatsService userStatsService) {
        this.userStatsService = userStatsService;
    }

    @GetMapping("/me")
    @Operation(summary = "Récupérer les statistiques de l'utilisateur courant")
    public ResponseEntity<UserStats> getCurrentUserStats(@AuthenticationPrincipal AppUser currentUser) {
        log.info("Récupération des statistiques pour l'utilisateur courant avec ID: {}", currentUser.getId());
        UserStats stats = userStatsService.getUserStats(currentUser.getId());
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/users/{userId}")
    @Operation(summary = "Récupérer les statistiques d'un utilisateur spécifique")
    public ResponseEntity<UserStats> getUserStats(@PathVariable Long userId) {
        log.info("Récupération des statistiques pour l'utilisateur avec ID: {}", userId);
        UserStats stats = userStatsService.getUserStats(userId);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/donors/{userId}")
    @Operation(summary = "Récupérer les statistiques spécifiques au donateur pour un utilisateur")
    public ResponseEntity<UserStats> getDonorStats(@PathVariable Long userId) {
        log.info("Récupération des statistiques donateur pour l'utilisateur avec ID: {}", userId);
        UserStats stats = userStatsService.getDonorStats(userId);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/beneficiaries/{userId}")
    @Operation(summary = "Récupérer les statistiques spécifiques au bénéficiaire pour un utilisateur")
    public ResponseEntity<UserStats> getBeneficiaryStats(@PathVariable Long userId) {
        log.info("Récupération des statistiques bénéficiaire pour l'utilisateur avec ID: {}", userId);
        UserStats stats = userStatsService.getBeneficiaryStats(userId);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/overall")
    @Operation(summary = "Récupérer les statistiques globales de la plateforme")
    public ResponseEntity<UserStats> getOverallStats() {
        log.info("Récupération des statistiques globales de la plateforme");
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