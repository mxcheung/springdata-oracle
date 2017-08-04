package com.maxcheung.funddata.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maxcheung.funddata.domain.UserData;
import com.maxcheung.funddata.repository.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	private static final AtomicLong counter = new AtomicLong();

	private static List<UserData> users;


	public List<UserData> findAllUsers() {
		return (List<UserData>) userRepository.findAll();
	}

	public UserData findById(long id) {

		return userRepository.findOne(id);
	}

	public UserData findByName(String name) {
		users = (List<UserData>) userRepository.findAll();
		for (UserData user : users) {
			if (user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}

	public void saveUser(UserData user) {
		// user.setId(counter.incrementAndGet());
		users.add(user);
		userRepository.save(user);
	}

	public void updateUser(UserData user) {
//		int index = users.indexOf(user);
//		users.set(index, user);
		userRepository.save(user);
	}

	public void deleteUserById(long id) {

//		for (Iterator<UserData> iterator = users.iterator(); iterator.hasNext();) {
//			UserData user = iterator.next();
//			if (user.getId() == id) {
//				iterator.remove();
//			}
//		}
		UserData user = userRepository.findOne(id);
		userRepository.delete(user);
	}

	public boolean isUserExist(UserData user) {
		return findByName(user.getName()) != null;
	}

	private  List<UserData> populateDummyUsers() {
		List<UserData> users = new ArrayList<UserData>();
		users.add(new UserData(counter.incrementAndGet(), "Sam", 30, 70000));
		users.add(new UserData(counter.incrementAndGet(), "Tom", 40, 50000));
		users.add(new UserData(counter.incrementAndGet(), "Jerome", 45, 30000));
		users.add(new UserData(counter.incrementAndGet(), "Silvia", 50, 40000));
		
		for ( UserData user : users){
			userRepository.save(user);
		}
		
		return users;
	}

	public void deleteAllUsers() {
		users.clear();
	}

}