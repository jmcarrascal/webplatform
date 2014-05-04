package ar.com.jmc.webplatform.base.model.message;

public class JsonResult {

	private boolean success;
	private boolean completed; // Indica si se llego al fin de la operacion en cuestion 
	private String result;
	
	public static final JsonResult SUCCESS = new JsonResult(true, true, null);
	
	public JsonResult(boolean success, boolean completed, String result) {
		if (success && !completed) {
			throw new IllegalStateException("El resultado no puede tener success==true cuando completed==false");
		}
		this.success = success;
		this.completed = completed;
		this.result = result;
	}
	
	public JsonResult(boolean success, String result){
		// Por default se asume que la operacion fue completada si y solo si tuvo exito
		this(success, success, result);
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
