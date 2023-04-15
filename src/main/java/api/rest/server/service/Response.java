package api.rest.server.service;

public class Response {
	
	private boolean avalibility;
	private double dailyRate;
	private double totalAmount;
	
	
	public boolean isAvalibility() {
		return avalibility;
	}
	public void setAvalibility(boolean avalibility) {
		this.avalibility = avalibility;
	}
	public double getDailyRate() {
		return dailyRate;
	}
	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	

}
