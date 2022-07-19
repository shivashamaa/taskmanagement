package com.tryit.services.taskmanagement.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CalenderOperation {

	public Date getCurrentDateByTimezone(String timeZone,String format) {
		SimpleDateFormat currentDate = new SimpleDateFormat(format);
		if(timeZone!=null && !timeZone.equalsIgnoreCase("")){
			currentDate.setTimeZone(TimeZone.getTimeZone(timeZone));
		}
		String date = currentDate.format(new Date());
		try {
			return currentDate.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Date getDefaultCurrentDateByTimezone(String timeZone) {
		SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		if(timeZone!=null && !timeZone.equalsIgnoreCase("")){
			currentDate.setTimeZone(TimeZone.getTimeZone(timeZone));
		}
		String date = currentDate.format(new Date());
		try {
			return currentDate.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Date convetStringToDate(String stringDate, String format) {
		Date date;
		if(stringDate != null ) {
			try {
				date = new SimpleDateFormat(format).parse(stringDate);
			} catch (ParseException e) {
				return null;
			}
		}else {
			return null;
		}
		return date;
	}
	
	public String convertDateFormartString(Date date, String format) {
		if(date!=null && format!=null) {
			try {
				 SimpleDateFormat formatter = new SimpleDateFormat(format);  
				 String finalDate = formatter.format(date); 
				 return finalDate;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}
	
	public String convertDateFormat(String date,String format,String conFormat){
		//System.out.println("Before Converted Date time : "+date);
		if(date!=null && format!=null){
			DateFormat realFormat = new SimpleDateFormat(format);
			DateFormat conFormat1 = new SimpleDateFormat(conFormat);
			try {
				Date current = realFormat.parse(date);
				return conFormat1.format(current);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
