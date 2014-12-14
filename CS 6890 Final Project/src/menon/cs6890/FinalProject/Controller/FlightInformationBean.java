package menon.cs6890.FinalProject.Controller;

public class FlightInformationBean {
	
	private String fromCity;
	private String toCity;
	private String monthOfTravel;
	private String dayOfWeekOfTravel;
	private int dayNumberOfTravel;
	private int travelDate;
	private int flightOption;
	
	public void initialize() {
		
		this.fromCity = null;
		this.toCity = null;
		this.monthOfTravel = null;
		this.dayOfWeekOfTravel = null;
		this.dayNumberOfTravel = 0;
		this.travelDate = 0;
		this.flightOption = 0;
		
	}
	
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public String getMonthOfTravel() {
		return monthOfTravel;
	}
	public void setMonthOfTravel(String monthOfTravel) {
		this.monthOfTravel = monthOfTravel;
	}
	public String getDayOfWeekOfTravel() {
		return dayOfWeekOfTravel;
	}
	public void setDayOfWeekOfTravel(String dayOfWeekOfTravel) {
		this.dayOfWeekOfTravel = dayOfWeekOfTravel;
	}
	public int getDayNumberOfTravel() {
		return dayNumberOfTravel;
	}
	public void setDayNumberOfTravel(int dayNumberOfTravel) {
		this.dayNumberOfTravel = dayNumberOfTravel;
	}
	public int getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(int travelDate) {
		this.travelDate = travelDate;
	}
	public int getFlightOption() {
		return flightOption;
	}
	public void setFlightOption(int flightOption) {
		this.flightOption = flightOption;
	}
	
}
