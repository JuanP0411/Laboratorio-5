
package model;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

public class Flight implements Comparable<Flight>{
	
	private String city;
	private String Airline;
	private int Gate;
	private int number;
	private String time;
	private Time ATime;
	private Flight next;
	private LocalDate Adate;

		//cambiar el string que esta cambiando las fechas
	public Flight(String city, String Airline, int gate, int number, Time time, LocalDate Adate) {
		this.city = city;
		this.Airline = Airline;
		this.Gate =  gate;
		this.number = number;
		SimpleDateFormat Date = new SimpleDateFormat("h:mm a");
		this.time = Date.format(time);
		this.ATime = time;
		this.Adate = Adate;
	}
	
	public String showFlight() {
		return city + " " + Airline + " " + Gate + " " + number + " " + time + " " + Adate ;
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

  
  public String getTime() {
	return time;
}
  
  public int getNumber() {
	return number;
}
	public int compareCity(Flight o1) {
		
		return this.getCity().compareToIgnoreCase(o1.getCity());
	}
	
	public void setATime(Time aTime) {
		ATime = aTime;
	}
	
	public Time getATime() {
		return ATime;
	}
	
	public Flight getNext() {
		return next;
	}
	
	public void setNext(Flight next) {
		this.next = next;
	}

	public void setGate(int gate) {
		Gate = gate;
	}
	
	public void setAirline(String airline) {
		Airline = airline;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public LocalDate getAdate() {
		return Adate;
	}
	
	public void setAdate(LocalDate adate) {
		Adate = adate;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
}
