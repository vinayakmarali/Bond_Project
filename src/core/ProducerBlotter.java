package core;
import java.util.ArrayList;
import java.util.Map;

import javax.jms.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.catalina.connector.Request;


public class ProducerBlotter {
    // URL of the JMS server. DEFAULT_BROKER_URL will just mean
    // that JMS server is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // Name of the queue we will be sending messages to
    private static String subject1 = "BondACTIVEMQ";
    private static String subject2 = "BondBlotterResponse";

    void putBlotterOrder(Order o, HttpServletRequest request) throws JMSException{

        // Getting JMS connection from the server and starting it
        ActiveMQConnectionFactory connectionFactory =
            new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

       
        Session session = connection.createSession(false,
            Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(subject1);

        // MessageProducer is used for sending messages (as opposed
        // to MessageConsumer which is used for receiving them)
        MessageProducer producer = session.createProducer(destination);
        HttpSession ses  = request.getSession();
        // We will send a small text message saying 'Hello' in Japanese
        TextMessage message = session.createTextMessage("8|"+request.getParameter("direction")+"|"+request.getParameter("coupon1")+"|"+request.getParameter("maturity1")+"|"+ses.getAttribute("uname")+"|"+request.getParameter("sdate")+"|"+request.getParameter("edate")+"|"+request.getParameter("coupon2")+"|"+request.getParameter("maturity2"));

        // Here we are sending the message!
        producer.send(message);
        System.out.println("Sent message '" + message.getText() + "'");

        connection.close();
        
     }
    
    void putEditBlotterOrder(Order o, HttpServletRequest request) throws JMSException{

        // Getting JMS connection from the server and starting it
        ActiveMQConnectionFactory connectionFactory =
            new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

       
        Session session = connection.createSession(false,
            Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(subject1);

        // MessageProducer is used for sending messages (as opposed
        // to MessageConsumer which is used for receiving them)
        MessageProducer producer = session.createProducer(destination);
        HttpSession ses  = request.getSession();
        // We will send a small text message saying 'Hello' in Japanese
        String c =request.getParameter("r1");
        TextMessage message = session.createTextMessage("12|"+ses.getAttribute("uname")+"|"+c+"|"+request.getParameter("notional."+c)+"|"+request.getParameter("price."+c));

        // Here we are sending the message!
        producer.send(message);
        System.out.println("Sent message '" + message.getText() + "'");

        connection.close();
        
     }
    
    ArrayList<Map<String, String>> getBlotterOrder() throws JMSException
    {
    	ArrayList<Map<String, String>> list = null;
    	ActiveMQConnectionFactory connectionFactory
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
         list = (ArrayList<Map<String, String>>) objectMessage.getObjectProperty("list");
 }
     connection.close();
	return list;
}
    
    ArrayList<Map<String, String>> getCurrentBlotterOrder() throws JMSException
    {
    	ArrayList<Map<String, String>> list = null;
    	ActiveMQConnectionFactory connectionFactory
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
         list = (ArrayList<Map<String, String>>) objectMessage.getObjectProperty("list");
 }
     connection.close();
	return list;
}
    
    void putBlotterTrade(Trade t, HttpServletRequest request) throws JMSException{

        // Getting JMS connection from the server and starting it
        ActiveMQConnectionFactory connectionFactory =
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
        TextMessage message = session.createTextMessage("9|"+request.getParameter("direction")+"|"+request.getParameter("yield")+"|"+request.getParameter("sdate")+"|"+request.getParameter("edate"));

        // Here we are sending the message!
        producer.send(message);
        System.out.println("Sent message '" + message.getText() + "'");

        connection.close();
        
     }

	public ArrayList<Map<String, String>> getBlotterTrade() throws JMSException {
		ArrayList<Map<String, String>> list = null;
    	ActiveMQConnectionFactory connectionFactory
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
         list = (ArrayList<Map<String, String>>) objectMessage.getObjectProperty("list");
 }
     connection.close();
	return list;
	}

	public void putCancelBlotterOrder(Order o, HttpServletRequest request) throws JMSException {
		// Getting JMS connection from the server and starting it
        ActiveMQConnectionFactory connectionFactory =
            new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

       
        Session session = connection.createSession(false,
            Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(subject1);

        // MessageProducer is used for sending messages (as opposed
        // to MessageConsumer which is used for receiving them)
        MessageProducer producer = session.createProducer(destination);
        HttpSession ses  = request.getSession();
        // We will send a small text message saying 'Hello' in Japanese
        TextMessage message = session.createTextMessage("13|"+ses.getAttribute("uname")+"|"+request.getParameter("r1"));

        // Here we are sending the message!
        producer.send(message);
        System.out.println("Sent message '" + message.getText() + "'");

        connection.close();
		
	}
    
    }