package com.youcode.nowastebackend.web;

import com.youcode.nowastebackend.dto.request.ReservationRequestDto;
import com.youcode.nowastebackend.dto.response.ReservationResponseDto;
import com.youcode.nowastebackend.entity.enums.Status;
import com.youcode.nowastebackend.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/create")
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody ReservationRequestDto requestDto) {
        return ResponseEntity.ok(reservationService.createReservation(requestDto));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateReservationStatus(@PathVariable Long id, @RequestParam Status status) {
        reservationService.updateReservationStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }
}

