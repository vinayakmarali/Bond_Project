package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


public class DepositoryBank {

	String dpId;
	String dpName;
	String location;
	
	public DepositoryBank() {
		super();
	}

	public DepositoryBank(String dpName, String location) {
		super();
		this.dpName = dpName;
		this.location = location;
	}
	
	public String getDpId() {
		return dpId;
	}
	public void setDpId(String dpId) {
		this.dpId = dpId;
	}
	
	

	public String getDpName() {
		return dpName;
	}

	public void setDpName(String dpName) {
		this.dpName = dpName;
	}


	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public DepositoryBank(String dpId, String dpName, String location) {
		super();
		this.dpId = dpId;
		this.dpName = dpName;
		this.location = location;
	}


}
