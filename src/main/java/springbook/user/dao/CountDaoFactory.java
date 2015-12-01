package springbook.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

@Configuration
public class CountDaoFactory {
	
	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setConnectionMaker(countconntionMaker());
		return userDao;
	}
	
	@Bean
	public CountConnectionMaker countconntionMaker() {
		CountConnectionMaker countConnectionMaker = new CountConnectionMaker();
		countConnectionMaker.setCountConnectionMaker(dataSource());
		return countConnectionMaker ;
	}

	@Bean
	public ConnectionMaker realConnectionMaker() {
		return new DConnectionMaker();
	}
	
	@Bean
	public DataSource dataSource(){
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/springbook");
		dataSource.setUsername("spring");
		dataSource.setPassword("book");
		
		return dataSource;
	}
}
