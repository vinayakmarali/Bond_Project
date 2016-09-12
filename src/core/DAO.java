package core;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.jdbc.driver.OracleTypes;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAO {
	HttpSession session;
public boolean validateUser(Login login){
		
		try {
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select count(*) as count from login where user_id=? and password=?");
			preparedStatement.setString(1, login.getUserId());
			preparedStatement.setString(2, login.getPassword());
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if(resultSet.getString("count").equals("1"))
			return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return false;
		}
	
	String getLimitOfBroker(String userId)
	{
		try {
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select limit_amount from broker where broker_id=?");
			preparedStatement.setString(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String limitAmount = resultSet.getString("limit_amount");
			
			return limitAmount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	Map<String, Object> getOTR()
	{
		Map<String, Object> map = new HashMap<String, Object>();
	try {
		
		Connection connection = ConnectionFactory.getConnection();
		Statement statement = connection.createStatement();
		
		
		//Last trade price
		ResultSet resultSet2 = statement.executeQuery("select price from (select price from trade order by trade_id desc) where rownum=1");
		if(resultSet2.next()==true)
			map.put("LTP", resultSet2.getDouble("PRICE"));
		else
			map.put("LTP", 0.0);
		resultSet2.close();
		ResultSet resultSet3;
		
		// Close Trade Price
		Date date = new Date();
		String currTime = date.getHours()+":"+date.getMinutes()+":"+date.getSeconds(); 
		
		if(currTime.compareTo("17:0:0")==0 || currTime.compareTo("17:0:0")==1)
			resultSet3 = statement.executeQuery("select price from trade where trade_date=to_date(sysdate) and trade_time = (select max(trade_time) from trade) and rownum=1");
		else
			resultSet3 = statement.executeQuery("select price from trade where trade_date=to_date(sysdate) and trade_time = (select max(trade_time) from trade) and rownum=1");
		if(resultSet3.next()==true)
			map.put("CLOSE", resultSet3.getDouble("price"));
		else
			map.put("CLOSE", 0.0);
		resultSet3.close();
		
		// Open Trade Price
		ResultSet resultSet4 = statement.executeQuery("select price from trade where trade_date=to_date(sysdate) and trade_time = (select min(trade_time) from trade) and rownum=1");
		if(resultSet4.next()==true)
			map.put("OPEN", resultSet4.getDouble("price"));
		else
			map.put("OPEN", 0.0);
		resultSet4.close();
		// High Trade Price
		ResultSet resultSet5 = statement.executeQuery("select max(price) as high from trade where trade_date=to_date(sysdate) and rownum=1");
		if(resultSet5.next()==true)
			map.put("HIGH", resultSet5.getDouble("high"));
		else
			map.put("HIGH", 0.0);
		resultSet5.close();
		// Low Trade Price
		ResultSet resultSet6 = statement.executeQuery("select min(price) as low from trade where trade_date=to_date(sysdate) and rownum=1");
		if(resultSet6.next()==true)
			map.put("LOW", resultSet6.getDouble("low"));
		else
			map.put("LOW", 0.0);
		resultSet6.close();
		List<OTRIssues> list = new ArrayList<OTRIssues>();
		ResultSet resultSet1 = statement.executeQuery("select * from OTRIssues");
		while(resultSet1.next())
		{
			OTRIssues otrIssues = new OTRIssues(resultSet1.getInt("id"),resultSet1.getString("cusip"),resultSet1.getString("ISSUER"),resultSet1.getDouble("coupon"),resultSet1.getDate("maturity").toString(),resultSet1.getDouble("bid"),resultSet1.getDouble("ask"),resultSet1.getDouble("bid_prev"),resultSet1.getDouble("ask_prev"),resultSet1.getDouble("bid_yield"),resultSet1.getDouble("ask_yield"));
			list.add(otrIssues);
		}
		map.put("OTRISSUES",list);
		resultSet1.close();
		
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return map;


	}

public String getTradeId()  {
		
		Connection connection = ConnectionFactory.getConnection();
		try {
			CallableStatement callableStatement = connection.prepareCall("call getTradeId(?,?)");
			callableStatement.setString(1, "Bond");
			callableStatement.registerOutParameter(2,java.sql.Types.VARCHAR);
			callableStatement.executeUpdate();
			String tradeId = callableStatement.getString(2);
			return tradeId;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}


public void insertOrder(String str[])
{
	Connection connection = ConnectionFactory.getConnection();
	try {
		
		
		System.out.println(str[0]+"---"+str[1]+"---"+str[2]+"---"+str[3]+"---"+str[4]+"---"+str[5]+"---"+str[6]+"---"+str[7]+"---"+str[8]+"---"+str[9]+"---"+str[10]);
		SimpleDateFormat dateParse = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
		String maturityDate="";
		try {
			Date d = dateParse.parse(str[5]);
			maturityDate = simpleDateFormat.format(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "insert into orderdetails(order_id,broker_id,cusip,direction,coupon,maturity,notional,price,yield,status,order_date,settlement_date,order_time,issuer,tenor,value_till,user_order) values(order_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setString(1, str[1]);
		preparedStatement.setString(2, str[2]);
		preparedStatement.setString(3, str[3]);
		preparedStatement.setDouble(4, Double.parseDouble(str[4]));
		preparedStatement.setString(5, maturityDate);
		preparedStatement.setDouble(6, Double.parseDouble(str[6]));
		preparedStatement.setDouble(7, Double.parseDouble(str[7]));
		preparedStatement.setDouble(8, Double.parseDouble(str[8]));
		preparedStatement.setString(9, str[9]);
		preparedStatement.setString(10, str[10]);
		preparedStatement.setString(11, str[11]);
		preparedStatement.setString(12, str[12]);
		preparedStatement.setString(13, str[13]);
		preparedStatement.setDouble(14, Double.parseDouble(str[14]));
		preparedStatement.setString(15, str[15]);
		preparedStatement.setDouble(16, Double.parseDouble(str[16]));
		preparedStatement.executeQuery();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
		
		void updatePrice(String str[])
		{
			SessionFactory sf=HibernateUtil.getSessionFactory();
			Session ses=sf.openSession();  
			
			try {
						CallableStatement callableStatement = ses.connection().prepareCall("call updatePriceBond(?,?,?,?)");
						callableStatement.setString(1, "Bond");
						callableStatement.setString(2, str[1]);
						callableStatement.setString(3, str[2]);
						callableStatement.setString(4, str[3]);
						callableStatement.executeUpdate();
					} catch (NumberFormatException e) {
					
						e.printStackTrace();
					} catch (HibernateException e) {
						
						e.printStackTrace();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
			
		}
		
		public void saveRegistrationData(String username, String password,
				String firstName, String lastName, String firmId, String bookId,
				String bankAccNo, String depositoryAccNo) {
			System.out.println("IN AVE REG");
			
			SessionFactory sf=HibernateUtil.getSessionFactory();
			Session ses=sf.openSession();  
			
			try {
						CallableStatement callableStatement = ses.connection().prepareCall("call saveBondRegistrationData(?,?,?,?,?,?,?,?)");
						callableStatement.setString(1,firstName);
						callableStatement.setString(2, lastName);
						callableStatement.setString(3,username);
						callableStatement.setString(4,password );
						callableStatement.setString(5,firmId );
						callableStatement.setString(6,bookId );
						callableStatement.setString(7,depositoryAccNo );
						callableStatement.setString(8,bankAccNo);
						callableStatement.executeUpdate();
					} catch (NumberFormatException e) {
					
						e.printStackTrace();
					} catch (HibernateException e) {
						
						e.printStackTrace();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
			
		}


		
		ArrayList<ArrayList<Map<String, String>>> populateRegister()
		{
			ArrayList<ArrayList<Map<String, String>>> list;
			try {
				SessionFactory sf=HibernateUtil.getSessionFactory();
				Session ses=sf.openSession();  
				list = new ArrayList<ArrayList<Map<String, String>>>();
				CallableStatement callableStatement = ses.connection().prepareCall("call populateRegister(?,?,?,?,?,?)");
				callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
				callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
				callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
				callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
				callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
				callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
				callableStatement.execute();
				
				ResultSet resultSet1 = (ResultSet) callableStatement.getObject(1);
				ArrayList<Map<String, String>> list1 = new ArrayList<Map<String,String>>();
				
				ResultSet resultSet2 = (ResultSet) callableStatement.getObject(2);
				ArrayList<Map<String, String>> list2 = new ArrayList<Map<String,String>>();
				
				ResultSet resultSet3 = (ResultSet) callableStatement.getObject(3);
				ArrayList<Map<String, String>> list3 = new ArrayList<Map<String,String>>();
				
				ResultSet resultSet4 = (ResultSet) callableStatement.getObject(4);
				ArrayList<Map<String, String>> list4 = new ArrayList<Map<String,String>>();
				
				ResultSet resultSet5 = (ResultSet) callableStatement.getObject(5);
				ArrayList<Map<String, String>> list5 = new ArrayList<Map<String,String>>();
				
				ResultSet resultSet6 = (ResultSet) callableStatement.getObject(6);
				ArrayList<Map<String, String>> list6 = new ArrayList<Map<String,String>>();
				
				
				while(resultSet1.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					map.put("FIRM_ID", resultSet1.getString("FIRM_ID"));
					map.put("NAME", resultSet1.getString("NAME"));
					list1.add(map);
				}
				
				while(resultSet2.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					map.put("BOOKID", resultSet2.getString("BOOKID"));
					map.put("NAME", resultSet2.getString("NAME"));
					list2.add(map);
				}
				
				while(resultSet3.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					map.put("BANK_ID", resultSet3.getString("BANK_ID"));
					map.put("NAME", resultSet3.getString("NAME"));
					map.put("STREETNAME", resultSet3.getString("STREETNAME"));
					map.put("CITY", resultSet3.getString("CITY"));
					map.put("STATE", resultSet3.getString("STATE"));
					list3.add(map);
				}
				
				while(resultSet4.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					map.put("ACC_NO", resultSet4.getString("ACC_NO"));
					map.put("BANK_ID", resultSet4.getString("BANK_ID"));
					map.put("BALANCE", resultSet4.getString("BALANCE"));
					list4.add(map);
				}
				
				while(resultSet5.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					map.put("DP_ID", resultSet5.getString("DP_ID"));
					map.put("DP_NAME", resultSet5.getString("DP_NAME"));
					map.put("LOCATION", resultSet5.getString("LOCATION"));
					list5.add(map);
				}
				
				while(resultSet6.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					map.put("ID", resultSet6.getString("ID"));
					map.put("DP_ACC_NO", resultSet6.getString("DP_ACC_NO"));
					map.put("DP_ID", resultSet6.getString("DP_ID"));
					map.put("CUSIP", resultSet6.getString("CUSIP"));
					list6.add(map);
				}
				
				list.add(list1);
				list.add(list2);
				list.add(list3);
				list.add(list4);
				list.add(list5);
				list.add(list6);
				return list;
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
			
		}
		
		
		public Map<String, Object> getCusipData(String str[])
		{
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("select * from IndicativeData where cusip=?");
				preparedStatement.setString(1, str[2]);
				ResultSet resultSet = preparedStatement.executeQuery();
				if(resultSet.next()==true)
				{
					String cusip = resultSet.getString("CUSIP");
					String isin = resultSet.getString("ISIN");
					int issuerId = resultSet.getInt("ISSUER_ID");
					String ticker = resultSet.getString("TICKER");
					String type = resultSet.getString("TYPE");
					String issueDate = resultSet.getDate("ISSUE_DATE").toString();
					String maturityDate = resultSet.getDate("MATURITY_DATE").toString();
					double tenor = resultSet.getDouble("TENOR");
					double coupon = resultSet.getDouble("COUPON");
					int subordination = resultSet.getInt("SUBORDINATIONID");
					String moodys = resultSet.getString("MOODYS");
					String snp = resultSet.getString("SNP");
					String callable = resultSet.getString("CALLABLE");
					String putable = resultSet.getString("PUTABLE");
					
					String callDate1 = "";
					if(resultSet.getString("CALLDATE1")==null)
						callDate1 = null;
					else
						callDate1 = resultSet.getDate("CALLDATE1").toString();
					
					String callDate2 = "";
					if(resultSet.getString("CALLDATE2")==null)
						callDate2 = null;
					else
						callDate2 = resultSet.getDate("CALLDATE2").toString();
					
					String callDate3 = "";
					if(resultSet.getString("CALLDATE2")==null)
						callDate3 = null;
					else
						callDate3 = resultSet.getDate("CALLDATE3").toString();
					
					String putDate="";
					if(resultSet.getString("PUTDATE")==null)
						putDate = null;
					else
						putDate = resultSet.getDate("PUTDATE").toString();
					
					String firstCouponDate = "";
					if(resultSet.getString("FIRSTCOUPON_DATE")==null)
						firstCouponDate = null;
					else
						firstCouponDate = resultSet.getDate("FIRSTCOUPON_DATE").toString();
					
					String nextCouponDate = "";
					if(resultSet.getString("NEXTCOUPON_DATE")==null)
						nextCouponDate=null;
					else
						nextCouponDate = resultSet.getDate("NEXTCOUPON_DATE").toString();
					
					String lastCouponDate="";
					if(resultSet.getString("LASTCOUPON_DATE")==null)
						lastCouponDate=null;
					else
						lastCouponDate = resultSet.getDate("LASTCOUPON_DATE").toString();
					
					double yTM = resultSet.getDouble("YTM");
					double yTW = resultSet.getDouble("YTW");
					
					
					//IndicativeData i = new IndicativeData(cusip, isin, issuerId, ticker, type, issueDate, maturityDate, tenor, coupon, subordination, moodys, snp, callable, putable, callDate1, callDate2, callDate3, putDate, firstCouponDate, nextCouponDate, lastCouponDate, yTM, yTW);
				map.put("CUSIP",cusip);
				map.put("ISIN", isin);
				map.put("ISSUER_ID", issuerId);
				map.put("TICKER", ticker);
				map.put("TYPE", type);
				map.put("ISSUE_DATE", issueDate);
				map.put("MATURITY_DATE", maturityDate);
				map.put("TENOR", tenor);
				map.put("COUPON", coupon);
				map.put("SUBORDINATIONID", subordination);
				map.put("MOODYS", moodys);
				map.put("SNP", snp);
				map.put("CALLABLE", callable);
				map.put("PUTABLE", putable);
				map.put("CALLDATE1", callDate1);
				map.put("CALLDATE2", callDate2);
				map.put("CALLDATE3", callDate3);
				map.put("PUTDATE", putDate);
				map.put("FIRSTCOUPON_DATE", firstCouponDate);
				map.put("NEXTCOUPON_DATE", nextCouponDate);
				map.put("LASTCOUPON_DATE", lastCouponDate);
				map.put("YTM", yTM);
				map.put("YTW", yTW);
				}
				
				CallableStatement callableStatement = connection.prepareCall("call getTradeId(?,?)");
				callableStatement.setString(1, "Bond");
				callableStatement.registerOutParameter(2,java.sql.Types.VARCHAR);
				callableStatement.executeUpdate();
				String tradeId = callableStatement.getString(2);
				map.put("ORDER_ID", tradeId);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return map;
				  
		}
		
		Double getTradePortfolio(String userId, String cusip)
		{
			try {
				Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("select t.gross_amt as gross_amt from TRADE t,ORDERDETAILS d where t.cusip=? and t.order_id=d.order_id and d.broker_id=? and t.direction='BUY' order by rownum desc");
				preparedStatement.setString(1, cusip);
				preparedStatement.setString(2, userId);
				ResultSet resultSet = preparedStatement.executeQuery();
				if(resultSet.next()==true)
				{
					Double grossAmt = resultSet.getDouble("gross_amt");
					return grossAmt;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
				return null;
		}
		
		public void updateLimit(String broker_id, String limitAmount) {
			try {
				Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("update broker set limit_amount=? where broker_id=?");
				preparedStatement.setDouble(1, Double.parseDouble(limitAmount));
				preparedStatement.setString(2, broker_id);
				preparedStatement.executeUpdate();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		ArrayList<ArrayList<Map<String, String>>> getOrderDetails()
		{
			Connection connection = ConnectionFactory.getConnection();
				
				try {
					ArrayList<ArrayList<Map<String, String>>> list = new ArrayList<ArrayList<Map<String, String>>>();
					CallableStatement callableStatement = connection.prepareCall("call getOrderDetails(?,?)");
					
					callableStatement.registerOutParameter(1,OracleTypes.CURSOR);//indi
					callableStatement.registerOutParameter(2,OracleTypes.CURSOR);//iss
					
					callableStatement.execute();
					
					ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
					ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
					ArrayList<Map<String, String>> list1 = new ArrayList<Map<String,String>>();
					int column = resultSetMetaData.getColumnCount();
				
					ResultSet resultSet2 = (ResultSet) callableStatement.getObject(2);
					ArrayList<Map<String, String>> list2 = new ArrayList<Map<String,String>>();
					
					
					
					
					int i = 1;
					while(resultSet.next())
					{
						
						i = 1;
						Map<String, String> map = new HashMap<String, String>();
						while(i!=column+1)
						{
							
							String columnName = resultSetMetaData.getColumnName(i);
			
							map.put(columnName, resultSet.getString(columnName));
							i++;
						}
						list1.add(map);
						list1.size();
						
					}
					int j = 1;
					while(resultSet2.next())
					{
						
						j = 1;
						Map<String, String> map = new HashMap<String, String>();
						while(j!=column+1)
						{
							
							String columnName = resultSetMetaData.getColumnName(j);
			
							map.put(columnName, resultSet2.getString(columnName));
							j++;
						}
						
						list2.add(map);
						
					}
					
					
				
					list.add(list1);
					list.add(list2);
					
					return list;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
		}
		
		
		public ArrayList<Map<String, String>> blotterOrder(String[] str) throws SQLException {
			System.out.println("---------------------- 00000 --------------------");
			ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
			Map<String, String> map = new HashMap<String, String>();
			Connection connection = ConnectionFactory.getConnection();
			if(str.length<7)
			{
				//if coupon,date,tenor are empty
				System.out.println("-----------------c ----------------");
				if(str[1].equals("BOTH"))
				{
					CallableStatement callableStatement =connection.prepareCall("call blotterOrderNoDCTBoth(?,?)");				
					callableStatement.setString(1, str[4]);
					callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
					callableStatement.execute();
					ResultSet resultSet=(ResultSet) callableStatement.getObject(2);
					ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
					int column = resultSetMetaData.getColumnCount();
					int i = 1;
					while(resultSet.next())
					{
						i = 1;
						map = new HashMap<String, String>();
						while(i!=column+1)
						{
							
							String columnName = resultSetMetaData.getColumnName(i);
							map.put(columnName, resultSet.getString(columnName));
							i++;
						}
						list.add(map);
					}
					return list;
				}
				else
				{
				CallableStatement callableStatement =connection.prepareCall("call blotterOrderNoDateCouponTenor(?,?,?)");				
				callableStatement.setString(1, str[4]);
				callableStatement.setString(2,str[1]);
				callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
				callableStatement.execute();
				ResultSet resultSet=(ResultSet) callableStatement.getObject(3);
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int column = resultSetMetaData.getColumnCount();
				int i = 1;
				while(resultSet.next())
				{
					i = 1;
					map = new HashMap<String, String>();
					while(i!=column+1)
					{
						
						String columnName = resultSetMetaData.getColumnName(i);
						map.put(columnName, resultSet.getString(columnName));
						i++;
					}
					list.add(map);
				}
				return list;
				}
			}
			else
			{
			String sdate = str[5].toUpperCase();
			String edate = str[6].toUpperCase();
			System.out.println("---------------------- 0 --------------------");
			
				if((str[2].isEmpty() || str[7].isEmpty()) && (str[3].isEmpty() || str[8].isEmpty()) && !(str[5].isEmpty() || str[6].isEmpty()))
				{
					System.out.println("-----------------a ----------------");
					if(str[1].equals("BOTH"))
					{
							System.out.println("-----------------a ----------------");
							//if coupon,tenor is empty & date not empty
							CallableStatement callableStatement =connection.prepareCall("call blotterOrderNoCNoTBoth(?,?,?,?)");				
							callableStatement.setString(1, str[4]);
							callableStatement.setString(2, sdate);
							callableStatement.setString(3, edate);
							callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
							callableStatement.execute();
							ResultSet resultSet=(ResultSet) callableStatement.getObject(4);
							ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
							int column = resultSetMetaData.getColumnCount();
							int i = 1;
							while(resultSet.next())
							{
								
								i = 1;
								map = new HashMap<String, String>();
								while(i!=column+1)
								{
									
									String columnName = resultSetMetaData.getColumnName(i);
									map.put(columnName, resultSet.getString(columnName));
									i++;
								}
								list.add(map);
							}
							return list;
					}
					//if coupon,tenor is empty & date not empty
					else
					{
					CallableStatement callableStatement =connection.prepareCall("call blotterOrderNoCouponNoTenor(?,?,?,?,?)");				
					callableStatement.setString(1, str[4]);
					callableStatement.setString(2,str[1]);
					callableStatement.setString(3, sdate);
					callableStatement.setString(4, edate);
					callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
					callableStatement.execute();
					ResultSet resultSet=(ResultSet) callableStatement.getObject(5);
					ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
					int column = resultSetMetaData.getColumnCount();
					int i = 1;
					while(resultSet.next())
					{
						
						i = 1;
						map = new HashMap<String, String>();
						while(i!=column+1)
						{
							
							String columnName = resultSetMetaData.getColumnName(i);
							map.put(columnName, resultSet.getString(columnName));
							i++;
						}
						list.add(map);
					}
					return list;
				}
				}
				else if((str[2].isEmpty() || str[7].isEmpty()) && (str[5].isEmpty() || str[6].isEmpty()) && !(str[3].isEmpty() || str[8].isEmpty()) )
					{
						//if coupon,date is empty and tenor not empty
					System.out.println("-----------------b ----------------");
					if(str[1].equals("BOTH"))
					{
						CallableStatement callableStatement =connection.prepareCall("call blotterOrderNoCNoDBoth(?,?,?,?)");				
						callableStatement.setString(1, str[4]);
						callableStatement.setString(2,str[3]);
						callableStatement.setString(3,str[8]);
						callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
						callableStatement.execute();
						ResultSet resultSet=(ResultSet) callableStatement.getObject(4);
						ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
						int column = resultSetMetaData.getColumnCount();
						int i = 1;
						while(resultSet.next())
						{
							
							i = 1;
							map = new HashMap<String, String>();
							while(i!=column+1)
							{
								
								String columnName = resultSetMetaData.getColumnName(i);
								map.put(columnName, resultSet.getString(columnName));
								i++;
							}
							list.add(map);
						}
						return list;
					}
					else
					{
						CallableStatement callableStatement =connection.prepareCall("call blotterOrderNoCouponNoDate(?,?,?,?,?)");				
						callableStatement.setString(1, str[4]);
						callableStatement.setString(2,str[1]);
						callableStatement.setString(3,str[3]);
						callableStatement.setString(4,str[8]);
						callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
						callableStatement.execute();
						ResultSet resultSet=(ResultSet) callableStatement.getObject(5);
						ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
						int column = resultSetMetaData.getColumnCount();
						int i = 1;
						while(resultSet.next())
						{
							
							i = 1;
							map = new HashMap<String, String>();
							while(i!=column+1)
							{
								
								String columnName = resultSetMetaData.getColumnName(i);
								map.put(columnName, resultSet.getString(columnName));
								i++;
							}
							list.add(map);
						}
						return list;
					}}
				
				else if((str[2].isEmpty() || str[7].isEmpty()) && !(str[5].isEmpty() || str[6].isEmpty()) && !(str[3].isEmpty() || str[8].isEmpty()))
				{
					
					if(str[1].equals("BOTH"))
					{
						CallableStatement callableStatement =connection.prepareCall("call blotterOrderNoCBoth(?,?,?,?,?,?)");				
						callableStatement.setString(1, str[4]);
						callableStatement.setString(2, str[3]);
						callableStatement.setString(3, sdate);
						callableStatement.setString(4, edate);
						callableStatement.setString(5, str[8]);
						callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
						callableStatement.execute();
						ResultSet resultSet=(ResultSet) callableStatement.getObject(6);
						ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
						int column = resultSetMetaData.getColumnCount();
						int i = 1;
						while(resultSet.next())
						{
							
							i = 1;
							map = new HashMap<String, String>();
							while(i!=column+1)
							{
								
								String columnName = resultSetMetaData.getColumnName(i);
								map.put(columnName, resultSet.getString(columnName));
								i++;
							}
							list.add(map);
						}
						return list;
					}
					//only coupon is empty
					else
					{
					System.out.println("-----------------d ----------------");
					CallableStatement callableStatement =connection.prepareCall("call blotterOrderNoCoupon(?,?,?,?,?,?,?)");				
					callableStatement.setString(1, str[4]);
					callableStatement.setString(2,str[1]);
					callableStatement.setString(3, str[3]);
					callableStatement.setString(4, sdate);
					callableStatement.setString(5, edate);
					callableStatement.setString(6, str[8]);
					callableStatement.registerOutParameter(7, OracleTypes.CURSOR);
					callableStatement.execute();
					ResultSet resultSet=(ResultSet) callableStatement.getObject(7);
					ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
					int column = resultSetMetaData.getColumnCount();
					int i = 1;
					while(resultSet.next())
					{
						
						i = 1;
						map = new HashMap<String, String>();
						while(i!=column+1)
						{
							
							String columnName = resultSetMetaData.getColumnName(i);
							map.put(columnName, resultSet.getString(columnName));
							i++;
						}
						list.add(map);
					}
					return list;
			}}
				else if((str[3].isEmpty() || str[8].isEmpty()) && !(str[2].isEmpty() || str[7].isEmpty()) && !(str[5].isEmpty() || str[6].isEmpty()))
			{
				//if only tenor is empty
					if(str[1].equals("BOTH"))
					{
						CallableStatement callableStatement =connection.prepareCall("call blotterOrderNoTBoth(?,?,?,?,?,?)");				
						callableStatement.setString(1, str[4]);
						callableStatement.setString(2,str[2]);
						callableStatement.setString(3, sdate);
						callableStatement.setString(4, edate);
						callableStatement.setString(5,str[7]);
						callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
						callableStatement.execute();
						ResultSet resultSet=(ResultSet) callableStatement.getObject(6);
						ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
						int column = resultSetMetaData.getColumnCount();
						int i = 1;
						while(resultSet.next())
						{
							
							i = 1;
							map = new HashMap<String, String>();
							while(i!=column+1)
							{
								
								String columnName = resultSetMetaData.getColumnName(i);
								map.put(columnName, resultSet.getString(columnName));
								i++;
							}
							list.add(map);
						}
						return list;
					}
					else
					{
					System.out.println("-----------------e ----------------");
					CallableStatement callableStatement =connection.prepareCall("call blotterOrderNoTenor(?,?,?,?,?,?,?)");				
					callableStatement.setString(1, str[4]);
					callableStatement.setString(2,str[1]);
					callableStatement.setString(3,str[2]);
					callableStatement.setString(4, sdate);
					callableStatement.setString(5, edate);
					callableStatement.setString(6,str[7]);
					callableStatement.registerOutParameter(7, OracleTypes.CURSOR);
					callableStatement.execute();
					ResultSet resultSet=(ResultSet) callableStatement.getObject(7);
					ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
					int column = resultSetMetaData.getColumnCount();
					int i = 1;
					while(resultSet.next())
					{
						
						i = 1;
						map = new HashMap<String, String>();
						while(i!=column+1)
						{
							
							String columnName = resultSetMetaData.getColumnName(i);
							map.put(columnName, resultSet.getString(columnName));
							i++;
						}
						list.add(map);
					}
					return list;
			}}
			
				else if((str[5].isEmpty() || str[6].isEmpty()) && !(str[2].isEmpty() || str[7].isEmpty())  && !(str[3].isEmpty() || str[8].isEmpty()))
			{
				//if only date is empty
					if(str[1].equals("BOTH"))
					{
						CallableStatement callableStatement =connection.prepareCall("call blotterOrderNoDBoth(?,?,?,?,?,?)");				
						callableStatement.setString(1, str[4]);
						callableStatement.setString(2,str[2]);
						callableStatement.setString(3,str[3]);
						callableStatement.setString(4,str[7]);
						callableStatement.setString(5,str[8]);
						callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
						callableStatement.execute();
						ResultSet resultSet=(ResultSet) callableStatement.getObject(6);
						ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
						int column = resultSetMetaData.getColumnCount();
						int i = 1;
						while(resultSet.next())
						{
							
							i = 1;
							map = new HashMap<String, String>();
							while(i!=column+1)
							{
								
								String columnName = resultSetMetaData.getColumnName(i);
								map.put(columnName, resultSet.getString(columnName));
								i++;
							}
							list.add(map);
						}
						return list;
					}
					else
					{
					System.out.println("-----------------f ----------------");
				CallableStatement callableStatement =connection.prepareCall("call blotterOrderNoDate(?,?,?,?,?,?,?)");				
				callableStatement.setString(1, str[4]);
				callableStatement.setString(2,str[1]);
				callableStatement.setString(3,str[2]);
				callableStatement.setString(4,str[3]);
				callableStatement.setString(5,str[7]);
				callableStatement.setString(6,str[8]);
				callableStatement.registerOutParameter(7, OracleTypes.CURSOR);
				callableStatement.execute();
				ResultSet resultSet=(ResultSet) callableStatement.getObject(7);
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int column = resultSetMetaData.getColumnCount();
				int i = 1;
				while(resultSet.next())
				{
					
					i = 1;
					map = new HashMap<String, String>();
					while(i!=column+1)
					{
						
						String columnName = resultSetMetaData.getColumnName(i);
						map.put(columnName, resultSet.getString(columnName));
						i++;
					}
					list.add(map);
				}
				return list;
			}}
				else if((str[5].isEmpty() || str[6].isEmpty()) && (str[3].isEmpty() || str[8].isEmpty())&& !(str[2].isEmpty() || str[7].isEmpty()))
				{
					//if date,tenor is empty and coupon is not empty
					
					if(str[1].equals("BOTH"))
					{
						CallableStatement callableStatement =connection.prepareCall("call blotterOrderNoDNoTBoth(?,?,?,?)");				
						callableStatement.setString(1, str[4]);
						callableStatement.setString(2,str[2]);
						callableStatement.setString(3,str[7]);
						callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
						callableStatement.execute();
						ResultSet resultSet=(ResultSet) callableStatement.getObject(4);
						ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
						int column = resultSetMetaData.getColumnCount();
						int i = 1;
						while(resultSet.next())
						{
							
							i = 1;
							map = new HashMap<String, String>();
							while(i!=column+1)
							{
								
								String columnName = resultSetMetaData.getColumnName(i);
								map.put(columnName, resultSet.getString(columnName));
								i++;
							}
							list.add(map);
						}
						return list;
					}
					else
					{
						System.out.println("-----------------g ----------------");
					CallableStatement callableStatement =connection.prepareCall("call blotterOrderNoDateNoTenor(?,?,?,?,?)");				
					callableStatement.setString(1, str[4]);
					callableStatement.setString(2,str[1]);
					callableStatement.setString(3,str[2]);
					callableStatement.setString(4,str[7]);
					callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
					callableStatement.execute();
					ResultSet resultSet=(ResultSet) callableStatement.getObject(5);
					ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
					int column = resultSetMetaData.getColumnCount();
					int i = 1;
					while(resultSet.next())
					{
						
						i = 1;
						map = new HashMap<String, String>();
						while(i!=column+1)
						{
							
							String columnName = resultSetMetaData.getColumnName(i);
							map.put(columnName, resultSet.getString(columnName));
							i++;
						}
						list.add(map);
					}
					return list;
				}}
				else if(!(str[5].isEmpty() || str[6].isEmpty()) && !(str[3].isEmpty() || str[8].isEmpty())&& !(str[2].isEmpty() || str[7].isEmpty()))
				{
					//no empty fields
					if(str[1].equals("BOTH"))
					{
						CallableStatement callableStatement =connection.prepareCall("call blotterFullOrder(?,?,?,?,?,?,?,?)");
						callableStatement.setString(1, str[4]);
						callableStatement.setString(2, str[2]);
						callableStatement.setString(3, str[3]);
						callableStatement.setString(4, sdate);
						callableStatement.setString(5, edate);
						callableStatement.setString(6, str[7]);
						callableStatement.setString(7, str[8]);
						callableStatement.registerOutParameter(8, OracleTypes.CURSOR);
						callableStatement.execute();
						ResultSet resultSet=(ResultSet) callableStatement.getObject(8);
						ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
						int column = resultSetMetaData.getColumnCount();
						int i = 1;
						while(resultSet.next())
						{
							
							i = 1;
							map = new HashMap<String, String>();
							while(i!=column+1)
							{
								
								String columnName = resultSetMetaData.getColumnName(i);
								map.put(columnName, resultSet.getString(columnName));
								i++;
							}
							list.add(map);
						}
						return list;
					}
					else
					{
					System.out.println("-----------------h ----------------");
					CallableStatement callableStatement =connection.prepareCall("call blotterOrder(?,?,?,?,?,?,?,?,?)");				
					callableStatement.setString(1, str[4]);
					callableStatement.setString(2,str[1]);
					callableStatement.setString(3, str[2]);
					callableStatement.setString(4, str[3]);
					callableStatement.setString(5, sdate);
					callableStatement.setString(6, edate);
					callableStatement.setString(7, str[7]);
					callableStatement.setString(8, str[8]);
					callableStatement.registerOutParameter(9, OracleTypes.CURSOR);
					callableStatement.execute();
					ResultSet resultSet=(ResultSet) callableStatement.getObject(9);
					ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
					int column = resultSetMetaData.getColumnCount();
					int i = 1;
					while(resultSet.next())
					{
						
						i = 1;
						map = new HashMap<String, String>();
						while(i!=column+1)
						{
							
							String columnName = resultSetMetaData.getColumnName(i);
							map.put(columnName, resultSet.getString(columnName));
							i++;
						}
						list.add(map);
					}
					return list;
				}}
				return list;
			}
		}
		
		public ArrayList<Map<String, String>> currentBlotterOrder(HttpServletRequest request) throws SQLException {		
			HttpSession session = request.getSession();
			Connection connection = ConnectionFactory.getConnection();
			String uname= (String) session.getAttribute("uname");
			ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
			Map<String, String> map = new HashMap<String, String>();
			CallableStatement callableStatement = connection.prepareCall("call currentBlotterOrder(?,?)");
			callableStatement.setString(1,uname);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			callableStatement.execute();
			ResultSet resultSet=(ResultSet) callableStatement.getObject(2);
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int column = resultSetMetaData.getColumnCount();
			int i = 1;
			while(resultSet.next())
			{
				
				i = 1;
				map = new HashMap<String, String>();
				while(i!=column+1)
				{
					
					String columnName = resultSetMetaData.getColumnName(i);
					map.put(columnName, resultSet.getString(columnName));
					i++;
				}
				list.add(map);
			}
			return list;
		}
		
		public ArrayList<Map<String, String>> blotterTrade(String[] str) throws SQLException {
			return null;
			/*ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
			Map<String, String> map = new HashMap<String, String>();
			SessionFactory sf=HibernateUtil.getSessionFactory();
			Session ses=sf.openSession();
			CallableStatement callableStatement = ses.connection().prepareCall("call blotterTrade(?,?,?,?,?,?)");
			callableStatement.setString(1, "Bond");
			callableStatement.setString(2,str[1]);
			callableStatement.setString(3, str[2]);
			callableStatement.setString(4, str[3]);
			callableStatement.setString(5, str[4]);
			callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
			callableStatement.execute();
			ResultSet resultSet=(ResultSet) callableStatement.getObject(6);
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int column = resultSetMetaData.getColumnCount();
			int i = 1;
			while(resultSet.next())
			{
				
				i = 1;
				map = new HashMap<String, String>();
				while(i!=column+1)
				{
					
					String columnName = resultSetMetaData.getColumnName(i);
					map.put(columnName, resultSet.getString(columnName));
					i++;
				}
				list.add(map);
			}
			return list;*/
		}



		public ArrayList<Map<String, String>> currentBlotterTrade() throws HibernateException, SQLException {
			return null;
			/*ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
			Map<String, String> map = new HashMap<String, String>();
			SessionFactory sf=HibernateUtil.getSessionFactory();
			Session ses=sf.openSession();
			CallableStatement callableStatement = ses.connection().prepareCall("call currentBlotterTrade(?)");
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.execute();
			ResultSet resultSet=(ResultSet) callableStatement.getObject(1);
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int column = resultSetMetaData.getColumnCount();
			int i = 1;
			while(resultSet.next())
			{
				
				i = 1;
				map = new HashMap<String, String>();
				while(i!=column+1)
				{
					
					String columnName = resultSetMetaData.getColumnName(i);
					map.put(columnName, resultSet.getString(columnName));
					i++;
				}
				list.add(map);
			}
			return list;*/
		}
		
	public static void main(String[] args) throws HibernateException, SQLException {
		//String str[] = {"Bond","viraj","viraj"};
		
		//get trade ID
		/*String tradeId = new DAO().getTradeId();
		System.out.println(tradeId);*/
		
		//Market Data
		/*ArrayList<ArrayList<Map<String, String>>> list = new DAO().getBuyData();
		
		ArrayList<Map<String, String>> list1 = list.get(0);
		ArrayList<Map<String, String>> list2 = list.get(1);
		
		for(Map<String, String> map:list2)
		{
			System.out.println(map);
		}*/
		
		// Sell Data
	/*	ArrayList<ArrayList<Map<String, String>>> list = new DAO().getSellData("viraj");
		ArrayList<Map<String, String>> list1 = list.get(0);
		ArrayList<Map<String, String>> list2 = list.get(1);
		for(Map<String, String> map:list2)
		{
			System.out.println(map);
		}*/
		
		//insertOrder
		String str[]={"5","viraj","987654321","buy","9.5","2","8.5","10000","10.0","yes","2013-03-03"};
		
		new DAO().insertOrder(str);
		
		// update Price
		/*String str[]={"6","123456789","50","100"};
		new DAO().updatePrice(str);*/
		
		// populateRegister
/*ArrayList<ArrayList<Map<String, String>>> list = new DAO().populateRegister();
		
		
		ArrayList<Map<String, String>> list1 = list.get(5);
		
		for(Map<String, String> map:list1)
		{
			System.out.println(map);
		}*/
		
		// getBuyData
/*		String str[]={"10","viraj"};
ArrayList<ArrayList<Map<String, String>>> list = new DAO().getBuyData(str);
		
		ArrayList<Map<String, String>> list1 = list.get(0);
		ArrayList<Map<String, String>> list2 = list.get(1);
		ArrayList<Map<String, String>> list3 = list.get(2);
		
		for(Map<String, String> map:list2)
		{
			System.out.println(map);
		}*/
		
	}



	public void EditOrderDetails(String[] str) throws SQLException {
		ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Map<String, String> map = new HashMap<String, String>();
		Connection connection = ConnectionFactory.getConnection();
		CallableStatement callableStatement =connection.prepareCall("call EditOrder(?,?,?,?)");
		callableStatement.setString(1, str[1]);
		callableStatement.setString(2, str[2]);
		callableStatement.setString(3, str[3]);
		callableStatement.setString(4, str[4]);
		callableStatement.execute();
	}



	public void CancelOrderDetails(String[] str) throws SQLException {
		ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Map<String, String> map = new HashMap<String, String>();
		Connection connection = ConnectionFactory.getConnection();
		CallableStatement callableStatement =connection.prepareCall("call CancelOrder(?,?)");
		callableStatement.setString(1, str[1]);
		callableStatement.setString(2, str[2]);
		callableStatement.execute();
		
	}
}
