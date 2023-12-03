package api.rest.server.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private String type;
    private int quantity;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;

    public Reservation(String type, int quantity, double price, LocalDate startDate, LocalDate endDate) {
        this.setType(type);
        this.setQuantity(quantity);
        this.setPrice(price);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        
    }
    public Reservation() {}
    
    public boolean overlaps(LocalDate otherStartDate, LocalDate otherEndDate) {
        return !startDate.isAfter(otherEndDate) && !endDate.isBefore(otherStartDate);
    }


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public void setRoom(Room room2) {
		this.room = room2;
		
	}
	public Room getRoom() {
		return room;
	}
}
