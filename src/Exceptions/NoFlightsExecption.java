package Exceptions;

@SuppressWarnings("serial")
public class NoFlightsExecption extends Exception{

	public NoFlightsExecption() {
	super("No se puede hacer esta operacion debido a que no hay vuelos existentes ");
	}
}
