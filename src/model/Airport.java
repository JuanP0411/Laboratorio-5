package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;

import Exceptions.NotOrderArray;

public class Airport {
	
	private String [] citys;
	private String [] airlines;
	private Flight [] flights;

	
       public Airport() {
    	   
	} 
	
	public void CreateFlights(int amount) {
		flights = new Flight[amount];
		final int milis = 24*60*60*1000;
		for(int c = 0; c < amount; c++) {
			Random rand = new Random();
			String city = citys[rand.nextInt(citys.length-1)];
			String air = airlines[rand.nextInt(airlines.length-1)];
			int number = rand.nextInt(100);
			int gate = rand.nextInt(100);
			Time time = new Time((long)rand.nextInt(milis));
			int day = rand.nextInt(30);
			int month = rand.nextInt(12);
			int year = (int) (Math.random()*(2019-2000));
			Flight f = new Flight(city,air,number,gate,time,year,month,day);
			flights[c] = f;
		}
	}
	
	public void Data() throws IOException {
	FileReader fr = new FileReader("Datos.txt");
	BufferedReader br = new BufferedReader(fr);
	String line = br.readLine();
	airlines = line.split(" ");
	String line2 = br.readLine();
	citys = line2.split(" ");
	
	}
	
	public Flight[] getFlights() {
		return flights;
	}
	
	public void sortAirline() {
		Arrays.sort(flights);
	}
	
	public void sortCity() {
		Arrays.sort(flights,flights[0]); 
	}
	
	public boolean OrderArray(int[] temp)throws NotOrderArray{
	   boolean decide = false;
	   int acum = 0;
	  if(temp[0] < temp[temp.length-1]) {
		  throw new NotOrderArray();
	  }
	  return decide;
	}
	
	public void sortGate() {
		for(int C = 0 ; C < flights.length; C++) {
			for(int J = 1; J < flights.length; J++) {
				if(flights[J-1].getGate() < flights[J].getGate()) {
					Flight temp = flights[J-1];
					flights[J-1] = flights[J];
					flights[J] = temp;
				}
			}
		}
	}
	
	public void sortNumber() {
	//insertion
		Flight temp;
		for(int i = 1; i < flights.length; i++) {
			for(int j = i; j > 0; j--) {
				if(flights[j].getNumber()<flights[j-1].getNumber()) {
					temp = flights[j-1];
					flights[j-1] = flights[j];
					flights[j] = temp;
				}
			}
		}
	}
	
	public void sortDate() {
	//selection
		for(int c = 0; c < flights.length -1; c++) {
			int min = c;
			for(int j = c +1; j < flights.length; j++) {
			    if(flights[j].getYear() < flights[min].getYear()) {
			    	min = j;
			    	Flight temp = flights[min];
					flights[min] = flights[c];
					flights[c] = temp;
			    }
			}
		}
		
	}
	public void sortTime() {
		System.out.println("entro");
		for(int c = 0; c < flights.length; c++) {
			for(int j = 1; j < flights.length-c; j++) {
				System.out.println(flights[j].getATime().compareTo(flights[j-1].getATime()));
				if(flights[j].getATime().compareTo(flights[j-1].getATime()) <= -1) {
					 Flight temp = flights[j-1];
					flights[j-1] = flights[j];
					flights[j] = temp;
				}
			}
		}
	}

	
	public void SearchAir() {
		String air = JOptionPane.showInputDialog("Introduzca el nombre de la aerolinea");
		String message = "";
		 for(int c = 0; c < flights.length; c++) {
			 if(flights[c].getAirline().equals(air)){
				 System.out.println("entro");
				 message += flights[c].showFlight() + "\n";
			 }
		 }
		 if(message.isEmpty() == true) {
			message = "No hay vuelos con esta aerolinea"; 
		 }
		 JOptionPane.showMessageDialog(null, message);
		}
	
	    public void SearchCity() {
	    	String air = JOptionPane.showInputDialog("Introduzca el nombre de la ciudad");
			String message = "";
			 for(int c = 0; c < flights.length; c++) {
				 if(flights[c].getCity().equals(air)){
					 message += flights[c].showFlight() + "\n";
				 }
			 }
			 if(message.isEmpty()== true) {
				 message = "no se pudo encontrar vuelos con eat ciudad";
			 }
			 JOptionPane.showMessageDialog(null, message);
			}
	    
	    
	    public void SearchGate() {
	        int number = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el puerto"));
	        int [] temp = new int[flights.length];
	        
	        for(int c = 0; c < flights.length; c++) {
	        	temp[c] = flights[c].getGate();
	        }
	        try {
				OrderArray(temp);
			} catch (NotOrderArray e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
	       int low = 0;
	        int high = temp.length-1;
	       int mid = (low + high)/2;

	       boolean decide = false;
	       while(low <= high && decide == false) {
	    	   if(temp[mid] > number) {
	    		   low = mid +1;
	    	   }else if (temp[mid] == number) {
	    		   decide = true;
	    		   JOptionPane.showMessageDialog(null, flights[mid].showFlight());
	    	   }else {
	    		   high = mid -1;
	    	   }
	    	   mid = (low + high)/2;
	    	  
	       }
	        if(low > high) {
	        	JOptionPane.showMessageDialog(null, "no se pudo encontrar el puerto");
	        }
	    }
	    
	    public void searchID() {
	    	
	    	 int number = Integer.parseInt(JOptionPane.showInputDialog("Introduzca la identificacion"));
		        int [] temp = new int[flights.length];
		        
		        for(int c = 0; c < flights.length; c++) {
		        	temp[c] = flights[c].getNumber();
		        }
		        try {
					OrderArray(temp);
				} catch (NotOrderArray e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
		       int low = 0;
		        int high = temp.length-1;
		       int mid = (low + high)/2;
		       
		       boolean decide = false;
		       while(low <= high && decide == false) {
		    	   if(temp[mid] > number) {
		    		   low = mid +1;
		    	   }else if (temp[mid] == number) {
		    		   decide = true;
		    		   JOptionPane.showMessageDialog(null, flights[mid].showFlight());
		    	   }else {
		    		   high = mid -1;
		    	   }
		    	   mid = (low + high)/2;
		    	
		       }
		        if(low > high) {
		        	JOptionPane.showMessageDialog(null, "no se pudo encontrar el numero");
		        }
	    	
	    }
	    
	    public void searchFecha() {
	    	int year = Integer.parseInt(JOptionPane.showInputDialog("inserte el año"));
	    	int month = Integer.parseInt(JOptionPane.showInputDialog("inserte el mes"));
	    	int day = Integer.parseInt(JOptionPane.showInputDialog("inserte el dia"));
	    	String message = "";
	    	for(int c = 0; c < flights.length; c++) {
	    		if(year == flights[c].getYear() && month == flights[c].getMonth() && day == flights[c].getDay()) {
	    			message += flights[c].showFlight();
	    		}
	    	}
	    	if(message.isEmpty() == true) {
	    		message = "no se pudo encontrar un vuelo con la fecha";
	    	}
	        JOptionPane.showMessageDialog(null, message);
	    }
	    
	    public void searchTime() {
	    	String message = "";
	    	String time = JOptionPane.showInputDialog("inserte la hora en el siguiente formato :" 
	    + "\n" + "H:min AM/PM");
	    	for(int c = 0; c < flights.length; c++) {
	    		if(time.equals(flights[c].getTime())) {
	    			message += flights[c].showFlight();
	    		}
	    	}
	    	if(message.isEmpty() == true) {
	    		message = "no se encontro un vuelo con esta fecha";
	    	}
	    	JOptionPane.showMessageDialog(null, message);
	    }
	
	}
	
