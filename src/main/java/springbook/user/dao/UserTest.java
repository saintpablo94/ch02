package springbook.user.dao;

import java.sql.SQLException;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/countbeans.xml")
public class UserTest {
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CountConnectionMaker countConnectionMaker;
	
	private User userOne;
	private User userTwo;
	private User userThree;
	
	@Before
	public void setUp(){
//		ApplicationContext context = new AnnotationConfigApplicationContext(CountDaoFactory.class);
		System.out.println(this.context);
		System.out.println(this);
		
		this.userOne = new User().setId("test0").setName("테스트1").setPasswordString("married1");
		this.userTwo = new User().setId("test1").setName("테스트2").setPasswordString("married2");
		this.userThree = new User().setId("test2").setName("테스트3").setPasswordString("married3");
	}
	
	
	@Test
	public void addAndGet() throws SQLException {
		
		userDao.deleteAll();
		assertThat(userDao.getCount(), is(0));

		userDao.add(userOne);
		assertThat(userDao.getCount(), is(1));
		
		User user2 = userDao.get(userOne.getId());
		
		assertThat(user2.getName(), is(userOne.getName()));
		assertThat(user2.getPassword(), is(userOne.getPassword()));
		
		/*assertThat(countConnectionMaker.getCounter(), is(4));*/		
	}
	
	
	@Test
	public void count() throws SQLException {
		
		userDao.deleteAll();
		assertThat(userDao.getCount(), is(0));
		
		userDao.add(userOne);
		userDao.add(userTwo);
		assertThat(userDao.getCount(), is(2));
		
		User userOneGet = userDao.get(userOne.getId());
		assertThat(userOneGet.getName(), is(userOne.getName()));
		
		User userTwoGet = userDao.get(userTwo.getId());
		assertThat(userTwoGet.getName(), is(userTwo.getName()));
		
		/*assertThat(countConnectionMaker.getCounter(), is(4));*/		
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {

		userDao.deleteAll();
		assertThat(userDao.getCount(), is(0));
		
		userDao.get("unknowid");
	}

}
