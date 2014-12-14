package menon.cs6890.FinalProject.Controller;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	
	/**
	 * This private constructor prevents instantiation
	 */
	private Constants() {}
	
	public static final String SPACER = " ";
	public static final String EMPTY_STRING = "";
	public static final String NEW_LINE = "\n";
	
	@SuppressWarnings("serial")
	public static final Map<String, String> INFO_EXTRACTOR_MAP = new HashMap<String, String>() {
		
		{
			put("REQMNT TRAVEL", "menon.cs6890.FinalProject.Controller.InfoExtractor.CityDateExtractor");
			put("WHEN", "menon.cs6890.FinalProject.Controller.InfoExtractor.TravelDateExtractor");
			put("CTYP", "menon.cs6890.FinalProject.Controller.InfoExtractor.FromCityExtractor");
			put("CHOOSE", "menon.cs6890.FinalProject.Controller.InfoExtractor.FlightOptionExtractor");
		}
		
	};
	
}
