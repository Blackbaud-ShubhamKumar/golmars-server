package com.marse.martian.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static final String FORMATE = "dd MMMM yyyy  hh:mm:ss a";

	private static final String FORMATE_STRING = "dd-MM-yyyy";

	public static String calenderToString(Calendar c) {
		if (null == c)
			return "No Date";
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE);
		String dateStr = sdf.format(c.getTime());
		return dateStr;
	}

	public static Calendar stringToCalender(String date) {
		if (null == date)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_STRING);
		Date newDate = null;
		try {
			newDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (newDate == null) {
			return null;
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(newDate);
			return cal;
		}
	}

}
