package springbook.user.dao;

import java.sql.SQLException;


import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.domain.User;

public class UserTest {

	@Test
	public void addAndGet() throws SQLException {
//		ApplicationContext context = new AnnotationConfigApplicationContext(CountDaoFactory.class);
		ApplicationContext context = new GenericXmlApplicationContext("countbeans.xml");

		UserDao userDao = context.getBean("userDao",UserDao.class);
		CountConnectionMaker countConnectionMaker = context.getBean(CountConnectionMaker.class);

		User userOne = new User().setId("test0").setName("테스트1").setPasswordString("married1");
		
		userDao.deleteAll();
		assertThat(userDao.getCount(), is(0));

		userDao.add(userOne);
		assertThat(userDao.getCount(), is(1));
		
		User user2 = userDao.get(userOne.getId());
		
		assertThat(user2.getName(), is(userOne.getName()));
		assertThat(user2.getPassword(), is(userOne.getPassword()));
		
		System.out.println(user2.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		/*assertThat(countConnectionMaker.getCounter(), is(4));*/		
	}
	
	
	@Test
	public void count() throws SQLException {
//		ApplicationContext context = new AnnotationConfigApplicationContext(CountDaoFactory.class);
		ApplicationContext context = new GenericXmlApplicationContext("countbeans.xml");

		UserDao userDao = context.getBean("userDao",UserDao.class);
		CountConnectionMaker countConnectionMaker = context.getBean(CountConnectionMaker.class);

		User userOne = new User().setId("test0").setName("테스트1").setPasswordString("married1");
		User userTwo = new User().setId("test1").setName("테스트2").setPasswordString("married2");
		User userThree = new User().setId("test2").setName("테스트3").setPasswordString("married3");
		
		userDao.deleteAll();
		assertThat(userDao.getCount(), is(0));

		userDao.add(userOne);
		assertThat(userDao.getCount(), is(1));
		
		userDao.add(userTwo);
		assertThat(userDao.getCount(), is(2));
		
		userDao.add(userThree);
		assertThat(userDao.getCount(), is(3));
		
		User user2 = userDao.get(userOne.getId());
		
		assertThat(user2.getName(), is(userOne.getName()));
		assertThat(user2.getPassword(), is(userOne.getPassword()));
		
		/*assertThat(countConnectionMaker.getCounter(), is(4));*/		
	}
	

}
