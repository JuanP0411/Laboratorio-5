package Exceptions;

@SuppressWarnings("serial")
public class NotOrderArray extends Exception {
    public NotOrderArray() {
	super("no se pude hacer esta operacino debido a que el arreglo no esta ordenado");
}
}
