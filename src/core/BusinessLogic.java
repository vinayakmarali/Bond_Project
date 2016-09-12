package core;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class BusinessLogic {
    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    //private static String url = "tcp://localhost:61616";

    // Name of the queue we will receive messages from
    private static String subject1 = "BondACTIVEMQ";
    private static String subject2 = "BondLoginResponse";
    private static String subject3 = "BondMarketResponse";
    private static String subject4 = "BondSellResponse";
    private static String subject5 = "BondPopulateRegisterResponse";
    private static String subject9= "BondBlotterResponse";
    private static String subject10 = "BondBuyResponse";
    private static String subject11 = "BondMatchedOrderResponse";
    		void getMessage() throws JMSException
    	{
    			 // Getting JMS connection from the server
    	        ActiveMQConnectionFactory connectionFactory
    	            = new ActiveMQConnectionFactory(url);
    	        Connection connection = connectionFactory.createConnection();
    	        connection.start();

    	        // Creating session for seding messages
    	        Session session = connection.createSession(false,
    	            Session.AUTO_ACKNOWLEDGE);

    	        
    	        Destination destination = session.createQueue(subject1);

    	        // MessageConsumer is used for receiving (consuming) messages
    	        MessageConsumer consumer = session.createConsumer(destination);
    	        SampleListener listener=new SampleListener(session);
    	        consumer.setMessageListener(listener);

    	        connection.close();
    	}
    
    		
    		
    		
    		
    		void putTradeId(String str) throws JMSException
    		{

    	        // Getting JMS connection from the server and starting it
    	        ActiveMQConnectionFactory connectionFactory =
    	            new ActiveMQConnectionFactory(url);
    	        Connection connection = connectionFactory.createConnection();
    	        connection.start();

    	       
    	        Session session = connection.createSession(false,
    	            Session.AUTO_ACKNOWLEDGE);

    	        Destination destination = session.createQueue(subject3);

    	        // MessageProducer is used for sending messages (as opposed
    	        // to MessageConsumer which is used for receiving them)
    	        MessageProducer producer = session.createProducer(destination);

    	        // We will send a small text message saying 'Hello' in Japanese
    	       TextMessage message = session.createTextMessage(str);

    	        // Here we are sending the message!
    	        producer.send(message);
    	        System.out.println("Sent message '" + message.getText() + "'");

    	        connection.close();
    		}
    		
    	
    		
    		
    		void putPopulateRegister(ArrayList<ArrayList<Map<String, String>>> list) throws JMSException
    		{
    			 // Getting JMS connection from the server and starting it
    	        ActiveMQConnectionFactory connectionFactory =
    	            new ActiveMQConnectionFactory(url);
    	        Connection connection = connectionFactory.createConnection();
    	        connection.start();

    	       
    	        Session session = connection.createSession(false,
    	            Session.AUTO_ACKNOWLEDGE);

    	        Destination destination = session.createQueue(subject5);

    	        // MessageProducer is used for sending messages (as opposed
    	        // to MessageConsumer which is used for receiving them)
    	        MessageProducer producer = session.createProducer(destination);

    	        // We will send a small text message saying 'Hello' in Japanese
    	        ObjectMessage message = session.createObjectMessage();
    	        message.setObjectProperty("list", list);
    	       //TextMessage message = session.createTextMessage(str);

    	        // Here we are sending the message!
    	        producer.send(message);
    	        //System.out.println("Sent message '" + message.getText() + "'");

    	        connection.close();
    		}
    		
    		void putBlotterOrderData(ArrayList<Map<String, String>> list) throws JMSException
    		{

    	        // Getting JMS connection from the server and starting it
    	        ActiveMQConnectionFactory connectionFactory =
    	            new ActiveMQConnectionFactory(url);
    	        Connection connection = connectionFactory.createConnection();
    	        connection.start();
    	       
    	        Session session = connection.createSession(false,
    	            Session.AUTO_ACKNOWLEDGE);

    	        Destination destination = session.createQueue(subject9);

    	        // MessageProducer is used for sending messages (as opposed
    	        // to MessageConsumer which is used for receiving them)
    	        MessageProducer producer = session.createProducer(destination);

    	        // We will send a small text message saying 'Hello' in Japanese
    	        ObjectMessage message = session.createObjectMessage();
    	        message.setObjectProperty("list", list);
    	       //TextMessage message = session.createTextMessage(str);

    	        // Here we are sending the message!
    	        producer.send(message);
    	        //System.out.println("Sent message '" + message.getText() + "'");

    	        connection.close();
    		}
    		
    		
    		void putCusipBuyData(Map<String, Object> map) throws JMSException
    		{

    	        // Getting JMS connection from the server and starting it
    	        ActiveMQConnectionFactory connectionFactory =
    	            new ActiveMQConnectionFactory(url);
    	        Connection connection = connectionFactory.createConnection();
    	        connection.start();

    	       
    	        Session session = connection.createSession(false,
    	            Session.AUTO_ACKNOWLEDGE);

    	        Destination destination = session.createQueue(subject10);

    	        // MessageProducer is used for sending messages (as opposed
    	        // to MessageConsumer which is used for receiving them)
    	        MessageProducer producer = session.createProducer(destination);

    	        // We will send a small text message saying 'Hello' in Japanese
    	        ObjectMessage message = session.createObjectMessage();
    	        message.setObjectProperty("map", map);
    	       //TextMessage message = session.createTextMessage(str);

    	        // Here we are sending the message!
    	        producer.send(message);
    	        //System.out.println("Sent message '" + message.getText() + "'");

    	        connection.close();
    		}
    		
    		void putCusipSellData(Map<String, Object> map) throws JMSException
    		{

    	        // Getting JMS connection from the server and starting it
    	        ActiveMQConnectionFactory connectionFactory =
    	            new ActiveMQConnectionFactory(url);
    	        Connection connection = connectionFactory.createConnection();
    	        connection.start();

    	       
    	        Session session = connection.createSession(false,
    	            Session.AUTO_ACKNOWLEDGE);

    	        Destination destination = session.createQueue(subject4);

    	        // MessageProducer is used for sending messages (as opposed
    	        // to MessageConsumer which is used for receiving them)
    	        MessageProducer producer = session.createProducer(destination);

    	        // We will send a small text message saying 'Hello' in Japanese
    	        ObjectMessage message = session.createObjectMessage();
    	        message.setObjectProperty("map", map);
    	       //TextMessage message = session.createTextMessage(str);

    	        // Here we are sending the message!
    	        producer.send(message);
    	        //System.out.println("Sent message '" + message.getText() + "'");

    	        connection.close();
    		}
    		
    		public void putBlotterTradeData(ArrayList<Map<String, String>> list) throws JMSException {

    	        // Getting JMS connection from the server and starting it
    	        ActiveMQConnectionFactory connectionFactory =
    	            new ActiveMQConnectionFactory(url);
    	        Connection connection = connectionFactory.createConnection();
    	        connection.start();
    	       
    	        Session session = connection.createSession(false,
    	            Session.AUTO_ACKNOWLEDGE);

    	        Destination destination = session.createQueue(subject9);

    	        // MessageProducer is used for sending messages (as opposed
    	        // to MessageConsumer which is used for receiving them)
    	        MessageProducer producer = session.createProducer(destination);

    	        // We will send a small text message saying 'Hello' in Japanese
    	        ObjectMessage message = session.createObjectMessage();
    	        message.setObjectProperty("list", list);
    	       //TextMessage message = session.createTextMessage(str);

    	        // Here we are sending the message!
    	        producer.send(message);
    	        //System.out.println("Sent message '" + message.getText() + "'");

    	        connection.close();
    			
    		}

public void putOrderDetails(ArrayList<ArrayList<Map<String, String>>> list) throws JMSException {
    			
    			// Getting JMS connection from the server and starting it
    	        ActiveMQConnectionFactory connectionFactory =
    	            new ActiveMQConnectionFactory(url);
    	        Connection connection = connectionFactory.createConnection();
    	        connection.start();

    	       
    	        Session session = connection.createSession(false,
    	            Session.AUTO_ACKNOWLEDGE);

    	        Destination destination = session.createQueue(subject11);

    	        // MessageProducer is used for sending messages (as opposed
    	        // to MessageConsumer which is used for receiving them)
    	        MessageProducer producer = session.createProducer(destination);

    	        // We will send a small text message saying 'Hello' in Japanese
    	        ObjectMessage message = session.createObjectMessage();
    	        message.setObjectProperty("list", list);
    	       //TextMessage message = session.createTextMessage(str);

    	        // Here we are sending the message!
    	        producer.send(message);
    	        //System.out.println("Sent message '" + message.getText() + "'");

    	        connection.close();
    		}
    		
    	
    
    public static void main(String[] args) throws JMSException {
        // Getting JMS connection from the server
    	        ActiveMQConnectionFactory connectionFactory
    	            = new ActiveMQConnectionFactory(url);
    	        Connection connection = connectionFactory.createConnection();
    	        connection.start();

    	        // Creating session for seding messages
    	        Session session = connection.createSession(false,
    	            Session.AUTO_ACKNOWLEDGE);

    	        // Getting the queue 'TESTQUEUE'
    	        Destination destination = session.createQueue(subject1);
    	   
    	        // MessageConsumer is used for receiving (consuming) messages
    	        MessageConsumer consumer = session.createConsumer(destination);
    	        SampleListener listener=new SampleListener(session);
    	        consumer.setMessageListener(listener);

    	     
    	       
    	        
    }
}