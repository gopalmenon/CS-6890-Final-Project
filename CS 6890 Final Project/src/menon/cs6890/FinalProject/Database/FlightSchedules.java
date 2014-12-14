package menon.cs6890.FinalProject.Database;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import menon.cs6890.FinalProject.Controller.Constants;
import menon.cs6890.FinalProject.Controller.FlightInformationBean;
import menon.cs6890.FinalProject.Controller.TravelDateProcessor;

public class FlightSchedules {

	private static Map<String, List<FlightTimes>> flightSchedulesFromDatabase = new HashMap<String, List<FlightTimes>>();
	private static final String INPUT_TEXT_FILE = "flightSchedules.txt";
	private static final String FROM_TO_SEPARATOR = "~";
	
	private static FlightSchedules instance;
	
	private FlightSchedules() {
		
	}
	
	private static FlightSchedules getInstance() {
		
		if (instance == null) {
			instance = new FlightSchedules();
			instance.loadFlightSchedulesFromDatabase();
		}
		
		return instance;
	}
	
	private void loadFlightSchedulesFromDatabase() {
		
		TextFileReader flightSchedulesTextFileReader = null;
		try {
			flightSchedulesTextFileReader = new TextFileReader(INPUT_TEXT_FILE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		List<String> cities = flightSchedulesTextFileReader.getLinesFromTextFile();
		String fromAndToCities = null;
		List<FlightTimes> flightsBetweenCities = null;
		for (String city : cities) {
			String[] scheduleParts = city.split("\\|");
			fromAndToCities = scheduleParts[0].trim().toUpperCase() + FROM_TO_SEPARATOR +  scheduleParts[1].trim().toUpperCase();
			if (flightSchedulesFromDatabase.containsKey(fromAndToCities)) {
				flightsBetweenCities = flightSchedulesFromDatabase.get(fromAndToCities);
			} else {
				flightsBetweenCities = new ArrayList<FlightTimes>();
			}
			flightsBetweenCities.add(new FlightTimes(Integer.parseInt(scheduleParts[2]), Integer.parseInt(scheduleParts[3]), Integer.parseInt(scheduleParts[4]), Integer.parseInt(scheduleParts[5])));
			flightSchedulesFromDatabase.put(fromAndToCities, flightsBetweenCities);
		}
		
	}
	
	public static List<FlightTimes> getFlightTimes(String fromCity, String toCity, FlightInformationBean flightInformationFromUser) {
		
		FlightSchedules instance = getInstance();
		String fromAndToCities = fromCity.trim().toUpperCase() + FROM_TO_SEPARATOR + toCity.trim().toUpperCase();
		if (flightSchedulesFromDatabase.containsKey(fromAndToCities)) {
			return filterFlightTimes(flightSchedulesFromDatabase.get(fromAndToCities), flightInformationFromUser);
		}
		return null;
	}
	
	private static List<FlightTimes> filterFlightTimes(List<FlightTimes> flightTimes, FlightInformationBean flightInformationFromUser) {
		
		int travelDate = 0;
		
		if (flightInformationFromUser.getMonthOfTravel() != null && !Constants.EMPTY_STRING.equals(flightInformationFromUser.getMonthOfTravel().trim()) && flightInformationFromUser.getDayNumberOfTravel() != 0) {
			travelDate = new TravelDateProcessor().getTravelDate(flightInformationFromUser.getMonthOfTravel().trim(), flightInformationFromUser.getDayNumberOfTravel());
		} else if (flightInformationFromUser.getDayOfWeekOfTravel() != null && !Constants.EMPTY_STRING.equals(flightInformationFromUser.getDayOfWeekOfTravel().trim())) {
			travelDate = new TravelDateProcessor().getTravelDate(flightInformationFromUser.getDayOfWeekOfTravel().trim());
		} else if (flightInformationFromUser.getDayNumberOfTravel() != 0) {
			travelDate = new TravelDateProcessor().getTravelDate(flightInformationFromUser.getDayNumberOfTravel());
		}
		
		List<FlightTimes> returnValue = new ArrayList<FlightTimes>();
		
		for (FlightTimes flightTime : flightTimes) {
			if (flightTime.getDepartureDate() == travelDate) {
				returnValue.add(flightTime);
			}
		}
		
		return returnValue;
	}

}
