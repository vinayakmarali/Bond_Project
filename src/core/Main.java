package core;


import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

	public static void main(String[] args) throws HibernateException, SQLException {
		SessionFactory sf=HibernateUtil.getSessionFactory();
		Session ses=sf.openSession();
		ses.beginTransaction();

		
	/*
		
		Firm firm = new Firm("HDFC Security");
		Industry industry = new Industry("IT");
		Issuer issuer = new Issuer("INFOSYS", industry);
		Bank bank = new Bank("JPM","Evershine nagar","Mumbai","Maharashtra");
		BankAcc bankAcc = new BankAcc(bank,100000);
		Book book = new Book("Book1");
		Subordination subordination = new Subordination("Young Group");
		IndicativeData indicativeData = new IndicativeData("123456789123","123456789", issuer, "INFY", "Corporate", Date.valueOf("2013-02-01"),Date.valueOf("2013-04-01"), "semi-annually", 8.5, subordination, "A++", "A--", true, false, Date.valueOf("2013-02-01"),Date.valueOf("2013-05-01"), Date.valueOf("2013-08-01"), Date.valueOf("2013-02-01"),Date.valueOf("2013-02-01"), Date.valueOf("2013-02-01"), Date.valueOf("2013-02-01"), 12.5, 13.5);
		DepositoryBank depositoryBank = new DepositoryBank("JPMORGAN", "MUMBAI");
		DepositoryAcc depositoryAcc = new DepositoryAcc(depositoryBank,indicativeData.getCusip());
		Broker broker= new Broker("viraj","Viraj", "Kamath", firm, book, bankAcc, depositoryAcc);
		
		Login login = new Login("viraj","viraj", "user", Date.valueOf("2013-02-11"));
		
		OTRIssues otrIssues = new OTRIssues(indicativeData, issuer, indicativeData.getCoupon(),indicativeData.getMaturityDate(), 1000.0, 1200, 20.9);
		Order order1= new Order(broker, firm,indicativeData, "buy", 8.0, 3.2, 1000.0, 1001.0, 20.0, "exex",Date.valueOf("2013-02-01" ));
		Order order2= new Order(broker, firm,indicativeData, "sell", 8.0, 3.2, 1000.0, 1001.0, 20.0, "exex",Date.valueOf("2013-02-01" ));
		Trade trade1 = new Trade(order1,firm,indicativeData, order1.getPrice(), order1.getYield(), order1.getDirection(), 2002.0, 32.0, 24.0, 12.36, 45.0, Date.valueOf("2013-01-12"), Date.valueOf("2013-02-01"));
		Trade trade2 = new Trade(order2,firm,indicativeData, order2.getPrice(), order2.getYield(), order2.getDirection(), 2002.0, 32.0, 24.0, 12.36, 45.0, Date.valueOf("2013-01-12"), Date.valueOf("2013-02-01"));
		MatchedOrder matchedOrder = new MatchedOrder(order1, order2,"bond");
		
	
		ses.save(firm);
		ses.save(issuer);
		ses.save(bankAcc);
		ses.save(indicativeData);
		ses.save(broker);
		ses.save(otrIssues);
		ses.save(order1);
		ses.save(trade1);
		ses.save(order2);
		ses.save(trade2);
		ses.save(matchedOrder);
		CallableStatement callableStatement = ses.connection().prepareCall("call register(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		callableStatement.setString(1,broker.getFirstName());
		callableStatement.setString(2, login.getPassword());
		callableStatement.setDate(3,login.getDateTime());
		callableStatement.setInt(4, login.getEnabled());
		callableStatement.setString(5, userRoles.getAuthority());
		callableStatement.setString(6, broker.getLastName());
		callableStatement.setString(7, firm.getName());
		callableStatement.setString(8, bank.getName());
		callableStatement.setString(9, bank.getStreet_name());
		callableStatement.setString(10, bank.getCity());
		callableStatement.setString(11, bank.getState());
		callableStatement.setDouble(12, bankAcc.getBalance());
		callableStatement.setString(13, book.getName());
		callableStatement.setString(14, depositoryBank.getName());
		callableStatement.setString(15, depositoryBank.getLocation());
		callableStatement.setLong(16,depositoryAcc.getCusip());
		
		
		callableStatement.execute();
		
	
		ses.save(login);*/
		//ses.save(login1);
ses.getTransaction().commit();
ses.flush();
	ses.close();
	HibernateUtil.shutdown();



	}
}