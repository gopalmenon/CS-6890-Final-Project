package menon.cs6890.FinalProject.Controller;

import java.util.Calendar;

public class TravelDateProcessor {
	
	private static String[] months = {"january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
	private static String[] weekdays = {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};

	public int getTravelDate(String travelMonth, int travelDay) {
		
		Calendar today = today();
		Calendar adjustedTravelDate = today();
		int travelMonthFromUser = getMonthNumber(travelMonth);
		int currentMonthNumber = today.get(Calendar.MONTH);
		int currentDayNumber = today.get(Calendar.DATE);
		
		if (currentMonthNumber == travelMonthFromUser) {
			
			if (travelDay >= currentDayNumber) {
				adjustedTravelDate.set(Calendar.DATE, travelDay);
			} else {
				adjustedTravelDate.set(Calendar.DATE, travelDay);
				adjustedTravelDate.set(Calendar.YEAR, today.get(Calendar.YEAR) + 1);
			}				
			
		} else if (currentMonthNumber < travelMonthFromUser) {
			
			adjustedTravelDate.set(Calendar.MONTH, travelMonthFromUser);
			adjustedTravelDate.set(Calendar.DATE, travelDay);
			
		} else {
			
			adjustedTravelDate.set(Calendar.YEAR, today.get(Calendar.YEAR) + 1);
			adjustedTravelDate.set(Calendar.MONTH, travelMonthFromUser);
			adjustedTravelDate.set(Calendar.DATE, travelDay);
			
		}
		
		return getDate(adjustedTravelDate);
	}

	public int getTravelDate(String travelWeekday) {
		
		Calendar today = today();
		Calendar adjustedTravelDate = today();

		int currentWeekday = today.get(Calendar.DAY_OF_WEEK) - 1;
		int travelWeekdayNumber = getWeekdayNumber(travelWeekday);
		
		if (travelWeekdayNumber >= currentWeekday) {
			int daysToAdd = travelWeekdayNumber - currentWeekday;	
			if (daysToAdd > 0) {
				adjustedTravelDate.add(Calendar.DATE, daysToAdd);
			}
		} else {
			int daysToAdd = currentWeekday - travelWeekdayNumber;	
			if (daysToAdd > 0) {
				daysToAdd = 7 - daysToAdd;
				adjustedTravelDate.add(Calendar.DATE, daysToAdd);
			}
			
		}
		
		return getDate(adjustedTravelDate);
		
	}
	

	public int getTravelDate(int travelDay) {
		
		Calendar today = today();
		Calendar adjustedTravelDate = today();
		
		int currentDay = today.get(Calendar.DATE);
		if (travelDay > currentDay) {
			adjustedTravelDate.set(Calendar.DATE, travelDay);
		} else if (travelDay < currentDay) {
			adjustedTravelDate.set(Calendar.DATE, travelDay);
			adjustedTravelDate.set(Calendar.MONTH, today.get(Calendar.MONTH) + 1);
		}
		
		return getDate(adjustedTravelDate);
		
	}
	
	private Calendar today() {
		return Calendar.getInstance();
	}

	private int getMonthNumber(String travelMonth) {
		
		String travelMonthInput = travelMonth.toLowerCase().trim();
		int monthCounter = 0;
		for (String month : months) {
			if (month.equals(travelMonthInput)) {
				return monthCounter;
			}
			++monthCounter;
		}
		
		System.err.println("Invalid month " + travelMonthInput + " in TravelDateProcessor.java");
		System.exit(0);
		return 0;
	}


	private int getWeekdayNumber(String travelWeekday) {
		
		String travelWeekdayInput = travelWeekday.toLowerCase().trim();
		int weekdayCounter = 0;
		for (String weekday : weekdays) {
			if (weekday.equals(travelWeekdayInput)) {
				return weekdayCounter;
			}
			++weekdayCounter;
		}
		
		System.err.println("Invalid weekday " + travelWeekdayInput + " in TravelDateProcessor.java");
		System.exit(0);
		return 0;
	}
	
	private int getDate(Calendar travelDate) {
		
		return travelDate.get(Calendar.YEAR) * 10000 + (travelDate.get(Calendar.MONTH) + 1) * 100 + travelDate.get(Calendar.DATE);
	}

}
