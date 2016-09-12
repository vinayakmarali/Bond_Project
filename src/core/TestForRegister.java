package core;

import java.util.ArrayList;
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

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class TestForRegister {
	
	 // URL of the JMS server. DEFAULT_BROKER_URL will just mean
    // that JMS server is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	  // Name of the queue we will be sending messages to
    private static String subject1 = "BondACTIVEMQ";
    private static String subject2 = "BondPopulateRegisterResponse";
    
    ArrayList<ArrayList<Map<String, String>>> getRegisterData() throws JMSException{
    	String str=null; 
    	ConnectionFactory connectionFactory
         = new ActiveMQConnectionFactory(url);
     Connection connection = connectionFactory.createConnection();
     connection.start();

     // Creating session for seding messages
     Session session = connection.createSession(false,
         Session.AUTO_ACKNOWLEDGE);

     // Getting the queue 'TESTQUEUE'
     Destination destination = session.createQueue(subject2);

     // MessageConsumer is used for receiving (consuming) messages
     MessageConsumer consumer = session.createConsumer(destination);

    
     Message message = consumer.receive();

     if (message instanceof ObjectMessage) {
         ObjectMessage objectMessage = (ObjectMessage)message;
         ArrayList<ArrayList<Map<String, String>>> list = (ArrayList<ArrayList<Map<String, String>>>) objectMessage.getObjectProperty("list");
         return list;
 		/*ArrayList<Map<String, String>> list1 = list.get(0);
 		ArrayList<Map<String, String>> list2 = list.get(1);
 		
 		for(Map<String, String> map:list1)
 		{
 			System.out.println(map.get("FIRM_ID"));
 		}
 			
         */
         /*str =  textMessage.getText();*/
     }
     connection.close();
     return null;
    }
	public static void registerModule()throws JMSException {
		
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
        TextMessage message = session.createTextMessage("7|populateRegister");

        // Here we are sending the message!
        producer.send(message);
        System.out.println("Sent message '" + message.getText() + "'");

        connection.close();
       

	}
	

}

