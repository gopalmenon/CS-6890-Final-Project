package menon.cs6890.FinalProject.Database;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DestinationCities {

	private static Map<String, CityDatabaseAttributes> destinationCitiesFromDatabase = new HashMap<String, CityDatabaseAttributes>();
	private static final String INPUT_TEXT_FILE = "Cities.txt";
	private static String CODE_TYPE_AKA = "AKA";
	private static String CODE_TYPE_DESCRIPTION = "Description";
	
	private static DestinationCities instance;
	
	private DestinationCities() {
		
	}
	
	private static DestinationCities getInstance() {
		
		if (instance == null) {
			instance = new DestinationCities();
			instance.loadCitiesFromDatabase();
		}
		
		return instance;
	}
	
	private void loadCitiesFromDatabase() {
		
		TextFileReader citiesTextFileReader = null;
		try {
			citiesTextFileReader = new TextFileReader(INPUT_TEXT_FILE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		List<String> cities = citiesTextFileReader.getLinesFromTextFile();
		for (String city : cities) {
			String[] cityParts = city.split("\\|");
			destinationCitiesFromDatabase.put(cityParts[0], new CityDatabaseAttributes(cityParts[1], cityParts[2]));
		}
		
	}
	
	public static String getCityName(String input) {
		
		DestinationCities instance = getInstance();
		CityDatabaseAttributes cityDatabaseAttributes = destinationCitiesFromDatabase.get(input.trim());
		if (CODE_TYPE_DESCRIPTION.equals(cityDatabaseAttributes.getCityCodeType())) {
			return cityDatabaseAttributes.getCityName();
		} else if (CODE_TYPE_AKA.equals(cityDatabaseAttributes.getCityCodeType())) {
			return destinationCitiesFromDatabase.get(cityDatabaseAttributes.getCityName()).getCityName();
		}
		
		return null;
		
	}
	
	public static String getAirportCode(String input) {
		
		DestinationCities instance = getInstance();
		CityDatabaseAttributes cityDatabaseAttributes = destinationCitiesFromDatabase.get(input.trim());
		if (CODE_TYPE_DESCRIPTION.equals(cityDatabaseAttributes.getCityCodeType())) {
			return input.toUpperCase();
		} else if (CODE_TYPE_AKA.equals(cityDatabaseAttributes.getCityCodeType())) {
			return cityDatabaseAttributes.getCityName().toUpperCase();
		}
		
		return null;
		
	}
	

}
