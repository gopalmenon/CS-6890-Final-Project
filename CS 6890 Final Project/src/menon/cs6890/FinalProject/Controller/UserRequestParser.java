package menon.cs6890.FinalProject.Controller;

import java.util.ArrayList;
import java.util.List;

import menon.cs6890.FinalProject.Controller.InfoExtractor.InformationExtractor;
import menon.cs6890.FinalProject.Controller.InfoExtractor.InformationExtractorFactory;
import menon.cs6890.FinalProject.Database.DestinationCities;
import menon.cs6890.FinalProject.Database.FlightSchedules;
import menon.cs6890.FinalProject.Database.FlightTimes;

import org.apache.velocity.VelocityContext;
import org.vkedco.nlp.earlyparser.ParseTree;
import org.vkedco.nlp.earlyparser.Parser;

public class UserRequestParser {
	
	private static final String GRAMMAR_FILE_PATH = "flightReservationGrammar.txt";
	
	private FlightInformationBean flightInformationFromUser;
	private List<FlightTimes> flightTimes;
	
	public UserRequestParser() {
		this.flightInformationFromUser = new FlightInformationBean();
	}
	
	public String parse(String userInput) {
		
		String production = null;
		String[] terminalsInCommand = userInput.split("\\s");
		Parser earlyParser = Parser.factory(GRAMMAR_FILE_PATH, terminalsInCommand.length);
		ArrayList<ParseTree> parseTrees = earlyParser.parse(userInput.toLowerCase());
		if (parseTrees != null && parseTrees.size() != 0 && parseTrees.get(0) != null) {
			production = parseTrees.get(0).mCFProduction.toString().split("::=")[1].trim();
			InformationExtractor informationExtractor = InformationExtractorFactory.getInformationExtractorInstance(production, parseTrees);
			populatedFlightInformation(informationExtractor.extractInformation());
			return constructResponseToUser(true);
		} else {
			return constructResponseToUser(false);
		}
		
	}
	
	private String constructResponseToUser(boolean validResponse) {
		
		//Construct response based on what information has been obtained so far
		if ((this.flightInformationFromUser.getFromCity() == null || Constants.EMPTY_STRING.equals(this.flightInformationFromUser.getFromCity())) &&
			(this.flightInformationFromUser.getToCity() == null || Constants.EMPTY_STRING.equals(this.flightInformationFromUser.getToCity())) && 
			(this.flightInformationFromUser.getMonthOfTravel() == null || Constants.EMPTY_STRING.equals(this.flightInformationFromUser.getMonthOfTravel())) &&
			(this.flightInformationFromUser.getDayOfWeekOfTravel() == null || Constants.EMPTY_STRING.equals(this.flightInformationFromUser.getDayOfWeekOfTravel())) &&
			this.flightInformationFromUser.getDayNumberOfTravel() == 0) {
			
			this.flightTimes = null;
			ResponseGenerator responseGenerator = new ResponseGenerator("whereTo.vm");
	        VelocityContext context = new VelocityContext();
			return responseGenerator.getResponseForUser(context);
			
		} else if (this.flightInformationFromUser.getToCity() != null && !Constants.EMPTY_STRING.equals(this.flightInformationFromUser.getToCity()) &&
				  (this.flightInformationFromUser.getFromCity() == null || Constants.EMPTY_STRING.equals(this.flightInformationFromUser.getFromCity()))) {
			
			this.flightTimes = null;
			ResponseGenerator responseGenerator = new ResponseGenerator("whereFrom.vm");
	        VelocityContext context = new VelocityContext();
	        context.put("toCity", DestinationCities.getCityName(this.flightInformationFromUser.getToCity()));
			return responseGenerator.getResponseForUser(context);
			
		} else if (this.flightInformationFromUser.getToCity() != null && !Constants.EMPTY_STRING.equals(this.flightInformationFromUser.getToCity()) &&
				  (this.flightInformationFromUser.getFromCity() != null && !Constants.EMPTY_STRING.equals(this.flightInformationFromUser.getFromCity())) &&
				  this.flightInformationFromUser.getDayNumberOfTravel() == 0 && 
				 (this.flightInformationFromUser.getMonthOfTravel() == null || Constants.EMPTY_STRING.equals(this.flightInformationFromUser.getMonthOfTravel())) &&
				 (this.flightInformationFromUser.getDayOfWeekOfTravel() == null || Constants.EMPTY_STRING.equals(this.flightInformationFromUser.getDayOfWeekOfTravel()))) {
			
			this.flightTimes = null;
			ResponseGenerator responseGenerator = new ResponseGenerator("whenToTravel.vm");
	        VelocityContext context = new VelocityContext();
	        context.put("fromCity", DestinationCities.getCityName(this.flightInformationFromUser.getFromCity()));
	        context.put("toCity", DestinationCities.getCityName(this.flightInformationFromUser.getToCity()));
			return responseGenerator.getResponseForUser(context);
			
		} else if (this.flightInformationFromUser.getFromCity() != null && !Constants.EMPTY_STRING.equals(this.flightInformationFromUser.getFromCity()) &&
				   this.flightInformationFromUser.getToCity() != null && !Constants.EMPTY_STRING.equals(this.flightInformationFromUser.getToCity()) &&
				 ((this.flightInformationFromUser.getMonthOfTravel() != null && !Constants.EMPTY_STRING.equals(this.flightInformationFromUser.getMonthOfTravel()) && this.flightInformationFromUser.getDayNumberOfTravel() != 0) ||
				  (this.flightInformationFromUser.getDayOfWeekOfTravel() != null && !Constants.EMPTY_STRING.equals(this.flightInformationFromUser.getDayOfWeekOfTravel())) ||
				  (this.flightInformationFromUser.getDayNumberOfTravel() != 0))) {
			
			if (this.flightTimes == null) {
		        List<FlightTimes> flightTimes = FlightSchedules.getFlightTimes(DestinationCities.getAirportCode(this.flightInformationFromUser.getFromCity()), DestinationCities.getAirportCode(this.flightInformationFromUser.getToCity()), this.flightInformationFromUser);
		        ResponseGenerator responseGenerator = null;
		        VelocityContext context = new VelocityContext();
		        if (flightTimes == null || flightTimes.isEmpty()) {
		        	this.flightTimes = null;
			        responseGenerator = new ResponseGenerator("noAvailableFlights.vm");
			        context.put("fromCity", DestinationCities.getCityName(this.flightInformationFromUser.getFromCity()));
			        context.put("toCity", DestinationCities.getCityName(this.flightInformationFromUser.getToCity()));
				} else {
					this.flightTimes = flightTimes;
			        responseGenerator = new ResponseGenerator("availableFlights.vm");
			        context.put("fromCity", DestinationCities.getCityName(this.flightInformationFromUser.getFromCity()));
			        context.put("toCity", DestinationCities.getCityName(this.flightInformationFromUser.getToCity()));
			        context.put("flights", getFlightTimes(flightTimes));
				}
				return responseGenerator.getResponseForUser(context);
			} else {
				int flightOption = this.flightInformationFromUser.getFlightOption();
				boolean flightOptionValid = false;
				if (flightOption != 0) {
					if (this.flightTimes.size() >= flightOption - 1) {
						flightOptionValid = true;
					}
				}
		        ResponseGenerator responseGenerator = null;
		        VelocityContext context = new VelocityContext();
				if (flightOptionValid) {
					responseGenerator = new ResponseGenerator("bookedSelectedFlight.vm");
			        context.put("fromCity", DestinationCities.getCityName(this.flightInformationFromUser.getFromCity()));
			        context.put("toCity", DestinationCities.getCityName(this.flightInformationFromUser.getToCity()));
			        context.put("departureDate", convertDbDateToDisplay(this.flightTimes.get(flightOption - 1).getDepartureDate()));
			        context.put("departureTime", convertDbTimeToDisplay(this.flightTimes.get(flightOption - 1).getDepartureTime()));
			        context.put("arrivalTime", convertDbTimeToDisplay(this.flightTimes.get(flightOption - 1).getArrivalTime()));
			        this.flightTimes = null;
			        this.flightInformationFromUser.initialize();
				} else {
					responseGenerator = new ResponseGenerator("invalidFlightOption.vm");
				}
				return responseGenerator.getResponseForUser(context);
			}
		}
		
        ResponseGenerator responseGenerator = new ResponseGenerator("didNotUnderstand.vm");
        VelocityContext context = new VelocityContext();
		return responseGenerator.getResponseForUser(context);
	}
	
	private void populatedFlightInformation(FlightInformationBean flightInformationFromUser) {
		
		if (flightInformationFromUser.getDayNumberOfTravel() != 0) {
			this.flightInformationFromUser.setDayNumberOfTravel(flightInformationFromUser.getDayNumberOfTravel());
		}
		
		if (flightInformationFromUser.getDayOfWeekOfTravel() != null && !Constants.EMPTY_STRING.equals(flightInformationFromUser.getDayOfWeekOfTravel().trim())) {
			this.flightInformationFromUser.setDayOfWeekOfTravel(flightInformationFromUser.getDayOfWeekOfTravel().trim());
		}
		
		if (flightInformationFromUser.getFromCity() != null && !Constants.EMPTY_STRING.equals(flightInformationFromUser.getFromCity().trim())) {
			this.flightInformationFromUser.setFromCity(flightInformationFromUser.getFromCity().trim());
		}
		
		if (flightInformationFromUser.getToCity() != null && !Constants.EMPTY_STRING.equals(flightInformationFromUser.getToCity().trim())) {
			this.flightInformationFromUser.setToCity(flightInformationFromUser.getToCity().trim());
		}
		
		if (flightInformationFromUser.getMonthOfTravel() != null && !Constants.EMPTY_STRING.equals(flightInformationFromUser.getMonthOfTravel().trim())) {
			this.flightInformationFromUser.setMonthOfTravel(flightInformationFromUser.getMonthOfTravel().trim());
		}
		
		if (flightInformationFromUser.getFlightOption() != 0) {
			this.flightInformationFromUser.setFlightOption(flightInformationFromUser.getFlightOption());
		}
	}
	
	private String getFlightTimes(List<FlightTimes> flightTimes) {
		
		if (flightTimes == null) {
			return null;
		}
		
		StringBuffer returnValue = new StringBuffer();
		
		for (FlightTimes flight : flightTimes) {
			returnValue.append(Constants.NEW_LINE).append("Leaving ").append(convertDbDateToDisplay(flight.getDepartureDate())).append(" at ").append(convertDbTimeToDisplay(flight.getDepartureTime())).append(" and arriving ").append(convertDbTimeToDisplay(flight.getArrivalTime()));
		}
		
		return returnValue.toString();
	}
	
	private String convertDbDateToDisplay(int dbDate) {
		
		String dateString = Integer.toString(dbDate);
		StringBuffer returnValue = new StringBuffer();
		
		returnValue.append(dateString.substring(4, 6)).append("-").append(dateString.substring(6)).append("-").append(dateString.substring(0, 4));
		
		return returnValue.toString();
		
	}
	
	private String convertDbTimeToDisplay(int dbTime) {
		
		String dateString = Integer.toString(dbTime);
		if (dateString.length() == 3) {
			dateString = "0" + dateString;
		}
		
		StringBuffer returnValue = new StringBuffer();
		
		returnValue.append(dateString.substring(0, 2)).append(":").append(dateString.substring(2));
		
		return returnValue.toString();

	}

}
