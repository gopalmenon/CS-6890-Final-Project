package menon.cs6890.FinalProject.Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader {
	
	private String textFilePath;
	private BufferedReader textFileReader;
	
	public TextFileReader(String textFilePath) throws FileNotFoundException {
		this.textFilePath = textFilePath;
		this.textFileReader = new BufferedReader(new FileReader(textFilePath));
	}
	
	public List<String> getLinesFromTextFile() {
		
		String lines = null;
		List<String> returnValue = new ArrayList<String>();
		try {
			lines = this.textFileReader.readLine();
			while(lines != null) {
				if (lines.trim().length() > 0) {
					returnValue.add(lines.trim());
				}
				lines = textFileReader.readLine();
			}
			textFileReader.close();
		} catch (FileNotFoundException e) {
			System.err.println("File " + this.textFilePath + " was not found.");
			e.printStackTrace();
			return null;
		}
		catch (IOException e) {
			System.err.println("IOException thrown while reading file " + this.textFilePath + ".");
			e.printStackTrace();
			return null;
		}
		
		return returnValue;
	}
}
