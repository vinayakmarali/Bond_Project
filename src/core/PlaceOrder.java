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

public class PlaceOrder {
	
	 // URL of the JMS server. DEFAULT_BROKER_URL will just mean
    // that JMS server is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	  // Name of the queue we will be sending messages to
    private static String subject1 = "BondACTIVEMQ";
    
    public static void placeOrder(String brokerId,String cusip,String direction,String coupon,String maturity,String notional,String price,String yield,String status,String order_date,String settlement_date,String order_time, String issuer, String tenor, String value_till, String user_order) {

		
		try {
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
			TextMessage message = session.createTextMessage("5|"+brokerId+"|"+cusip+"|"+direction+"|"+coupon+"|"+maturity+"|"+notional+"|"+price+"|"+yield+"|"+status+"|"+order_date+"|"+settlement_date+"|"+order_time+"|"+issuer+"|"+tenor+"|"+value_till+"|"+user_order);

			// Here we are sending the message!
			producer.send(message);
			System.out.println("Sent message '" + message.getText() + "'");

			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

	}
	
	

}
