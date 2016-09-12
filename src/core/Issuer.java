package core;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


public class Issuer {
	String issuerId;
	String issuerName;
	String industryId;
	
public Issuer(String issuerName, String industryId) {
		super();
		this.issuerName = issuerName;
		this.industryId = industryId;
	}

	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}


	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	

	public String getIndustry() {
		return industryId;
	}
	public void setIndustry(String industryId) {
		this.industryId = industryId;
	}

	public Issuer(String issuerId, String issuerName, String industryId) {
		super();
		this.issuerId = issuerId;
		this.issuerName = issuerName;
		this.industryId = industryId;
	}
	
	
	public Issuer() {
		// TODO Auto-generated constructor stub
	}

}
