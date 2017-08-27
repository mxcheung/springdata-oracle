package com.maxcheung.company;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.maxcheung.company.greeting.User;
import com.maxcheung.company.greeting.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
	
	@Autowired
	UserRepository userRepository; 

	@Test
	public void userTests() {
		
		User user = new User();
		user.setUsername("rocky");
		userRepository.save(user);
		
		User user1 = userRepository.findByUsername("rocky");
		assertEquals(user.getUsername(), user1.getUsername());
		
		
	}

}
