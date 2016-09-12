package core;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

public class BankAcc
{
	String accno;
	String bankId;
	double balance;
	
	
	
	public BankAcc() {
		// TODO Auto-generated constructor stub
	}
	
	
	public BankAcc(String accno, String bankId, double balance) {
		super();
		this.accno = accno;
		this.bankId = bankId;
		this.balance = balance;
	}




	public BankAcc(String accno) {
		super();
		this.accno = accno;
	}

	public  String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	

	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}


	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
