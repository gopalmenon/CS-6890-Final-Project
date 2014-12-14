package menon.cs6890.FinalProject.Controller.InfoExtractor;

import java.util.ArrayList;

import menon.cs6890.FinalProject.Controller.Constants;
import menon.cs6890.FinalProject.Controller.FlightInformationBean;

import org.vkedco.nlp.earlyparser.ParseTree;

public class FromCityExtractor implements InformationExtractor {
	
	private String production;
	private ArrayList<ParseTree> parseTrees;
	
	@Override
	public FlightInformationBean extractInformation() {
		
		if (this.parseTrees == null || this.parseTrees.isEmpty()) {
			return null;
		}
		
		FlightInformationBean returnValue = new FlightInformationBean();
		
		//Find out the type of date present
		int cityPrefixes = parseTrees.get(0).children.get(0).children.size() - 1;
		ArrayList<ParseTree> cityParseTree = parseTrees.get(0).children.get(0).children.get(cityPrefixes).children;
		int numberOfCityParts = cityParseTree.size();
		StringBuffer cityName = new StringBuffer();
		for (int cityPartCounter = 0; cityPartCounter < numberOfCityParts; ++cityPartCounter) {
			cityName.append(cityParseTree.get(cityPartCounter).mCFProduction.getRhs()).append(Constants.SPACER);
		}
		returnValue.setFromCity(cityName.toString());
		return returnValue;
	}

	@Override
	public void initInstance(String productionName, ArrayList<ParseTree> parseTrees) {
		this.production = production;
		this.parseTrees = parseTrees;
	}

}
