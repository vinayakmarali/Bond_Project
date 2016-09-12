package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


public class Bank {

	String bankId;
	String bankName;
	String streetName;
	String city;
	String state;
	
	
	
	public Bank() {
		super();
	}
	
	
	public Bank(String bankId, String bankName, String streetName, String city,
			String state) {
		super();
		this.bankId = bankId;
		this.bankName = bankName;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
	}


	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	

	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
