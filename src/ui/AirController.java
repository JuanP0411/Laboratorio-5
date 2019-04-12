package ui;

import java.io.IOException;
import java.sql.Time;

import javax.swing.JOptionPane;

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
	    
	    private int counter;
	    
	   private ObservableList<Flight> list;

	  public void initialize() {
		  ciudad.setCellValueFactory(new PropertyValueFactory<Flight,String>("city"));
		  aerolinea.setCellValueFactory(new PropertyValueFactory<Flight,String>("Airline"));
		  hora.setCellValueFactory(new PropertyValueFactory<Flight,String>("time"));
		  puerta.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("Gate"));
		  fecha.setCellValueFactory(new PropertyValueFactory<Flight,String>("date"));
		  id.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("number"));
		  a = new Airport();
		  try {
			a.Data();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  public void CreateFlight(ActionEvent e) {
		 int n =  Integer.parseInt(JOptionPane.showInputDialog("Digite el numero de vuelos que desea crear"));
		  a.CreateFlights(n);
		   list = FXCollections.observableArrayList();
		  for(int c = 0; c < 20 ;c++) {
			  list.add(a.getFlights()[c]);
		  }
		  
	     Table.setItems(list);
	  }
	  public void NextPage(ActionEvent e) {
		  counter = counter + 19;
		  System.out.println(counter);
		  list.clear();
		  boolean stop = false;
		  for(int c = 0; c < 20 && stop == false; c++) {
			try {
			  list.add(a.getFlights()[counter+c]);
			  System.out.println( c + "  " + (counter + c));
			}catch(ArrayIndexOutOfBoundsException m) {
				JOptionPane.showMessageDialog(null, "no hay mas vuelos");
				stop = true;
			}
		
		  }
		  if(stop == false) {
		  Table.setItems(list);
		  }
		 
	  }
	  
	  public void PrevPage() {
		  counter = counter - 19;
		 if(counter > -1) {
		  list.clear();
		  for(int c = 0; c < 20; c++) {
			  list.add(a.getFlights()[counter+c]);
		  }
		 
		  Table.setItems(list);
		 }else {
			 JOptionPane.showMessageDialog(null,"No hay mas vuelos previos");
		 }
	  }
	  
	  public void SortCity(ActionEvent e) {
		  a.sortCity();
		  list.clear();
		  for(int c = 0; c < a.getFlights().length;c++) {
			  System.out.println(a.getFlights()[c].showFlight());
			  list.add(a.getFlights()[c]);
	  }
		  
		  Table.setItems(list);  
	  }
	  
	  public void SortAir(ActionEvent e) {
		  a.sortAirline();
		  list.clear();
		  for(int c = 0; c < a.getFlights().length;c++) {
			  System.out.println(a.getFlights()[c].showFlight());
			  list.add(a.getFlights()[c]);
	  }
		  
		  Table.setItems(list);  
	  }
	  
	  public void SortDate(ActionEvent e) {
		  a.sortDate();
		  list.clear();
		  for(int c = 0; c < a.getFlights().length;c++) {
			  list.add(a.getFlights()[c]);
	  }
		  
		  Table.setItems(list); 
	  }
	  
	  public void SortGate(ActionEvent e) {
		  a.sortGate();
		  list.clear();
		  for(int c = 0; c < a.getFlights().length;c++) {
			  System.out.println(a.getFlights()[c].showFlight());
			  list.add(a.getFlights()[c]);
	  }
		  
		  Table.setItems(list); 
	  }
	  
	 public void SortNumber(ActionEvent e) {
		 a.sortNumber();
		 list.clear();
		  for(int c = 0; c < a.getFlights().length;c++) {
			  System.out.println(a.getFlights()[c].showFlight());
			  list.add(a.getFlights()[c]);
	  }
		  
		  Table.setItems(list);  
	 }
	 public void SortTime(ActionEvent e) {
		 a.sortTime();
		 list.clear();
		 
		 for(int c = 0; c < a.getFlights().length;c++) {
			  System.out.println(a.getFlights()[c].showFlight());
			  list.add(a.getFlights()[c]);
	  }
		  
		  Table.setItems(list);  
	 }
	 public void searchAir(ActionEvent e) {
		 a.SearchAir();
	 }
	 
	 public void searchCity(ActionEvent e) {
		 a.SearchCity();
	 }
	 
	 public void searchGate(ActionEvent e) {
		 a.SearchGate();
	 }
	 
	 public void searchDate(ActionEvent e) {
		 a.searchFecha();
	 }
	 
	 public void searchID(ActionEvent e) {
		 a.searchID();
	 }
	 public void searchTime(ActionEvent e) {
		 a.searchTime();
	 }
	 
	 
	 }
	 
