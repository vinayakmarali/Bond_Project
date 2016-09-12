package core;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


public class Broker {
	
	
	String brokerId;
	String firstName;
	String lastName;
	String firmId;
	String bookId;
	String bankAccNo;
	String dpAccNo;
	

	public Broker() {
		super();
	}
	

	public Broker(String brokerId, String firstName, String lastName,
			String firmId, String bookId, String bankAccNo, String dpAccNo) {
		super();
		this.brokerId = brokerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.firmId = firmId;
		this.bookId = bookId;
		this.bankAccNo = bankAccNo;
		this.dpAccNo = dpAccNo;
	}

	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirmId() {
		return firmId;
	}
	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	public String getBankAccNo() {
		return bankAccNo;
	}
	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}
	
	public String getDpAccNo() {
		return dpAccNo;
	}
	public void setDpAccNo(String dpAccNo) {
		this.dpAccNo = dpAccNo;
	}
	
}
