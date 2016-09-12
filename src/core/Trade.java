package core;

import java.sql.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



public class Trade {
	String tradeId;
	String orderId;
	String firmId;
	String cusip;
	double price;
	double yield;
	String direction;
	double grossAmt;
	double accruedInterest;
	double commission;
	double tax;
	double netAmt;
	String tradeDate;
	String settlementDate;
	
	public Trade(String orderId, String firmId, String cusip,
			double price, double yield, String direction, double grossAmt,
			double accruedInterest, double commission, double tax,
			double netAmt, String tradeDate, String settlementDate) {
		super();
		this.orderId = orderId;
		this.firmId = firmId;
		this.cusip = cusip;
		this.price = price;
		this.yield = yield;
		this.direction = direction;
		this.grossAmt = grossAmt;
		this.accruedInterest = accruedInterest;
		this.commission = commission;
		this.tax = tax;
		this.netAmt = netAmt;
		this.tradeDate = tradeDate;
		this.settlementDate = settlementDate;
	}

	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	

	
	public String getOrder() {
		return orderId;
	}
	public void setOrder(String orderId) {
		this.orderId = orderId;
	}
	
	
	public String getFirm() {
		return firmId;
	}
	public void setFirm(String firmId) {
		this.firmId = firmId;
	}

	
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
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
	
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
	public double getGrossAmt() {
		return grossAmt;
	}
	public void setGrossAmt(double grossAmt) {
		this.grossAmt = grossAmt;
	}
	
	
	public double getAccruedInterest() {
		return accruedInterest;
	}
	public void setAccruedInterest(double accruedInterest) {
		this.accruedInterest = accruedInterest;
	}
	
	
	
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	
	
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	
	
	
	public double getNetAmt() {
		return netAmt;
	}
	public void setNetAmt(double netAmt) {
		this.netAmt = netAmt;
	}
	
	
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		tradeDate = tradeDate;
	}
	
	
	public String getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		settlementDate = settlementDate;
	}
	public Trade(String tradeId, String orderId, String firmId, String cusip,
			double price, double yield, String direction, double grossAmt,
			double accruedInterest, double commission, double tax,
			double netAmt, String tradeDate, String settlementDate) {
		super();
		this.tradeId = tradeId;
		this.orderId = orderId;
		this.firmId = firmId;
		this.cusip = cusip;
		this.price = price;
		this.yield = yield;
		this.direction = direction;
		this.grossAmt = grossAmt;
		this.accruedInterest = accruedInterest;
		this.commission = commission;
		this.tax = tax;
		this.netAmt = netAmt;
		this.tradeDate = tradeDate;
		this.settlementDate = settlementDate;
	}
	
	
	public Trade() {
		// TODO Auto-generated constructor stub
	}
	
}
