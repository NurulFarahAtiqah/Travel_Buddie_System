package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import dbConnection.TravelDatabase;
import Model.Hotel;

public class HotelManager 
{
	//viewSelectedState
	public String selectHotelNameFromID(int state) throws ClassNotFoundException, SQLException
	{
		String nameHotel = null;
		String sql = "select name from hotel where id = ?";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, state);

		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			nameHotel = resultSet.getString("name");
		}

		resultSet.close();
		preparedStatement.close();
		conn.close();

		return nameHotel;
	}
	
	//viewSelectedHotel
		public ArrayList<Hotel> viewSelectedHotelFromPath(String path) throws ClassNotFoundException, SQLException
		{
			ArrayList<Hotel> listHotel = new ArrayList<Hotel>();
			String sql = "select id,name,price, state, path from hotel where path = ?";
			Connection conn = TravelDatabase.doConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, path);

			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String priceString = resultSet.getString("price");
				Double price = Double.parseDouble(priceString);
				String state = resultSet.getString("state");
			
				Hotel hotel = new Hotel();
				hotel.setId(id);
				hotel.setName(name);
				hotel.setPrice(price);
				hotel.setState(state);
				hotel.setPath(path);
				listHotel.add(hotel);

			}

			resultSet.close();
			preparedStatement.close();
			conn.close();

			return listHotel;
		}
	
	//viewAllState
	public ArrayList<String> viewAllStateHotel() throws ClassNotFoundException, SQLException
	{
		ArrayList<String> stateHotel = new ArrayList<String>();
		String sql = "select state from hotel";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			stateHotel.add(resultSet.getString("state"));

		}

		resultSet.close();
		preparedStatement.close();
		conn.close();

		return stateHotel;
	}

	//viewSelectedState
	public ArrayList<String> viewSelectedStateHotel(String state) throws ClassNotFoundException, SQLException
	{
		ArrayList<String> stateHotel = new ArrayList<String>();
		String sql = "select state from hotel where state = ?";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, state);

		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			stateHotel.add(resultSet.getString("state"));
		}

		resultSet.close();
		preparedStatement.close();
		conn.close();

		return stateHotel;
	}

	//viewSelectedHotel
	public ArrayList<Hotel> viewSelectedHotel(String state) throws ClassNotFoundException, SQLException
	{
		ArrayList<Hotel> listHotel = new ArrayList<Hotel>();
		String sql = "select id,name,price,path from hotel where state = ?";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, state);

		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String priceString = resultSet.getString("price");
			Double price = Double.parseDouble(priceString);
			String path = resultSet.getString("path");
		
			Hotel hotel = new Hotel();
			hotel.setId(id);
			hotel.setName(name);
			hotel.setPrice(price);
			hotel.setPath(path);
			listHotel.add(hotel);

		}

		resultSet.close();
		preparedStatement.close();
		conn.close();

		return listHotel;
	}

	//insert
	public int insertHotel(Hotel hotelModel) throws ClassNotFoundException, SQLException
	{
		int success = -1;

		String sql = "insert into hotel(name, price, roomType, description, state)values(?,?,?,?,?)";

		if(findHotel(hotelModel.getName())==false)
		{
			Connection conn = TravelDatabase.doConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, hotelModel.getName());
			preparedStatement.setDouble(2, hotelModel.getPrice());
//			preparedStatement.setString(3, hotelModel.getRoomType());
//			preparedStatement.setString(4, hotelModel.getDescription());
			preparedStatement.setString(5, hotelModel.getState());


			success = preparedStatement.executeUpdate();
			preparedStatement.close();
			conn.close();

		}

		return success;

	}


	//search
	public boolean findHotel(String name) throws ClassNotFoundException, SQLException
	{
		boolean success = false;

		String sql = "select name from hotel where name = ?";

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
	public ArrayList<Hotel> selectStudent (Hotel hotelObj) throws ClassNotFoundException, SQLException
	{
		Connection conn = TravelDatabase.doConnection();
		ArrayList<Hotel> arrayHotObj = new ArrayList<Hotel>();
		PreparedStatement ps = conn.prepareStatement("Select id, name, price, roomType, description, state FROM hotel WHERE name = ?");
		ps.setString(1, hotelObj.getName());

		ResultSet rs = ps.executeQuery();
		if(rs.isBeforeFirst())
		{
			while(rs.next())
			{

				String name = rs.getString("name");
				double price = rs.getDouble("Hotel Price");
				String roomType = rs.getString("Room Type");
				String description = rs.getString("Hotel Detail");
				String state = rs.getString("Hotel State");

				Hotel hotObj = new Hotel();
				hotObj.setName(name);
				hotObj.setPrice(price);
//				hotObj.setRoomType(roomType);
//				hotObj.setDescription(description);
				hotObj.setState(state);
				arrayHotObj.add(hotObj);
			}
		}
		else
		{
			System.out.println("No result found!");
		}
		ps.close();
		rs.close();
		conn.close();
		return arrayHotObj;
	}

	//delete
	public int deleteHotel(Hotel hotModel) throws SQLException, ClassNotFoundException
	{
		String sql = "DELETE from Hotel Where name = ?";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, hotModel.getName());
		ResultSet resultSet = preparedStatement.executeQuery();

		preparedStatement.close();
		resultSet.close();
		conn.close();
		return 0;

	}

	//update
	public int updateHotel(Hotel hotelModel) throws ClassNotFoundException, SQLException
	{
		int success = -1;

		String sql = "UPDATE into hotel(name, price, roomType, description, state)values(?,?,?,?,?)";

		if(findHotel(hotelModel.getName())==false)
		{
			Connection conn = TravelDatabase.doConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, hotelModel.getName());
			preparedStatement.setDouble(2, hotelModel.getPrice());
//			preparedStatement.setString(3, hotelModel.getRoomType());
//			preparedStatement.setString(4, hotelModel.getDescription());
			preparedStatement.setString(5, hotelModel.getState());

			success = preparedStatement.executeUpdate();
			preparedStatement.close();
			conn.close();

		}

		return success;
	}


	public boolean findHotelState(String state) throws ClassNotFoundException, SQLException
	{
		boolean success = false;

		String sql = "select state from hotel where state = ?";

		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, state);

		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
			success = true;

		resultSet.close();
		preparedStatement.close();
		conn.close();

		return success;
	}


}
