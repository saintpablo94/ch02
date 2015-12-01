package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.Statement;

import springbook.user.domain.User;

public class UserDao {

	private DataSource dataSource;
	
	
	public void setConnectionMaker(DataSource dataSource) {
		this.dataSource = dataSource; 
	}

	public void add(User user) throws SQLException {
		Connection conn = dataSource.getConnection();

		PreparedStatement stmt = conn
				.prepareStatement("insert into users(id,name,password) values(?,?,?) ");

		stmt.setString(1, user.getId());
		stmt.setString(2, user.getName());
		stmt.setString(3, user.getPassword());

		stmt.executeUpdate();

		stmt.close();
		conn.close();
	}	


	public User get(String id) throws SQLException {
		Connection conn = dataSource.getConnection();

		PreparedStatement stmt = conn
				.prepareStatement("select * from users where id = ? ");

		stmt.setString(1, id);

		ResultSet rs = stmt.executeQuery();

		User user = new User();
		if (rs.next()) {
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPasswordString(rs.getString("password"));
		}

		stmt.close();
		rs.close();
		conn.close();

		return user;
	}

	public void deleteAll() throws SQLException {
		Connection conn = dataSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement("delete from users");
		stmt.executeUpdate();
		
		stmt.close();
		conn.close();		
	}

	public int getCount() throws SQLException {
		Connection conn = dataSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement(""
				+ "select count(*) from users ");
		ResultSet rs = stmt.executeQuery();
		
		int count = -1;
		if(rs.next()) count = rs.getInt(1);
		
		rs.close();
		stmt.close();
		conn.close();
		
		return count;
	}
}
