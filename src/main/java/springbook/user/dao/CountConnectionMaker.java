package springbook.user.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

import lombok.Getter;

@Getter
public class CountConnectionMaker implements DataSource {

	int counter = 0;
//	private ConnectionMaker realCountConnectionMaker;
	private DataSource realdataSource;

/*	public void setCountConnectionMaker(ConnectionMaker realCountConnectionMaker) {
		this.realCountConnectionMaker = realCountConnectionMaker;
	}
*/
	public void setCountConnectionMaker(DataSource realCountConnectionMaker) {
		this.realdataSource = realCountConnectionMaker;
	}

	
	public Connection getConnection() throws SQLException {
		this.counter++;
		return realdataSource.getConnection();
	}


	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}


	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
