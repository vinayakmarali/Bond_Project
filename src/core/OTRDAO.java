package core;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OTRDAO {
	OTRIssues getOTR(int number)
	{
		try {
			Connection connection = ConnectionFactory.getConnection();
			Statement statement = connection.createStatement();
			int random = (int)(Math.random()*10);
			
			if(random==0)
				random=10;
			ResultSet resultSet = statement.executeQuery("select id,cusip,bid,ask,bid_yield,ask_yield,coupon from OTRIssues where id="+random);
			
			resultSet.next();
			OTRIssues otrIssues = new OTRIssues(resultSet.getInt("id"),resultSet.getString("cusip"),resultSet.getDouble("bid"),resultSet.getDouble("ask"),resultSet.getDouble("bid_yield"),resultSet.getDouble("ask_yield"),resultSet.getDouble("coupon"));
			
			if(number%2!=0)
			{
				PreparedStatement preparedStatement = connection.prepareStatement("update otrIssues set BID_PREV=?,BID_PREV_YIELD=? where id=?");
				preparedStatement.setDouble(1, otrIssues.getBid());
				preparedStatement.setDouble(2, otrIssues.getBid_yield());
				preparedStatement.setInt(3, otrIssues.getId());
				preparedStatement.executeUpdate();
			}
			else
			{
				PreparedStatement preparedStatement = connection.prepareStatement("update otrIssues set ASK_PREV=?,ASK_PREV_YIELD=? where id=?");
				preparedStatement.setDouble(1, otrIssues.getAsk());
				preparedStatement.setDouble(2, otrIssues.getAsk_yield());
				preparedStatement.setInt(3, otrIssues.getId());
				preparedStatement.executeUpdate();
			}
			
			return otrIssues;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	void updateOtr()
	{
		int number=1;
		
		RandomGeneration randomGeneration = new RandomGeneration();
		Connection connection = ConnectionFactory.getConnection();
		while(true)
		{
			OTRIssues o = randomGeneration.getOTR(number);
			number++;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
				try {
					
					PreparedStatement preparedStatement = connection.prepareStatement("update OTRISSUES set bid=?,ask=?,bid_yield=?,ask_yield=? where cusip=?");
					preparedStatement.setDouble(1, o.getBid());
					preparedStatement.setDouble(2, o.getAsk());
					preparedStatement.setDouble(3, o.getBid_yield());
					preparedStatement.setDouble(4, o.getAsk_yield());
					preparedStatement.setString(5, o.getCusip());
					preparedStatement.executeUpdate();
				} catch (SQLException e) {
				e.printStackTrace();
			}
				
		
		}
	}
	
	public static void main(String[] args) {
		new OTRDAO().updateOtr();
	}

}
