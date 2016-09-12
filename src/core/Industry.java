package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


public class Industry {
	
	String industryId;
	String name;
	
	public Industry(String name) {
		super();
		this.name = name;
	}

	public String getIndustryId() {
		return industryId;
	}
	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Industry(String industryId, String name) {
		super();
		this.industryId = industryId;
		this.name = name;
	}
	
	public Industry() {
		// TODO Auto-generated constructor stub
	}
}
