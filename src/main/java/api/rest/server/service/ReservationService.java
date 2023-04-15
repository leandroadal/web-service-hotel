package api.rest.server.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import api.rest.server.entities.Reservation;
import api.rest.server.entities.Room;

@Service
public class ReservationService {
	private List<Room> availableRooms;
	private List<Reservation> reservations;

	public ReservationService() {
		availableRooms = new ArrayList<>();
		reservations = new ArrayList<>();

		// adiciona os quartos disponíveis
		availableRooms.add(new Room("solteiro", "standard", 100, 10));
		availableRooms.add(new Room("casal", "standard", 120, 20));
		availableRooms.add(new Room("familia", "standard", 150, 5));
		availableRooms.add(new Room("solteiro", "executivo", 1000, 10));
		availableRooms.add(new Room("casal", "executivo", 1200, 20));
		availableRooms.add(new Room("familia", "executivo", 1500, 5));
		availableRooms.add(new Room("solteiro", "luxo", 10000, 10));
		availableRooms.add(new Room("casal", "luxo", 12000, 20));
		availableRooms.add(new Room("familia", "luxo", 15000, 5));
	}

	/*
	 * public boolean checkAvailability(String type, int quantity) { // verifica se
	 * há quartos disponíveis do tipo e com a quantidade desejada for (Room room :
	 * availableRooms) { if (room.getType().equals(type) && room.getAvailability()
	 * >= quantity) { return true; } } return false; }
	 */

	public double calculatePrice(Room room, long numberOfDays, String type, int quantity) {
		// calcula o preço total da reserva
		double price = 0;
		if (room.getType().equals(type)) {
			price = room.getPrice() * quantity * numberOfDays;
		}
		return price;
	}

	/*
	 * public void makeReservation(String type, int quantity, String guestName,
	 * String guestEmail) { // verifica a disponibilidade dos quartos e calcula o
	 * preço da reserva if (checkAvailability(type, quantity)) { double price =
	 * calculatePrice(type, quantity);
	 * 
	 * // atualiza a disponibilidade dos quartos reservados for (Room room :
	 * availableRooms) { if (room.getType().equals(type)) {
	 * room.setAvailability(room.getAvailability() - quantity); } }
	 * 
	 * // adiciona a reserva à lista de reservas feitas reservations.add(new
	 * Reservation(type, quantity, price, guestName, guestEmail)); } else { throw
	 * new
	 * RuntimeException("Não há quartos disponíveis para a reserva solicitada."); }
	 * }
	 */

	private Room findRoomByType(String type, String category) {
		for (Room room : availableRooms) {
			if (room.getType().equals(type) && room.getCategory().equals(category) && room.getAvailability() > 0) {
				return room;
			}
		}
		return null;
	}

	public boolean checkAvailability(String type, String category, int quantity, LocalDate startDate,
			LocalDate endDate) {
		Room room = findRoomByType(type, category);
		if (room != null && room.getAvailability() >= quantity) {
			// verifica se o quarto já está reservado durante o período de reserva
			// solicitado
			for (Reservation reservation : reservations) {
				if (reservation.getRoom() == room && room.getAvailability() > 0
						&& reservation.overlaps(startDate, endDate))
					return true; // Enquanto existir quartos disponiveis
				if (reservation.getRoom() == room && reservation.overlaps(startDate, endDate)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public Response makeReservation(String type, String category, int quantity, LocalDate startDate,
			LocalDate endDate) {
		// verifica a disponibilidade dos quartos e calcula o preço da reserva
		if (checkAvailability(type, category, quantity, startDate, endDate)) {
			// localiza o quarto disponível do tipo solicitado
			Room room = findRoomByType(type, category);
			
			// Calcula o número de dias entre startDate e endDate
			long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);

			double price = calculatePrice(room, numberOfDays, type, quantity);

			// atualiza a disponibilidade do quarto reservado
			room.setAvailability(room.getAvailability() - quantity);

			// adiciona a reserva à lista de reservas feitas e associa ao quarto correspondente
			Reservation reservation = new Reservation(type, quantity, price, startDate, endDate);
			reservation.setRoom(room);
			reservations.add(reservation);
			Response response = new Response();
			response.setAvalibility(true);
			response.setDailyRate(room.getPrice());
			response.setTotalAmount(price);
			return response;
		} else {
			Response response = new Response();
			response.setAvalibility(false);
			response.setDailyRate(0);
			response.setTotalAmount(0);
			return response;
		}
	}

	public List<Reservation> getReservations() {
		// TODO Auto-generated method stub
		return reservations;
	}

}
