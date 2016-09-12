package core;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


public class DepositoryAcc {
	
	
	String dpAccNo;
	String dpId;
	public DepositoryAcc(String dpAccNo, String dpId, String cusip) {
		super();
		this.dpAccNo = dpAccNo;
		this.dpId = dpId;
		this.cusip = cusip;
	}

	String cusip;
	
public DepositoryAcc(String dpId, String cusip) {
		super();
		this.dpId = dpId;
		this.cusip = cusip;
	}


	public DepositoryAcc(String dpAccNo) {
	super();
	this.dpAccNo = dpAccNo;
}
	public DepositoryAcc() {
		// TODO Auto-generated constructor stub
	}


	public String getDpAccNo() {
		return dpAccNo;
	}

	public void setDpAccNo(String dpAccNo) {
		this.dpAccNo = dpAccNo;
	}
	
	
	public String getDpId() {
		return dpId;
	}

	public void setDpId(String dpId) {
		this.dpId = dpId;
	}

	
public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

}
