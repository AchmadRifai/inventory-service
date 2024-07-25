package achmad.rifai.inventory.exceptions;

public class InsufficientException extends RuntimeException {

	private static final long serialVersionUID = 3205688295490037473L;

	public InsufficientException(String message) {
		super(message);
	}

}
