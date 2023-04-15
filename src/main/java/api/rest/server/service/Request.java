package api.rest.server.service;

import java.time.LocalDate;

public class Request {
	
	private LocalDate startDate;
	private LocalDate endDate;
	private String type;
	private String category;
	private int quantity;
	
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		
	}
	public int getQuantity() {
		return quantity;
	}
		
}
