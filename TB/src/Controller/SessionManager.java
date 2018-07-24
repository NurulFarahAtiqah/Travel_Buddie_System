package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import Model.Session;
import Model.Subscribe;
import dbConnection.TravelDatabase;

public class SessionManager {

	//insert
	public int insertLog(Session session) throws SQLException, ClassNotFoundException
	{
		int idSession = 0;

		Connection conn = TravelDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement("insert into session (id_subscribe, time, date) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, session.getId_subscribe());
		preparedStatement.setString(2, session.getTime());
		preparedStatement.setString(3, session.getDate());



		int status = preparedStatement.executeUpdate();

		if (status != 0)
		{


			ResultSet rs2 = preparedStatement.getGeneratedKeys();

			if(rs2.next())
			{
				idSession = rs2.getInt(1);
			}
		}

		preparedStatement.close();
		conn.close();

		return idSession;

	}

}
