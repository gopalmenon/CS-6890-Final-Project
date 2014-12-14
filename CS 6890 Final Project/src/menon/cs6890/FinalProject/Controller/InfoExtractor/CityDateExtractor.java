package menon.cs6890.FinalProject.Controller.InfoExtractor;

import java.util.ArrayList;

import org.vkedco.nlp.earlyparser.ParseTree;

import menon.cs6890.FinalProject.Controller.Constants;
import menon.cs6890.FinalProject.Controller.FlightInformationBean;

public class CityDateExtractor implements InformationExtractor {
	
	private String production;
	private ArrayList<ParseTree> parseTrees;
	
	@Override
	public FlightInformationBean extractInformation() {
		
		if (this.parseTrees == null || this.parseTrees.isEmpty()) {
			return null;
		}
		
		FlightInformationBean returnValue = new FlightInformationBean();
		//Extract the first city
		ArrayList<ParseTree> city1ParseTree = parseTrees.get(0).children.get(1).children.get(0).children.get(0).children.get(0).children;
		int numberOfCity1Parts = city1ParseTree.size();
		StringBuffer city1Name = new StringBuffer();
		for (int cityPartCounter = 0; cityPartCounter < numberOfCity1Parts; ++cityPartCounter) {
			city1Name.append(city1ParseTree.get(cityPartCounter).mCFProduction.getRhs()).append(Constants.SPACER);
		}
		
		//Extract the second city if present
		if (parseTrees.get(0).children.get(1).children.get(0).children.size() > 1) {
			ArrayList<ParseTree> city2ParseTree = parseTrees.get(0).children.get(1).children.get(0).children.get(1).children.get(1).children;
			int numberOfCity2Parts = city2ParseTree.size();
			returnValue.setFromCity(city1Name.toString().trim());
			StringBuffer city2Name = new StringBuffer();
			for (int cityPartCounter = 0; cityPartCounter < numberOfCity2Parts; ++cityPartCounter) {
				city2Name.append(city2ParseTree.get(cityPartCounter).mCFProduction.getRhs()).append(Constants.SPACER);
			}
			returnValue.setToCity(city2Name.toString().trim());
		} else {
			returnValue.setFromCity(null);
			returnValue.setToCity(city1Name.toString().trim());
		}
		
		//Find out the type of date present
		boolean travelDatePresent = parseTrees.get(0).children.get(1).children.size() > 1;

		if (travelDatePresent) {
			int travelDatePrefixes = parseTrees.get(0).children.get(1).children.get(1).children.size() - 1;
			String dateProduction = parseTrees.get(0).children.get(1).children.get(1).children.get(travelDatePrefixes).mCFProduction.getRhs();
			ArrayList<ParseTree> travelDateTree = parseTrees.get(0).children.get(1).children.get(1).children.get(travelDatePrefixes).children;
			if ("DAYOFWEEK".equals(dateProduction))  {
				returnValue.setDayOfWeekOfTravel(travelDateTree.get(0).mCFProduction.getRhs());
			} else if ("DAY".equals(dateProduction)) {
				returnValue.setDayNumberOfTravel(MonthDayToIntegerConverter.toIntegerDate(travelDateTree.get(0).mCFProduction.getRhs()));
			} else if ("MONTH DAY".equals(dateProduction)) {
				returnValue.setMonthOfTravel(travelDateTree.get(0).mCFProduction.getRhs());
				returnValue.setDayNumberOfTravel(MonthDayToIntegerConverter.toIntegerDate(travelDateTree.get(1).mCFProduction.getRhs()));
			}
		}
		return returnValue;
	}

	@Override
	public void initInstance(String production, ArrayList<ParseTree> parseTrees) {
		this.production = production;
		this.parseTrees = parseTrees;
	}

}