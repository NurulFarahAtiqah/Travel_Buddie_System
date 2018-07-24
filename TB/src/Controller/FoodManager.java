package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import dbConnection.TravelDatabase;
import Model.Attraction;
import Model.Food;

public class FoodManager 
{
	//view
	public String selectFoodNameFromID(int id) throws ClassNotFoundException, SQLException
	{
		String nameHotel = null;
		String sql = "select name from food where id=?";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);

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
	
	//viewSelectedFood
	public ArrayList<Food> viewSelectedFoodFromPath(String path) throws ClassNotFoundException, SQLException
	{
		ArrayList<Food> listFood = new ArrayList<Food>();
		String sql = "select id,name,price,state,path from food where path = ?";
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
		
			Food food = new Food();
			food.setId(id);
			food.setName(name);
			food.setPrice(price);
			food.setState(state);
			food.setPath(path);
			listFood.add(food);

		}

		resultSet.close();
		preparedStatement.close();
		conn.close();

		return listFood;
	}
	
	//viewAllState
	public ArrayList<String> viewAllStateFood() throws ClassNotFoundException, SQLException
	{
		ArrayList<String> stateHotel = new ArrayList<String>();
		String sql = "select state from food";
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
	public ArrayList<String> viewSelectedStateFood(String state) throws ClassNotFoundException, SQLException
	{
		ArrayList<String> stateHotel = new ArrayList<String>();
		String sql = "select state from food where state=?";
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
	
	//viewSelectedFood
		public ArrayList<Food> viewSelectedFood(String state) throws ClassNotFoundException, SQLException
		{
			ArrayList<Food> listFood = new ArrayList<Food>();
			String sql = "select id,name,price,state,path from food where state = ?";
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
			
				Food food = new Food();
				food.setId(id);
				food.setName(name);
				food.setPrice(price);
				food.setPath(path);
				listFood.add(food);

			}

			resultSet.close();
			preparedStatement.close();
			conn.close();

			return listFood;
		}

	//insert
	public int insertFood(Food foodModel) throws ClassNotFoundException, SQLException
	{
		int success = -1;

		String sql = "insert into foodraction (name, description, state, estimatePrice)values(?,?,?,?)";

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