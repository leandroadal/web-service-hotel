package api.rest.server.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.rest.client.Request;
import api.rest.server.entities.Reservation;
import api.rest.server.service.ReservationService;
import api.rest.server.service.Response;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    
    @GetMapping
    public List<Reservation> getReservations() {
        return reservationService.getReservations();
    }

    @PostMapping
    public ResponseEntity<?> makeReservation(@RequestBody Request requestBody) {
        try {
        	String type = requestBody.getType();
        	String category = requestBody.getCategory();
        	int quantity = requestBody.getQuantity();
        	LocalDate startDate = requestBody.getStartDate();
        	LocalDate endDate = requestBody.getEndDate();
            Response response = reservationService.makeReservation(type, category, quantity, startDate, endDate);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}