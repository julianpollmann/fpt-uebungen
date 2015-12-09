package exceptions;

public class IdOverflowException extends Exception {

	public IdOverflowException() {
	}

	public IdOverflowException(String message) {
		super(message);
	}

	public IdOverflowException(Throwable cause) {
		super(cause);
	}

	public IdOverflowException(String message, Throwable cause) {
		super(message, cause);
	}

	public IdOverflowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
