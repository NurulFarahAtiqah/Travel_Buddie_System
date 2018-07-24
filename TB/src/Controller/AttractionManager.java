package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import dbConnection.TravelDatabase;
import Model.Attraction;
import Model.Food;

public class AttractionManager 
{
	//selectNameFromID
	public String selectAttractionNameFromID(int id) throws ClassNotFoundException, SQLException
	{
		String nameAttraction = null;
		String sql = "select name from attraction where id=?";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			nameAttraction = resultSet.getString("name");
		}
		
		resultSet.close();
		preparedStatement.close();
		conn.close();
		
		return nameAttraction;
	}
	
	//viewSelectedAttraction
	public ArrayList<Attraction> viewSelectedAttractionFromPath(String path) throws ClassNotFoundException, SQLException
	{
		ArrayList<Attraction> listAttraction = new ArrayList<Attraction>();
		String sql = "select id,name,price,state,path from attraction where path = ?";
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
		
			Attraction attraction = new Attraction();
			attraction.setId(id);
			attraction.setName(name);
			attraction.setPrice(price);
			attraction.setState(state);
			attraction.setPath(path);
			listAttraction.add(attraction);

		}

		resultSet.close();
		preparedStatement.close();
		conn.close();

		return listAttraction;
	}
	
	//viewAllState
	public ArrayList<String> viewAllStateAttraction() throws ClassNotFoundException, SQLException
	{
		ArrayList<String> stateAttraction = new ArrayList<String>();
		String sql = "select state from attraction";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			stateAttraction.add(resultSet.getString("state"));
		}

		resultSet.close();
		preparedStatement.close();
		conn.close();

		return stateAttraction;
	}
	
	//viewSelectedState
	public ArrayList<String> viewSelectedStateAttraction(String state) throws ClassNotFoundException, SQLException
	{
		ArrayList<String> stateAttraction = new ArrayList<String>();
		String sql = "select state from attraction where state=?";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, state);

		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			stateAttraction.add(resultSet.getString("state"));
		}

		resultSet.close();
		preparedStatement.close();
		conn.close();

		return stateAttraction;
	}
	
	//viewSelectedAttraction
		public ArrayList<Attraction> viewSelectedAttraction(String state) throws ClassNotFoundException, SQLException
		{
			ArrayList<Attraction> listAttraction = new ArrayList<Attraction>();
			String sql = "select id,name,price,state,path from attraction where state = ?";
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
			
				Attraction attraction = new Attraction();
				attraction.setId(id);
				attraction.setName(name);
				attraction.setPrice(price);
				attraction.setPath(path);
				listAttraction.add(attraction);

			}

			resultSet.close();
			preparedStatement.close();
			conn.close();

			return listAttraction;
		}

	//insert
	public int insertAttraction(Attraction attractionModel) throws ClassNotFoundException, SQLException
	{
		int success = -1;

		String sql = "insert into foodraction (name, description, state, price,path)values(?,?,?,?,?)";

		if(findFood(attractionModel.getName())==false)
		{
			Connection conn = TravelDatabase.doConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, attractionModel.getName());
			preparedStatement.setString(2, attractionModel.getDescription());
			preparedStatement.setString(3, attractionModel.getState());
			preparedStatement.setDouble(4, attractionModel.getPrice());
			preparedStatement.setString(5, attractionModel.getPath());


			success = preparedStatement.executeUpdate();
			preparedStatement.close();
			conn.close();

		}

		return success;

	}


	//search
	public boolean findFood(String name) throws ClassNotFoundException, SQLException
	{
		boolean success = false;

		String sql = "select name from food where name = ?";

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
	public ArrayList<Food> selectStudent (Food foodObj) throws ClassNotFoundException, SQLException
	{
		Connection conn = TravelDatabase.doConnection();
		ArrayList<Food> arrayFoodObj = new ArrayList<Food>();
		PreparedStatement ps = conn.prepareStatement("Select id, name, description, state, estimatePrice FROM food WHERE id = ?");


		ResultSet rs = ps.executeQuery();
		if(rs.isBeforeFirst())
		{
			while(rs.next())
			{

				String name = rs.getString("name");
				String description = rs.getString("Foodraction Detail");
				String state = rs.getString("Foodraction State");
				double price = rs.getDouble("Price");

				Food foodObject = new Food();
				foodObject.setName(name);
				foodObject.setDescription(description);
				foodObject.setState(state);
				foodObject.setPrice(price);

				arrayFoodObj.add(foodObject);
			}
		}
		else
		{
			System.out.println("No result found!");
		}
		ps.close();
		rs.close();
		conn.close();
		return arrayFoodObj;
	}

	//delete
	public int deleteFood(Food foodModel) throws SQLException, ClassNotFoundException
	{
		String sql = "DELETE from food Where id = ?";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();

		preparedStatement.close();
		resultSet.close();
		conn.close();
		return 0;

	}

	//update
	public int updateFood(Food foodModel) throws ClassNotFoundException, SQLException
	{
		int success = -1;

		String sql = "UPDATE into food (name, description, state, estimatePrice)values(?,?,?,?)";

		if(findFood(foodModel.getName())==false)
		{
			Connection conn = TravelDatabase.doConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, foodModel.getName());
			preparedStatement.setString(2, foodModel.getDescription());
			preparedStatement.setString(3, foodModel.getState());
			preparedStatement.setDouble(4, foodModel.getPrice());


			success = preparedStatement.executeUpdate();
			preparedStatement.close();
			conn.close();

		}

		return success;
	}


	public boolean findFoodState(String state) throws ClassNotFoundException, SQLException
	{
		boolean success = false;

		String sql = "select state from food where state = ?";

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