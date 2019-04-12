
package model;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class Flight implements Comparable<Flight>, Comparator<Flight>{
	
	private String city;
	private String Airline;
	private int Gate;
	private int number;
	private String time;
	private Time ATime;
	private String date;
	private int year;
	private int month;
	private int day;
		
	public Flight(String city, String Airline, int gate, int number, Time time, int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
		date = year + "/" + month + "/" + day;
		this.city = city;
		this.Airline = Airline;
		this.Gate =  gate;
		this.number = number;
		SimpleDateFormat Date = new SimpleDateFormat("h:mm a");
		this.time = Date.format(time);
		this.ATime = time;
	}
	
	public String showFlight() {
		return city + " " + Airline + " " + Gate + " " + number + " " + time;
	}

	@Override
	public int compareTo(Flight f) {
		return (this.getAirline().compareToIgnoreCase(f.getAirline()));
	}
	public String getAirline() {
		return Airline;
	}
	
   public String getCity() {
	return city;
  }
  public int getGate() {
	return Gate;
}
  
  public String getDate() {
	return date;
}
  
  public String getTime() {
	return time;
}
  
  public int getNumber() {
	return number;
}
	@Override
	public int compare(Flight o1, Flight o2) {
		
		return o1.getCity().compareToIgnoreCase(o2.getCity());
	}
	
	public void setATime(Time aTime) {
		ATime = aTime;
	}
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
	
	public Time getATime() {
		return ATime;
	}
	
	
}
