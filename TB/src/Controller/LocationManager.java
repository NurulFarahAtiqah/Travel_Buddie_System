package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import dbConnection.TravelDatabase;
import Model.Location;

public class LocationManager 
{
	//insert
	public int insertLocation(Location locModel) throws ClassNotFoundException, SQLException
	{
		int success = -1;

		String sql = "insert into foodraction (name) values(?)";

		if(findLocation(locModel.getName())==false)
		{
			Connection conn = TravelDatabase.doConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, locModel.getName());

			success = preparedStatement.executeUpdate();
			preparedStatement.close();
			conn.close();

		}

		return success;

	}

	//search
	public boolean findLocation(String name) throws ClassNotFoundException, SQLException
	{
		boolean success = false;

		String sql = "select name from location where name = ?";

		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, name);

		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
			success = true;

		resultSet.close();
		preparedStatement.close();
		conn.close();

		return success;
	}

	//view
	public ArrayList<Location> selectStudent (Location locObj) throws ClassNotFoundException, SQLException
	{
		Connection conn = TravelDatabase.doConnection();
		ArrayList<Location> arrayLocationObj = new ArrayList<Location>();
		PreparedStatement ps = conn.prepareStatement("Select id, email FROM location WHERE id = ?");


		ResultSet rs = ps.executeQuery();
		if(rs.isBeforeFirst())
		{
			while(rs.next())
			{

				String name = rs.getString("name");

				Location locObject = new Location();
				locObject.setName(name);

				arrayLocationObj.add(locObject);
			}
		}
		else
		{
			System.out.println("No result found!");
		}
		ps.close();
		rs.close();
		conn.close();
		return arrayLocationObj;
	}

	//delete
	public int deleteLocation(Location locModel) throws SQLException, ClassNotFoundException
	{
		String sql = "DELETE from location Where id = ?";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();

		preparedStatement.close();
		resultSet.close();
		conn.close();
		return 0;

	}

	//update
	public int updateLocation(Location locModel) throws ClassNotFoundException, SQLException
	{
		int success = -1;

		String sql = "UPDATE into location (name)values(?)";

		if(findLocation(locModel.getName())==false)
		{
			Connection conn = TravelDatabase.doConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1,locModel.getName());


			success = preparedStatement.executeUpdate();
			preparedStatement.close();
			conn.close();

		}

		return success;
	}


}

