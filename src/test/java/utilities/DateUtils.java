package utilities;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class DateUtils {

	public String dateParser(LocalDate date) {
//	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");  
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		String strDate = date.format(formatter);
		strDate = strDate.toUpperCase();
//	    System.out.println(strDate);
		return strDate;
	}

	public String dateParser(LocalDate date, String pattern) {
//	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");  
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String strDate = date.format(formatter);
//	    System.out.println(strDate);
		return strDate;
	}

	public String[] ifSameDay(int week) {
		String[] last = new String[week];
		LocalDate dt = LocalDate.now();

		for (int i = 0; i < week; i++) {
			LocalDate lastIth = dt.minusDays(7 * i);
			last[i] = dateParser(lastIth);
		}
		return last;
	}

	public LocalDate previousDay(int day) {
		LocalDate dt = LocalDate.now();
		LocalDate prevDay = dt.with(TemporalAdjusters.previous(DayOfWeek.of(day)));
		return prevDay;
	}

	public String[] previousDayOfWeeks(int day, int week) {

		Boolean sameDay = checkIfSameDay(day);
		String[] last = new String[week];

		if (sameDay) {
			last = ifSameDay(week);
		} else {
			LocalDate prevDay = previousDay(day);
			last[0] = dateParser(prevDay);
			for (int i = 1; i < week; i++) {
				LocalDate lastIth = prevDay.minusDays(7 * i);
				last[i] = dateParser(lastIth);
			}
		}
		return last;
	}

	public boolean checkIfSameDay(int day) {
		LocalDate dt = LocalDate.now();
		return (day == dt.getDayOfWeek().getValue());
	}

	public String todaysDate() {
		LocalDate dt = LocalDate.now();
//		01/17/2024
		return dateParser(dt, "MM/DD/yyyy");
	}

	public Boolean checkIfSaturday() {
		LocalDate dt = LocalDate.now();
		return ("SATURDAY" == dt.getDayOfWeek().toString());
	}

	public LocalDate previousSaturday(LocalDate date) {
		LocalDate prevDay = date.with(TemporalAdjusters.previous(DayOfWeek.SATURDAY));
		return prevDay;
	}
	

	public String[] last3Saturdays() {

		Boolean sameDay = checkIfSaturday();
		String[] last3 = new String[3];
		LocalDate date = LocalDate.now();

		if (sameDay) {
			for (int i = 0; i < 3; i++) {
				last3[i] = dateParser(date.minusDays(7 * i));
			}
		} else {
			for (int i = 0; i < 3; i++)
				last3[i] = dateParser(previousSaturday(date.minusDays(7 * i)));
		}

		return last3;

	}
	
	public String thisWeek() {
		String week = "";
		LocalDate date = LocalDate.now();
		if(checkIfSaturday()) {
			week = dateParser(date);
			week += " To ";
			week += dateParser(date.plusDays(6));
		}
		else {
			LocalDate prevSat = previousSaturday(date);
			week = dateParser(prevSat);
			week += " To ";
			week += dateParser(prevSat.plusDays(6));
		}
		return week;
	}
		

	public static void main(String[] args) {
		DateUtils du = new DateUtils();

		System.out.println(du.thisWeek());

	}
}
