package com.runHani.demo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestEct {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 2); 
		System.err.println(sdf.format(cal.getTime()));
		cal.add(Calendar.DATE, 1);
		System.err.println(sdf.format(cal.getTime()));
		
	}
}
