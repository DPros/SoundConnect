package com.soundconnect.Utils;

public class Calendar {

	private static java.util.Calendar calendar = java.util.Calendar.getInstance();
	
	public static long getCurrentTime(){
		return calendar.getTimeInMillis();
	}
}
