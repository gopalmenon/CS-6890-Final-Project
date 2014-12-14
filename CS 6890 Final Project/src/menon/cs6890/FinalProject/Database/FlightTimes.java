package menon.cs6890.FinalProject.Database;

public class FlightTimes {
	
	private int departureDate;
	private int departureTime;
	private int arrivalDate;
	private int arrivalTime;
	
	public FlightTimes(int departureDate, int departureTime,int arrivalDate, int arrivalTime) {
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
	}
	public int getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(int departureDate) {
		this.departureDate = departureDate;
	}
	public int getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(int departureTime) {
		this.departureTime = departureTime;
	}
	public int getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(int arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
}
