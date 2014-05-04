package ar.com.jmc.webplatform.exception;

public class OISSException extends Exception {
	
	private static final long serialVersionUID = -2478981218180278255L;
	
	private int codigoError;
	
	public OISSException(int codigoError, String message) {
		super(message);
		this.codigoError = codigoError;
	}
	
	public int getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(int codigoError) {
		this.codigoError = codigoError;
	}
	
}
