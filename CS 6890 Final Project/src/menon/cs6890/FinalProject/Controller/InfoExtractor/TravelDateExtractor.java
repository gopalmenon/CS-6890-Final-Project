package menon.cs6890.FinalProject.Controller.InfoExtractor;

import java.util.ArrayList;

import menon.cs6890.FinalProject.Controller.FlightInformationBean;

import org.vkedco.nlp.earlyparser.ParseTree;

public class TravelDateExtractor implements InformationExtractor {
	
	private String production;
	private ArrayList<ParseTree> parseTrees;
	
	@Override
	public FlightInformationBean extractInformation() {
		
		if (this.parseTrees == null || this.parseTrees.isEmpty()) {
			return null;
		}
		
		FlightInformationBean returnValue = new FlightInformationBean();
		
		//Find out the type of date present
		int travelDatePrefixes = parseTrees.get(0).children.get(0).children.size() - 1;
		String dateProduction = parseTrees.get(0).children.get(0).children.get(travelDatePrefixes).mCFProduction.getRhs();
		ArrayList<ParseTree> travelDateTree = parseTrees.get(0).children.get(0).children.get(travelDatePrefixes).children;
		if ("DAYOFWEEK".equals(dateProduction))  {
			returnValue.setDayOfWeekOfTravel(travelDateTree.get(0).mCFProduction.getRhs());
		} else if ("DAY".equals(dateProduction)) {
			returnValue.setDayNumberOfTravel(MonthDayToIntegerConverter.toIntegerDate(travelDateTree.get(0).mCFProduction.getRhs()));
		} else if ("MONTH DAY".equals(dateProduction)) {
			returnValue.setMonthOfTravel(travelDateTree.get(0).mCFProduction.getRhs());
			returnValue.setDayNumberOfTravel(MonthDayToIntegerConverter.toIntegerDate(travelDateTree.get(1).mCFProduction.getRhs()));
		}
		
		return returnValue;
	}

	@Override
	public void initInstance(String productionName, ArrayList<ParseTree> parseTrees) {
		this.production = production;
		this.parseTrees = parseTrees;
	}

}
