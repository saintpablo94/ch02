package springbook.user.dao;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/beans.xml")
public class JUnitTest {
	
	@Autowired
	ApplicationContext context;
	
	static Set<JUnitTest> testObject = new HashSet<JUnitTest>();
	static ApplicationContext contextObject = null;
	
	@Test
	public void testOne(){
		//assertThat(this,is(not(sameInstance(testObject))));
		assertThat(testObject, not(hasItem(this)));
		testObject.add(this);
		
		assertThat(contextObject == null || contextObject == this.context, is(true));
		contextObject = this.context;
	}
	
	@Test
	public void testTwo(){
		assertThat(testObject, not(hasItem(this)));
		testObject.add(this);
		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
	}
	
	@Test
	public void testThree(){
		assertThat(testObject, not(hasItem(this)));
		testObject.add(this);
		
		/*assertThat(contextObject, either(is(nullValue())).or(is(this.context)));		                          
		contextObject = this.context;*/
	}
}
