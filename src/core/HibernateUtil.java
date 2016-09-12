package core;

import org.hibernate.*;
import org.hibernate.cfg.*;
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	static {
	sessionFactory=new Configuration().configure().buildSessionFactory();
		}
	
		public static SessionFactory getSessionFactory() {
		// Alternatively, you could look up in JNDI here
		return sessionFactory;
		}
		
		public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
		}
		}

