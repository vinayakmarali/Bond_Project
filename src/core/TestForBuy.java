package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class TestForBuy {
	
	 // URL of the JMS server. DEFAULT_BROKER_URL will just mean
    // that JMS server is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	  // Name of the queue we will be sending messages to
    private static String subject1 = "BondACTIVEMQ";
    private static String subject2 = "BondBuyResponse";
    
    void askBuyData(String userId,String cusip){
    	// Getting JMS connection from the server and starting it
        try {
			ConnectionFactory connectionFactory =
			    new ActiveMQConnectionFactory(url);
			Connection connection = connectionFactory.createConnection();
			connection.start();

      
			Session session = connection.createSession(false,
			    Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(subject1);

			// MessageProducer is used for sending messages (as opposed
			// to MessageConsumer which is used for receiving them)
			MessageProducer producer = session.createProducer(destination);

			// We will send a small text message saying 'Hello' in Japanese
			TextMessage message = session.createTextMessage("10|"+userId+"|"+cusip);

			// Here we are sending the message!
			producer.send(message);
			System.out.println("Sent message '" + message.getText() + "'");

			connection.close();
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
   Map<String, Object> getBuyData(HttpServletRequest request){
    	
    	ConnectionFactory connectionFactory
         = new ActiveMQConnectionFactory(url);
     Connection connection;
	ArrayList<ArrayList<Map<String, String>>> list = null;
	try {
		connection = connectionFactory.createConnection();
		 connection.start();
		 
		 // Creating session for sending messages
		 Session session = connection.createSession(false,
		     Session.AUTO_ACKNOWLEDGE);

		 // Getting the queue 'TESTQUEUE'
		 Destination destination = session.createQueue(subject2);

		 // MessageConsumer is used for receiving (consuming) messages
		 MessageConsumer consumer;
		 HttpSession session2 = request.getSession(true);
		 if(session2.getAttribute("consumerBuy")!=null)
			 consumer = (MessageConsumer) session2.getAttribute("consumerBuy");
		 else
		 {
			 consumer = session.createConsumer(destination);
		 	session2.setAttribute("consumerBuy", consumer);
		 }
		
		 Message message = consumer.receive();

		 if (message instanceof ObjectMessage) {
		     ObjectMessage objectMessage = (ObjectMessage)message;
		     
		     Map<String, Object> map =  (Map<String, Object>) objectMessage.getObjectProperty("map");
		    // System.out.println("In test for buy "+map.toString());
			return map;
		     
		}
		 connection.close();
	} catch (JMSException e) {
		
		e.printStackTrace();
	}
     
     
     return null;
    }
	public static void main(String[] args) throws JMSException {
		
		// Getting JMS connection from the server and starting it
        ConnectionFactory connectionFactory =
            new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

       
        Session session = connection.createSession(false,
            Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(subject1);

        // MessageProducer is used for sending messages (as opposed
        // to MessageConsumer which is used for receiving them)
        MessageProducer producer = session.createProducer(destination);

        // We will send a small text message saying 'Hello' in Japanese
        TextMessage message = session.createTextMessage("10|buy");

        // Here we are sending the message!
        producer.send(message);
        System.out.println("Sent message '" + message.getText() + "'");

        connection.close();
        ArrayList<ArrayList<Map<String, String>>> list =  new TestForMarket().getBuyData();
        ArrayList<Map<String, String>> list1 = list.get(0);
		ArrayList<Map<String, String>> list2 = list.get(1);
		
		for(Map<String, String> map:list1)
		{
			System.out.println(map.get("ISIN"));
		}

	}

}
