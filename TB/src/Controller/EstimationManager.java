package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Model.Estimation;
import Model.Hotel;
import Model.Session;
import dbConnection.TravelDatabase;

public class EstimationManager {
	
	//update
	public int updateEstimation(int quantity,Double totalPriceNew, int id) throws SQLException, ClassNotFoundException
	{	
		int status = 0;
		int getId =0;
		
		System.out.println("updateEstimation quantity="+quantity +" id="+id +" totalPriceNew="+totalPriceNew);
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement("UPDATE estimation SET estimation.quantity=? , estimation.totalEstimation=? WHERE estimation.id = ?",Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, quantity);
		preparedStatement.setDouble(2, totalPriceNew);
		preparedStatement.setInt(3, id);

		status = preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if(rs.next())
			getId = rs.getInt(1);

		System.out.println("getId="+getId);
		System.out.println("status="+status);
		preparedStatement.close();
		conn.close();

		return status;

	}
	
	//delete
	public int deleteEstimation( int id) throws SQLException, ClassNotFoundException
	{	
		int status = 0;
		int getId =0;
		
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement("delete from estimation where id=?",Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, id);
		
		 status = preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		if(rs.next())
			getId = rs.getInt(1);
		

		System.out.println("getId="+getId);
		System.out.println("status="+status);
		preparedStatement.close();
		conn.close();

		return status;

	}
	
	//insert
	public int insertEstimation(Estimation estimation) throws SQLException, ClassNotFoundException
	{
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement("insert into estimation (totalEstimation, id_food, id_Hotel, id_Attraction, id_session, quantity) values(?,?,?,?,?,?)");
		preparedStatement.setDouble(1, estimation.getTotalEstimation());
		preparedStatement.setInt(2, estimation.getId_food());
		preparedStatement.setInt(3, estimation.getId_Hotel());
		preparedStatement.setInt(4, estimation.getId_Attraction());
		preparedStatement.setInt(5, estimation.getId_session());
		preparedStatement.setInt(6, estimation.getQuantity());
		
		int status = preparedStatement.executeUpdate();

		preparedStatement.close();
		conn.close();

		return status;

	}
	
	//view 
	public ArrayList<Estimation> viewEstimation(Session session) throws ClassNotFoundException, SQLException
	{
		ArrayList<Estimation> listEstimation = new ArrayList<Estimation>();
		String sql = "select totalEstimation,id_food,id_Hotel, id_Attraction, id_session, id, quantity from estimation where id_session = ?";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, session.getId());

		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			Double totalEstimation = resultSet.getDouble("totalEstimation");
			int id_food = resultSet.getInt("id_food");
			int id_Hotel = resultSet.getInt("id_Hotel");
			int id_Attraction = resultSet.getInt("id_Attraction");
			int id_session = resultSet.getInt("id_session");
			int id = resultSet.getInt("id");
			int quantity = resultSet.getInt("quantity");
		
			Estimation estimation = new Estimation();
			estimation.setTotalEstimation(totalEstimation);
			estimation.setId_food(id_food);
			estimation.setId_Hotel(id_Hotel);
			estimation.setId_Attraction(id_Attraction);
			estimation.setId_session(id_session);
			estimation.setId(id);
			estimation.setQuantity(quantity);
			listEstimation.add(estimation);

		}

		resultSet.close();
		preparedStatement.close();
		conn.close();

		return listEstimation;
	}
	
	//Calculate
	public Double calculateEstimation(Session session) throws ClassNotFoundException, SQLException
	{
		Double totalEstimation = 0.0;
		
		String sql = "select SUM(totalEstimation) from estimation where id_session = ?";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, session.getId());

		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			 totalEstimation = resultSet.getDouble("SUM(totalEstimation)");
		}
		resultSet.close();
		preparedStatement.close();
		conn.close();

		return totalEstimation;
	}
	
	//Find Email 
	public String findEmail(Session session) throws ClassNotFoundException, SQLException
	{
		String email = null;
		
		String sql = "select e.id_session, s.id_subscribe, sb.email from estimation e, session s , subscribe sb where e.id_session = s.id and s.id_subscribe = sb.subsId and e.id_session =  ?";
		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, session.getId());

		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			email = resultSet.getString("email");
		}
		resultSet.close();
		preparedStatement.close();
		conn.close();

		return email;
	}

}
