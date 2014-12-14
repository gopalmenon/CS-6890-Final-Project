package menon.cs6890.FinalProject.Controller.InfoExtractor;

import java.util.ArrayList;

import org.vkedco.nlp.earlyparser.ParseTree;

import menon.cs6890.FinalProject.Controller.Constants;

public class InformationExtractorFactory {

	public static InformationExtractor getInformationExtractorInstance(String production, ArrayList<ParseTree> parseTrees) {
		
		InformationExtractor returnValue = null;
		
		String instanceClassName = Constants.INFO_EXTRACTOR_MAP.get(production);
		if (instanceClassName != null) {
			try {
				returnValue = (InformationExtractor) (Class.forName(instanceClassName).newInstance());
				returnValue.initInstance(production, parseTrees);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		
		return returnValue;
		
	}
}
