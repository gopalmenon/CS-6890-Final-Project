package menon.cs6890.FinalProject.Controller.InfoExtractor;

import java.util.ArrayList;

import org.vkedco.nlp.earlyparser.ParseTree;

import menon.cs6890.FinalProject.Controller.FlightInformationBean;

public interface InformationExtractor {
	
	public FlightInformationBean extractInformation();
	
	public void initInstance(String productionName, ArrayList<ParseTree> parseTrees);

}
