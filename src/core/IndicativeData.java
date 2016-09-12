package core;

import java.sql.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

public class IndicativeData {

	String cusip;
	String isin;
	int issuerId;
	String ticker;
	String type;
	String IssueDate;
	String maturityDate;
	double tenor;
    double coupon;
	int subordination;
	String moodys; 
	String snp;
	String Callable;
	String Putable;
	String CallDate1;
	String CallDate2;
	String CallDate3;
	String PutDate;
	String FirstCouponDate;
	String NextCouponDate;
	String LastCouponDate;
	double YTM;
	double YTW;

	
	
 public IndicativeData(String cusip, String isin, int issuerId,
			String ticker, String type, String issueDate, String maturityDate,
			double tenor, double coupon, int subordination, String moodys,
			String snp, String callable, String putable, String callDate1,
			String callDate2, String callDate3, String putDate,
			String firstCouponDate, String nextCouponDate,
			String lastCouponDate, double yTM, double yTW) {
		super();
		this.cusip = cusip;
		this.isin = isin;
		this.issuerId = issuerId;
		this.ticker = ticker;
		this.type = type;
		IssueDate = issueDate;
		this.maturityDate = maturityDate;
		this.tenor = tenor;
		this.coupon = coupon;
		this.subordination = subordination;
		this.moodys = moodys;
		this.snp = snp;
		Callable = callable;
		Putable = putable;
		CallDate1 = callDate1;
		CallDate2 = callDate2;
		CallDate3 = callDate3;
		PutDate = putDate;
		FirstCouponDate = firstCouponDate;
		NextCouponDate = nextCouponDate;
		LastCouponDate = lastCouponDate;
		YTM = yTM;
		YTW = yTW;
	}


 
 
 

public String getCusip() {
	return cusip;
}






public void setCusip(String cusip) {
	this.cusip = cusip;
}






public String getIsin() {
	return isin;
}






public void setIsin(String isin) {
	this.isin = isin;
}






public int getIssuerId() {
	return issuerId;
}






public void setIssuerId(int issuerId) {
	this.issuerId = issuerId;
}






public String getTicker() {
	return ticker;
}






public void setTicker(String ticker) {
	this.ticker = ticker;
}






public String getType() {
	return type;
}






public void setType(String type) {
	this.type = type;
}






public String getIssueDate() {
	return IssueDate;
}






public void setIssueDate(String issueDate) {
	IssueDate = issueDate;
}






public String getMaturityDate() {
	return maturityDate;
}






public void setMaturityDate(String maturityDate) {
	this.maturityDate = maturityDate;
}






public double getTenor() {
	return tenor;
}






public void setTenor(double tenor) {
	this.tenor = tenor;
}






public double getCoupon() {
	return coupon;
}






public void setCoupon(double coupon) {
	this.coupon = coupon;
}






public int getSubordination() {
	return subordination;
}






public void setSubordination(int subordination) {
	this.subordination = subordination;
}






public String getMoodys() {
	return moodys;
}






public void setMoodys(String moodys) {
	this.moodys = moodys;
}






public String getSnp() {
	return snp;
}






public void setSnp(String snp) {
	this.snp = snp;
}






public String getCallable() {
	return Callable;
}






public void setCallable(String callable) {
	Callable = callable;
}






public String getPutable() {
	return Putable;
}






public void setPutable(String putable) {
	Putable = putable;
}






public String getCallDate1() {
	return CallDate1;
}






public void setCallDate1(String callDate1) {
	CallDate1 = callDate1;
}






public String getCallDate2() {
	return CallDate2;
}






public void setCallDate2(String callDate2) {
	CallDate2 = callDate2;
}






public String getCallDate3() {
	return CallDate3;
}






public void setCallDate3(String callDate3) {
	CallDate3 = callDate3;
}






public String getPutDate() {
	return PutDate;
}






public void setPutDate(String putDate) {
	PutDate = putDate;
}






public String getFirstCouponDate() {
	return FirstCouponDate;
}






public void setFirstCouponDate(String firstCouponDate) {
	FirstCouponDate = firstCouponDate;
}






public String getNextCouponDate() {
	return NextCouponDate;
}






public void setNextCouponDate(String nextCouponDate) {
	NextCouponDate = nextCouponDate;
}






public String getLastCouponDate() {
	return LastCouponDate;
}






public void setLastCouponDate(String lastCouponDate) {
	LastCouponDate = lastCouponDate;
}






public double getYTM() {
	return YTM;
}






public void setYTM(double yTM) {
	YTM = yTM;
}






public double getYTW() {
	return YTW;
}






public void setYTW(double yTW) {
	YTW = yTW;
}






public IndicativeData() {
	// TODO Auto-generated constructor stub
}






@Override
public String toString() {
	return "IndicativeData [cusip=" + cusip + ", isin=" + isin + ", issuerId="
			+ issuerId + ", ticker=" + ticker + ", type=" + type
			+ ", IssueDate=" + IssueDate + ", maturityDate=" + maturityDate
			+ ", tenor=" + tenor + ", coupon=" + coupon + ", subordination="
			+ subordination + ", moodys=" + moodys + ", snp=" + snp
			+ ", Callable=" + Callable + ", Putable=" + Putable
			+ ", CallDate1=" + CallDate1 + ", CallDate2=" + CallDate2
			+ ", CallDate3=" + CallDate3 + ", PutDate=" + PutDate
			+ ", FirstCouponDate=" + FirstCouponDate + ", NextCouponDate="
			+ NextCouponDate + ", LastCouponDate=" + LastCouponDate + ", YTM="
			+ YTM + ", YTW=" + YTW + "]";
}
	



}
