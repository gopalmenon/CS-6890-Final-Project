package menon.cs6890.FinalProject.Controller.InfoExtractor;

public class MonthDayToIntegerConverter {
	
	public static int toIntegerDate(String dateString) {
		
		if ("first".equals(dateString)) {
			return 1;
		} else if ("second".equals(dateString)) {
			return 2;
		} else if ("third".equals(dateString)) {
			return 3;
		} else if ("fourth".equals(dateString)) {
			return 4;
		} else if ("fifth".equals(dateString)) {
			return 5;
		} else if ("sixth".equals(dateString)) {
			return 6;
		} else if ("seventh".equals(dateString)) {
			return 7;
		} else if ("eighth".equals(dateString)) {
			return 8;
		} else if ("ninth".equals(dateString)) {
			return 9;
		} else if ("tenth".equals(dateString)) {
			return 10;
		} else if ("eleventh".equals(dateString)) {
			return 11;
		} else if ("twelfth".equals(dateString)) {
			return 12;
		} else if ("thirteenth".equals(dateString)) {
			return 13;
		} else if ("fourteenth".equals(dateString)) {
			return 14;
		} else if ("fifteenth".equals(dateString)) {
			return 15;
		} else if ("sixteenth".equals(dateString)) {
			return 16;
		} else if ("seventeenth".equals(dateString)) {
			return 17;
		} else if ("eighteenth".equals(dateString)) {
			return 18;
		} else if ("nineteenth".equals(dateString)) {
			return 19;
		} else if ("twentieth".equals(dateString)) {
			return 20;
		} else if ("twenty-first".equals(dateString)) {
			return 21;
		} else if ("twenty-second".equals(dateString)) {
			return 22;
		} else if ("twenty-third".equals(dateString)) {
			return 23;
		} else if ("twenty-fourth".equals(dateString)) {
			return 24;
		} else if ("twenty-fifth".equals(dateString)) {
			return 25;
		} else if ("twenty-sixth".equals(dateString)) {
			return 26;
		} else if ("twenty-seventh".equals(dateString)) {
			return 27;
		} else if ("twenty-eighth".equals(dateString)) {
			return 28;
		} else if ("twenty-ninth".equals(dateString)) {
			return 29;
		} else if ("thirtieth".equals(dateString)) {
			return 30;
		} else if ("thirty-first".equals(dateString)) {
			return 31;
		} else if (dateString.endsWith("st")) {
			return Integer.parseInt(dateString.substring(0, dateString.indexOf("st")));
		} else if (dateString.endsWith("nd")) {
			return Integer.parseInt(dateString.substring(0, dateString.indexOf("nd")));
		} else if (dateString.endsWith("rd")) {
			return Integer.parseInt(dateString.substring(0, dateString.indexOf("rd")));
		} else if (dateString.endsWith("th")) {
			return Integer.parseInt(dateString.substring(0, dateString.indexOf("th")));
		} else {
			return Integer.parseInt(dateString);
		}
	}

}
