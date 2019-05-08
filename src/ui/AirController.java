package ui;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Exceptions.NoFlightsExecption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Airport;
import model.Flight;

public class AirController {
	
	  @FXML
	    private TableView<Flight> Table;
	  
	  @FXML
	    private TableColumn<Flight,String> ciudad;

	    @FXML
	    private TableColumn<Flight,String> aerolinea;

	    @FXML
	    private TableColumn<Flight,String> hora;

	    @FXML
	    private TableColumn<Flight, Integer> puerta;

	    @FXML
	    private TableColumn<Flight,String> fecha;

	    @FXML
	    private TableColumn<Flight, Integer> id;
	    
	    private Airport a;
	    
	    private ArrayList<Flight[]> counter;
	    
	    private int timer = 0;
	    
	   private ObservableList<Flight> list;

	  public void initialize() {
		  ciudad.setCellValueFactory(new PropertyValueFactory<Flight,String>("city"));
		  aerolinea.setCellValueFactory(new PropertyValueFactory<Flight,String>("Airline"));
		  hora.setCellValueFactory(new PropertyValueFactory<Flight,String>("time"));
		  puerta.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("Gate"));
		  fecha.setCellValueFactory(new PropertyValueFactory<Flight,String>("Adate"));
		  id.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("number"));
		  a = new Airport();
		  counter = new ArrayList<Flight[]>();
		  try {
			a.Data();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  public void CreateFlight(ActionEvent e) {
		 int n =  Integer.parseInt(JOptionPane.showInputDialog("Digite el numero de vuelos que desea crear"));
		  a.CreateAllFlights(n);
		   list = FXCollections.observableArrayList();
		   Flight temp = a.getFirst();
		  for(int c = 0; temp != null&&c < 20 ;c++) {
			  list.add(temp);
			  temp = temp.getNext();
		  }
		  
	     Table.setItems(list);
	     Flight temp2 = a.getFirst();
	     while(temp2 != null) {
	    	 Flight[] f = new Flight[20];
	    for(int c = 0; c < 20 && temp2!= null; c++) {
	    	f[c] = temp2;
	    	temp2= temp2.getNext();
	    }
	    counter.add(f);
	     }
	  }
	  public void NextPage(ActionEvent e) {
		  System.out.println(timer + " " +counter.size());
		  if(timer > counter.size()) {
			  JOptionPane.showMessageDialog(null,"no hay mas vuelos");
		  }else {
			  list.clear();
			  timer ++;
		 for(int c = 0 ; c < counter.get(timer).length; c++) {
			 list.add(counter.get(timer)[c]);
	  }
		 Table.setItems(list);  
	  }
		  
	  }
	  
	  public void PrevPage() {
		 if(timer < -1) {
			 JOptionPane.showMessageDialog(null, "no hay mas vuelos");
		 }else {
			 list.clear();
			 timer--;
			 for(int c = 0 ; c < counter.get(timer).length; c++) {
				 list.add(counter.get(timer)[c]);
		  }
			 Table.setItems(list);  
		 }
	  }
	  
	  public void SortCity(ActionEvent e) {
		  try {
			a.sortCity();
			  list.clear();
			  Flight temp = a.getFirst();
			  for(int c = 0; temp != null&&c < 20 ;c++) {
				  list.add(temp);
				  temp = temp.getNext();
			  }		  
			  
			  Table.setItems(list);  
		} catch (NoFlightsExecption e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	  }
	  
	  public void SortAir(ActionEvent e) {
		  try {
			a.sortAirline();
			 list.clear();
			  Flight temp = a.getFirst();
			  for(int c = 0;temp != null && c < 20 ;c++) {
				  list.add(temp);
				  temp = temp.getNext();
		
			  }		  
			  Table.setItems(list);  
		} catch (NoFlightsExecption e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	  }
	  
	  public void SortDate(ActionEvent e) {
		  try {
			a.sortDate();
			 list.clear();
			  Flight temp = a.getFirst();
			  for(int c = 0; temp != null&&c < 20 ;c++) {
				  list.add(temp);
				  temp = temp.getNext();
			  }		  
			  
			  Table.setItems(list); 
		} catch (NoFlightsExecption e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		 
	  }
	  
	  public void SortGate(ActionEvent e) {
		  try {
			a.sortGate();
			 list.clear();
			  Flight temp = a.getFirst();
			  for(int c = 0; temp != null&&c < 20 ;c++) {
				  list.add(temp);
				  temp = temp.getNext();
			  }
			  
			  Table.setItems(list); 
		} catch (NoFlightsExecption e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		 
	  }
	  //falta
	 public void SortNumber(ActionEvent e) {
		 try {
			a.sortNumber();
			list.clear();
			  Flight temp = a.getFirst();
			  for(int c = 0; temp != null&&c < 20 ;c++) {
				  list.add(temp);
				  temp = temp.getNext();
			  }
			  
			  Table.setItems(list);  
		} catch (NoFlightsExecption e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		 
	 }
	 public void SortTime(ActionEvent e) {
		 try {
			a.sortTime();
			list.clear();
			  Flight temp = a.getFirst();
			  for(int c = 0; temp != null&&c < 20 ;c++) {
				  list.add(temp);
				  temp = temp.getNext();
			  }
			  
			  Table.setItems(list);  
		} catch (NoFlightsExecption e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
	 }
	 public void searchAir(ActionEvent e) {
		 try {
			a.SearchAir();
		} catch (NoFlightsExecption e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	 }
	 
	 public void searchCity(ActionEvent e) {
		 try {
			a.SearchCity();
		} catch (NoFlightsExecption e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	 }
	 
	 public void searchGate(ActionEvent e) {
		 try {
			a.SearchGate();
		} catch (NoFlightsExecption e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	 }
	 
	 public void searchDate(ActionEvent e) {
		 try {
			a.searchFecha();
		} catch (NoFlightsExecption e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	 }
	 
	 public void searchID(ActionEvent e) {
		 try {
			a.searchID();
		} catch (NoFlightsExecption e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	 }
	 public void searchTime(ActionEvent e) {
		 try {
			a.searchTime();
		} catch (NoFlightsExecption e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	 }
	 
	 
	 }
	 
