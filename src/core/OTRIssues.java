package core;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


public class OTRIssues {
	int id;
	String cusip;
	String issuerId;
	double coupon;
	String maturity;
	double bid;
	double ask;
	double bid_prev;
	double ask_prev;
	double bid_yield;
	double ask_yield;
	double bid_prev_yield;
	double ask_prev_yield;


	public OTRIssues(int id, String cusip, double bid, double ask, double bid_yield, double ask_yield, double coupon) {
		super();
		this.id = id;
		this.cusip = cusip;
		this.bid = bid;
		this.ask = ask;
		this.bid_yield = bid_yield;
		this.ask_yield = ask_yield;
		this.coupon = coupon;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
public OTRIssues(int id, String cusip, String issuerId, double coupon,
			String maturity, double bid, double ask, double bid_prev,
			double ask_prev, double bid_yield, double ask_yield) {
		super();
		this.id = id;
		this.cusip = cusip;
		this.issuerId = issuerId;
		this.coupon = coupon;
		this.maturity = maturity;
		this.bid = bid;
		this.ask = ask;
		this.bid_prev = bid_prev;
		this.ask_prev = ask_prev;
		this.bid_yield = bid_yield;
		this.ask_yield = ask_yield;
		
	}




	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	
	
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	
	
	public double getCoupon() {
		return coupon;
	}
	public void setCoupon(double coupon) {
		this.coupon = coupon;
	}
	
	
	public String getMaturity() {
		return maturity;
	}
	public void setMaturity(String maturity) {
		this.maturity = maturity;
	}
	
	
	public double getBid() {
		return bid;
	}
	public void setBid(double bid) {
		this.bid = bid;
	}
	
public double getAsk() {
		return ask;
	}
	public void setAsk(double ask) {
		this.ask = ask;
	}
	
	public double getBid_prev() {
		return bid_prev;
	}




	public void setBid_prev(double bid_prev) {
		this.bid_prev = bid_prev;
	}




	public double getAsk_prev() {
		return ask_prev;
	}




	public void setAsk_prev(double ask_prev) {
		this.ask_prev = ask_prev;
	}




	public double getBid_yield() {
		return bid_yield;
	}




	public void setBid_yield(double bid_yield) {
		this.bid_yield = bid_yield;
	}




	public double getAsk_yield() {
		return ask_yield;
	}




	public void setAsk_yield(double ask_yield) {
		this.ask_yield = ask_yield;
	}




	public double getBid_prev_yield() {
		return bid_prev_yield;
	}




	public void setBid_prev_yield(double bid_prev_yield) {
		this.bid_prev_yield = bid_prev_yield;
	}




	public double getAsk_prev_yield() {
		return ask_prev_yield;
	}




	public void setAsk_prev_yield(double ask_prev_yield) {
		this.ask_prev_yield = ask_prev_yield;
	}




	public OTRIssues() {
		// TODO Auto-generated constructor stub
	} 
	 


}
