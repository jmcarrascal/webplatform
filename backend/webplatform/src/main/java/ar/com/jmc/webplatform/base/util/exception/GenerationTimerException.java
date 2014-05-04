package ar.com.jmc.webplatform.base.util.exception;

public class GenerationTimerException extends Exception {

	private static final long serialVersionUID = 5541322902511080453L;
	
	private int intError;

	public GenerationTimerException(int intErrNo) {
		intError = intErrNo;
	}

	GenerationTimerException(String strMessage) {
		super(strMessage);
	}

	public String toString() {
		return "ApplicationException[" + intError + "]";
	}

}
