package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



public class Subordination {
	
	String subordinationId;
	String description;
	
	
	
	public Subordination(String subordinationId, String description) {
		super();
		this.subordinationId = subordinationId;
		this.description = description;
	}

	public Subordination() {
		super();
	}

	public Subordination(String description) {
		super();
		this.description = description;
	}
	
	public String getSubordinationId() {
		return subordinationId;
	}
	public void setSubordinationId(String subordinationId) {
		this.subordinationId = subordinationId;
	}
	
	

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	


}
