package core;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.activemq.broker.BrokerFilter;
import org.hibernate.HibernateException;

public class SampleListener implements MessageListener  {

private Session session;

public SampleListener(Session session) {
	
    this.session = session; 
}

public void onMessage(Message message) {
	try {
	
		if (message instanceof TextMessage) 
		{
			TextMessage textMessage = (TextMessage) message;
			
			/*HashMap<Integer, Object> hashMap = new HashMap<Integer, Object>();
			hashMap.put(1, "validateUser");
			hashMap.put(2, "getTradeId");
			hashMap.put(3, "getMarketData");
			hashMap.put(4, "getPortfolio");
			hashMap.put(5, "insertOrder");
			hashMap.put(6, "insertOrder");
			hashMap.put(7, "updatePriceBond");*/
			
			//System.out.println(textMessage.getText());
			String str[] = textMessage.getText().split("\\|");
			
		System.out.println("Hello Split");
			if(str.length!=0)
			{
				/*if(str[0].equals("1"))
				{
					try 
					{
						String validate = new DAO().validateUser(str);
						System.out.println(validate);
						new BusinessLogic().putMessageLogin(validate);
					} 
					catch (HibernateException e) 
					{
						e.printStackTrace();
					}
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
		
				}
				else */if(str[0].equals("2"))
				{
					String tradeId = new DAO().getTradeId();
					new BusinessLogic().putTradeId(tradeId);
				}
				
				else if(str[0].equals("4"))
				{
					Map<String, Object> map = new DAO().getCusipData(str);
					System.out.println("In sample "+map.toString());
					new BusinessLogic().putCusipSellData(map);
				}
				else if(str[0].equals("5"))
				{
					
					
					new DAO().insertOrder(str);
					
				}
				else if(str[0].equals("6"))
				{
					new DAO().updatePrice(str);
				}
				else if(str[0].equals("7"))
				{
					ArrayList<ArrayList<Map<String, String>>> list = new DAO().populateRegister();
					new BusinessLogic().putPopulateRegister(list);
				}
				else if(str[0].equals("8"))
				{
					try {
						ArrayList<Map<String, String>> list = new DAO().blotterOrder(str);
						System.out.println("list blot:"+list);
						new BusinessLogic().putBlotterOrderData(list);
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(str[0].equals("9"))
				{
					try {
						ArrayList<Map<String, String>> list = new DAO().blotterTrade(str);
						new BusinessLogic().putBlotterTradeData(list);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(str[0].equals("10"))
				{
					Map<String, Object> map = new DAO().getCusipData(str);
					System.out.println("In sample "+map.toString());
					new BusinessLogic().putCusipBuyData(map);
				}
				else if(str[0].equals("11"))
				{
					System.out.println("sample listener");
					ArrayList<ArrayList<Map<String, String>>> list = new DAO().getOrderDetails();
					new BusinessLogic().putOrderDetails(list);
				}
				else if (str[0].equals("12"))
				{
					try {
						new DAO().EditOrderDetails(str);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (str[0].equals("13"))
				{
					try {
						new DAO().CancelOrderDetails(str);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
			}
		}
	} 
	catch (JMSException e) 
	{
		System.out.println("Caught:" + e);
		e.printStackTrace();
	}
}

}