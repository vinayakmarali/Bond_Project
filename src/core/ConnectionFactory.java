package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory 
{
	
static Connection connection;
public static Connection getConnection()
{
	try 
	{
		Class.forName("oracle.jdbc.OracleDriver");
		connection=DriverManager.getConnection("jdbc:oracle:thin:@IL031:1521:xe","system","manager");
	}
	catch (ClassNotFoundException e) 
	{
		e.printStackTrace();
	}
	catch (SQLException e) 
	{
		e.printStackTrace();
	}
	return connection;
}

public static void closeConnection()
{
	if(connection!=null)
	{
		try 
		{
			connection.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		}
}

	public static void main(String[] args) 
	{
		System.out.println(new ConnectionFactory().getConnection());
	}
	
}
