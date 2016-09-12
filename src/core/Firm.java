package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


public class Firm {
	String firmId;
	String firmName;
	
	public Firm() {
		super();
	}
	
	
	
	
	public Firm(String firmId, String firmName) {
		super();
		this.firmId = firmId;
		this.firmName = firmName;
	}
	public String getFirmId() {
		return firmId;
	}
	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}
	
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	
	
	

}
