package menon.cs6890.FinalProject.Controller.InfoExtractor;

import java.util.ArrayList;

import menon.cs6890.FinalProject.Controller.FlightInformationBean;

import org.vkedco.nlp.earlyparser.ParseTree;

public class FlightOptionExtractor implements InformationExtractor {
	
	private String production;
	private ArrayList<ParseTree> parseTrees;
	
	@Override
	public FlightInformationBean extractInformation() {
		
		if (this.parseTrees == null || this.parseTrees.isEmpty()) {
			return null;
		}
		
		FlightInformationBean returnValue = new FlightInformationBean();
		
		ArrayList<ParseTree> chooseFlightParseTree = parseTrees.get(0).children.get(0).children;
		String flightOption = null;
		if (chooseFlightParseTree.size() == 2) {
			flightOption = chooseFlightParseTree.get(0).mCFProduction.getRhs();
		} else if (chooseFlightParseTree.size() == 3) {
			flightOption = chooseFlightParseTree.get(1).mCFProduction.getRhs();
		}
		
		if (flightOption != null) {
			returnValue.setFlightOption(MonthDayToIntegerConverter.toIntegerDate(flightOption.trim()));
		}
		
		return returnValue;
	}

	@Override
	public void initInstance(String productionName, ArrayList<ParseTree> parseTrees) {
		this.production = production;
		this.parseTrees = parseTrees;
	}

}
