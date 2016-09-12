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


public class MatchedOrder {

	
	String id;
	String orderId1;
	String orderId2;
	String assetClass;
	
	public MatchedOrder(String orderId1, String orderId2, String assetClass) {
		super();
		this.orderId1 = orderId1;
		this.orderId2 = orderId2;
		this.assetClass = assetClass;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getOrderId1() {
		return orderId1;
	}
	public void setOrderId1(String orderId1) {
		this.orderId1 = orderId1;
	}
	
	
	public String getOrderId2() {
		return orderId2;
	}
	public void setOrderId2(String orderId2) {
		this.orderId2 = orderId2;
	}
	
	
	public String getAssetClass() {
		return assetClass;
	}
	public void setAssetClass(String assetClass) {
		this.assetClass = assetClass;
	}
	public MatchedOrder(String id, String orderId1, String orderId2,
			String assetClass) {
		super();
		this.id = id;
		this.orderId1 = orderId1;
		this.orderId2 = orderId2;
		this.assetClass = assetClass;
	}

	public MatchedOrder() {
		// TODO Auto-generated constructor stub
	}
	
}
