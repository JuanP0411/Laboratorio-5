package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.JOptionPane;

import Exceptions.NoFlightsExecption;
import Exceptions.NotOrderArray;

public class Airport implements Comparator<Flight>{
	
	private String [] citys;
	private String [] airlines;
	private Flight first;

	
       public Airport() {
    	   
	} 
	
	public Flight Create() {
		int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
		final int milis = 24*60*60*1000;
		Random rand = new Random();
		String city = citys[rand.nextInt(citys.length-1)];
		String air = airlines[rand.nextInt(airlines.length-1)];
		int number = rand.nextInt(100);
		int gate = rand.nextInt(100);
		Time time = new Time((long)rand.nextInt(milis));
		long randomDay = minDay + rand.nextInt(maxDay - minDay);
		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		Flight f = new Flight(city,air,number,gate,time,randomDate);
		return f;
	}
	
	public void addFlight() {
		Flight f = Create();
		if(first == null) {
			first = f;
		}else {
			Flight temp = first;
			while(temp.getNext() != null) {
			temp = temp.getNext();
			}
			temp.setNext(f);
			}
	
	}
	
	public void CreateAllFlights(int amount) {
		first = null;
		for(int c = 0; c < amount; c++) {
			addFlight();
		}
	}
	
	public String ShowAllFlight() {
		String report = "";
		Flight temp = first;
		report += temp.showFlight() + "\n";
		while(temp.getNext() != null) {
			report += temp.getNext().showFlight() + "\n";
			temp = temp.getNext();
		}
		return report;
	}
	
	public void Data() throws IOException {
	FileReader fr = new FileReader("Datos.txt");
	BufferedReader br = new BufferedReader(fr);
	String line = br.readLine();
	citys = line.split(" ");
	String line2 = br.readLine();
	airlines = line2.split(" ");
	}

	
	public void sortAirline() throws NoFlightsExecption {
		if(first== null) {
			throw new NoFlightsExecption();
		}
		Flight current = first;
	    while (current != null) {

	        Flight second = current.getNext();
	        while (second != null) {
	            if (current.compareTo(second) <= -1) {
	                String tmp = current.getAirline();
	                current.setAirline(second.getAirline());
	                second.setAirline(tmp);
	            }
	            second = second.getNext();
	        }
	        current = current.getNext();
	    }

	}
	
	public void sortCity() throws NoFlightsExecption {
		if(first== null) {
			throw new NoFlightsExecption();
		}
		
		Flight current = first;
	    while (current.getNext() != null) {

	        Flight second = current.getNext();
	        while (second != null) {
	            if (current.compareCity(second) <= -1) {
	                String tmp = current.getCity();
	                current.setCity(second.getCity());
	                second.setCity(tmp);
	                
	            }
	            second = second.getNext();
	        }
	        current = current.getNext();
	    }

		
	}
	
	
	public void sortGate() throws NoFlightsExecption {
		if(first== null) {
			throw new NoFlightsExecption();
		}
		Flight current = first;
	    while (current.getNext() != null) {

	        Flight second = current.getNext();
	        while (second.getNext() != null) {

	            if (current.getGate() > second.getGate()) {
	                int tmp = current.getGate();
	                current.setGate(second.getGate());;
	                second.setGate(tmp);
	            }
	            second = second.getNext();
	        }
	        current = current.getNext();
	    }
	}
	
	public void sortNumber() throws NoFlightsExecption {
		//insertion me gano :(
		if(first== null) {
			throw new NoFlightsExecption();
		}
		Flight temp = first;
		Flight counter = temp.getNext();
		while(counter.getNext() != null) {
			
			while(temp.getNext()!= counter) {

			if(temp.getNumber() < counter.getNumber()) {
				  int tmp = counter.getNumber();
	                counter.setNumber(temp.getNumber());;
	                temp.setNumber(tmp);
			}

			temp = temp.getNext();
			}
			first = temp;
			counter = counter.getNext();
		}
	}
	
	
	public void sortDate() throws NoFlightsExecption {
	//selection
		if(first== null) {
			throw new NoFlightsExecption();
		}
		 Flight temp = first; 
		 
		    while (temp != null) { 
		        Flight min = temp; 
		        Flight r = temp.getNext(); 

		        while (r!= null) { 
		            if (min.getAdate().getLong(ChronoField.EPOCH_DAY) < (r.getAdate().getLong(ChronoField.EPOCH_DAY))) {
		                min = r; 
		            }
		  
		            r = r.getNext(); 
		        } 
		 
		        LocalDate x = temp.getAdate(); 
		        temp.setAdate(min.getAdate()); 
		        min.setAdate(x);
		        temp = temp.getNext(); 
		    } 
	}
	
	public void sortTime() throws NoFlightsExecption {
		
		if(first== null) {
			throw new NoFlightsExecption();
		}
		Flight current = first;
	    while (current.getNext() != null) {

	        Flight second = current.getNext();
	        while (second.getNext() != null) {
	            if (current.getAdate().compareTo(second.getAdate())<= -1) {
	                String tmp = current.getTime();
	                current.setTime(second.getTime());
	                second.setTime(tmp);
	            }
	            second = second.getNext();
	        }
	        current = current.getNext();
	    }
		
	}

	//Searching
	
	 
	public void SearchAir() throws NoFlightsExecption {
		if(first== null) {
			throw new NoFlightsExecption();
		}
		
		String air = JOptionPane.showInputDialog("Introduzca el nombre de la aerolinea");
		String message = "";
		Flight temp = first;
		 while(temp.getNext() != null) {
			 if(temp.getAirline().equals(air)){
				 message += temp.showFlight();
			 }
			 temp = temp.getNext();
		 }
		 if(message.isEmpty() == true) {
			message = "No hay vuelos con esta aerolinea"; 
		 }
		 JOptionPane.showMessageDialog(null, message);
		}
	
	
	
	
	    public void SearchCity() throws NoFlightsExecption {
	    	if(first== null) {
				throw new NoFlightsExecption();
			}
	    	String air = JOptionPane.showInputDialog("Introduzca el nombre de la ciudad");
			String message = "";
			Flight temp = first;
			 while(temp.getNext() != null) {
				 if(temp.getCity().equals(air)){
					 System.out.println("entro");
					 message += temp.showFlight();
				 }
				 temp = temp.getNext();
			 }
			 if(message.isEmpty()== true) {
				 message = "no se pudo encontrar vuelos con eat ciudad";
			 }
			 JOptionPane.showMessageDialog(null, message);
			}
	    
	    
	    
	    
	    public void SearchGate() throws NoFlightsExecption {
	    	if(first== null) {
				throw new NoFlightsExecption();
			}
	    	int air = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el numero de la puerta de salida"));
			String message = "";
			Flight temp = first;
			 while(temp.getNext() != null) {
				 if(temp.getGate() == air){
					 System.out.println("entro");
					 message += temp.showFlight();
				 }
				 temp = temp.getNext();
			 }
			 if(message.isEmpty()== true) {
				 message = "no se pudo encontrar vuelos con eat ciudad";
			 }
			 JOptionPane.showMessageDialog(null, message);
	    }
	    
	    public void searchID() throws NoFlightsExecption {
	    	if(first== null) {
				throw new NoFlightsExecption();
			}
	    	int id = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el nombre de la ciudad"));
			String message = "";
			Flight temp = first;
			 while(temp.getNext() != null) {
				 if(temp.getNumber() == id){
					 System.out.println("entro");
					 message += temp.showFlight();
				 }
				 temp = temp.getNext();
			 }
			 if(message.isEmpty()== true) {
				 message = "no se pudo encontrar vuelos con eat ciudad";
			 }
			 JOptionPane.showMessageDialog(null, message);
	    	
	    }
	    
	    public void searchFecha() throws NoFlightsExecption {
	    	if(first== null) {
				throw new NoFlightsExecption();
			}
	    	int year = Integer.parseInt(JOptionPane.showInputDialog("inserte el año"));
	    	int month = Integer.parseInt(JOptionPane.showInputDialog("inserte el mes"));
	    	int day = Integer.parseInt(JOptionPane.showInputDialog("inserte el dia"));
	    	String message = "";
	    	Flight temp = first;
			 while(temp.getNext() != null) {
				 int tyear = temp.getAdate().getYear();
				 int tmonth = temp.getAdate().getMonthValue();
				 int tday = temp.getAdate().getDayOfYear();
				 if(year == tyear && month == tmonth && day == tday ){
					 message += temp.showFlight();
				 }
				 temp = temp.getNext();
			 }
	    	if(message.isEmpty() == true) {
	    		message = "no se pudo encontrar un vuelo con la fecha";
	    	}
	        JOptionPane.showMessageDialog(null, message);
	    }
	    
	    public void searchTime() throws NoFlightsExecption {
	    	if(first== null) {
				throw new NoFlightsExecption();
			}
	    	String message = "";
	    	String time = JOptionPane.showInputDialog("inserte la hora en el siguiente formato :" 
	    + "\n" + "H:min AM/PM");
	    	Flight temp = first;
			 while(temp.getNext() != null) {
				 if(temp.getTime().equals(time)){
					 message += temp.showFlight();
				 }
				 temp = temp.getNext();
			 }
	    	if(message.isEmpty() == true) {
	    		message = "no se encontro un vuelo con esta fecha";
	    	}
	    	JOptionPane.showMessageDialog(null, message);
	    }
	    
	    public Flight getFirst() {
			return first;
		}

		@Override
		public int compare(Flight o1, Flight o2) {
		if(o1.getNumber() < o2.getNumber()) {
			return -1;
		}else if(o1.getNumber() < o2.getNumber()) {
			return 1;
		}else {
			return 0;
		}
		}
	    
	
	}
	
