package core;

import java.sql.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



public class Order {
	
	String orderId;
	String brokerId; 
	String cusip;
	String direction;
	double coupon;
	double maturity;
	double notional;
	double price;
	double yield;
	String status;
	String DateTime;
	

	
	public Order(String orderId, String brokerId, String cusip,
			String direction, double coupon, double maturity, double notional,
			double price, double yield, String status, String dateTime) {
		super();
		this.orderId = orderId;
		this.brokerId = brokerId;
		this.cusip = cusip;
		this.direction = direction;
		this.coupon = coupon;
		this.maturity = maturity;
		this.notional = notional;
		this.price = price;
		this.yield = yield;
		this.status = status;
		DateTime = dateTime;
	}
	public Order(String brokerId,  String cusip,
			String direction, double coupon, double maturity, double notional,
			double price, double yield, String status, String dateTime) {
		super();
		this.brokerId = brokerId;
		
		this.cusip = cusip;
		this.direction = direction;
		this.coupon = coupon;
		this.maturity = maturity;
		this.notional = notional;
		this.price = price;
		this.yield = yield;
		this.status = status;
		DateTime = dateTime;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	

	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}


	public String getCusip() {
		return cusip;
	}
	
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
	public double getCoupon() {
		return coupon;
	}
	public void setCoupon(double coupon) {
		this.coupon = coupon;
	}
	
	
	public double getMaturity() {
		return maturity;
	}
	public void setMaturity(double maturity) {
		this.maturity = maturity;
	}
	
	public double getNotional() {
		return notional;
	}
	public void setNotional(double notional) {
		this.notional = notional;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public double getYield() {
		return yield;
	}
	public void setYield(double yield) {
		this.yield = yield;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}

    
    public Order() {
		// TODO Auto-generated constructor stub
	}
    
    
}
