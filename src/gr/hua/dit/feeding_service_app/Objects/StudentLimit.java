package gr.hua.dit.feeding_service_app.Objects;

public class StudentLimit {
	String oldLimit;
	String newLimit;
	public StudentLimit(String oldLimit, String newLimit) {
		super();
		this.oldLimit = oldLimit;
		this.newLimit = newLimit;
	}
	public StudentLimit() {
		super();
	}
	public String getOldLimit() {
		return oldLimit;
	}
	public void setOldLimit(String oldLimit) {
		this.oldLimit = oldLimit;
	}
	public String getNewLimit() {
		return newLimit;
	}
	public void setNewLimit(String newLimit) {
		this.newLimit = newLimit;
	}
	
	

}
