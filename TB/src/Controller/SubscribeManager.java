package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import dbConnection.TravelDatabase;
import Model.Subscribe;

public class SubscribeManager 
{
	//insert
	public int insertSubscribe(Subscribe subsMan) throws ClassNotFoundException, SQLException
	{
		int id = -1;
		
		String sql = "insert into subscribe (email) values(?)";
		
		if(findSubscribe(subsMan.getEmail())==false)
		{
			Connection conn = TravelDatabase.doConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, subsMan.getEmail());
			
			id = preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			
			if(rs.next())
			{
				id = rs.getInt(1);
			}
			
			preparedStatement.close();
			conn.close();
			
		}
		
		return id;
		
	}
	
	//search
	public int findSubscribeId(String email) throws ClassNotFoundException, SQLException
	{
		int id = 0;
		
		String sql = "select subsId from subscribe where email = ?";
		
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, email);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
			id = resultSet.getInt("subsId");
		
		resultSet.close();
		preparedStatement.close();
		conn.close();
		
		return id;
	}
	
	//search
	public boolean findSubscribe(String email) throws ClassNotFoundException, SQLException
	{
		boolean success = false;
		
		String sql = "select email from subscribe where email = ?";
		
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, email);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
			success = true;
		
		resultSet.close();
		preparedStatement.close();
		conn.close();
		
		return success;
	}
	
	
	
	//view
	public ArrayList<Subscribe> selectStudent (Subscribe subObj) throws ClassNotFoundException, SQLException
	{
		Connection conn = TravelDatabase.doConnection();
		ArrayList<Subscribe> arraySubscribeObj = new ArrayList<Subscribe>();
		PreparedStatement ps = conn.prepareStatement("Select id, email FROM subscribe WHERE id = ?");
	
		ResultSet rs = ps.executeQuery();
		if(rs.isBeforeFirst())
		{
			while(rs.next())
			{
				
				String email = rs.getString("email");
				
				Subscribe subObject = new Subscribe();
			    subObject.setEmail(email);
				
				arraySubscribeObj.add(subObject);
			}
		}
		else
		{
			System.out.println("No result found!");
		}
		ps.close();
		rs.close();
		conn.close();
		return arraySubscribeObj;
	}
	
	//delete
	public int deleteSubscribe(Subscribe subModel) throws SQLException, ClassNotFoundException
	{
		String sql = "DELETE from subscribe Where id = ?";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();
		
		preparedStatement.close();
		resultSet.close();
		conn.close();
		return 0;
		
	}
	
	//update
	public int updateSubscribe(Subscribe subModel) throws ClassNotFoundException, SQLException
	{
		int success = -1;
		
		String sql = "UPDATE into subscribe (email)values(?)";
		
		if(findSubscribe(subModel.getEmail())==false)
		{
			Connection conn = TravelDatabase.doConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, subModel.getEmail());
		
			
			success = preparedStatement.executeUpdate();
			preparedStatement.close();
			conn.close();
			
		}
		
		return success;
	}
	
	
}
