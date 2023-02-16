package com.example.demo.services;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class Q1and2Service {
	
	public String timeOfDay() {
		Calendar c = Calendar.getInstance();
		
		if (c.get(Calendar.HOUR_OF_DAY) < 12) {
			return "Morning";
		} else if (c.get(Calendar.HOUR_OF_DAY) < 16) {
			return "Afternoon";
		} else if (c.get(Calendar.HOUR_OF_DAY) < 20) {
			return "Evening";
		}
		
		return "Night";
	}

	public String weekendCheck() {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_WEEK);
		if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
			return ",it's the weekend!";
		} else return "";
	}
	
	public String pinGenerator() {
		Random rand = new Random();
		int[] pin = {0,0,0,0};
		
		for(int i = 0;i<pin.length;i++) {
			pin[i] = rand.nextInt(10);
		}
		
		return ", Your PIN is: " + Arrays.toString(pin);
	}
}
