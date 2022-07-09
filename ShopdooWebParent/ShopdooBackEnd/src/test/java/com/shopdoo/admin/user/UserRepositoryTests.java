package com.shopdoo.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopdoo.common.entity.Role;
import com.shopdoo.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateNewUserWithOneUser() {
			Role roleAdmin = entityManager.find(Role.class, 1);
			User userTheeraphon = new User("tpgitz@gmail.com", "P@ssw0rd", "Theeraphon", "Peanpraditkul");
			userTheeraphon.addRole(roleAdmin);
			
			User savedUser = repo.save(userTheeraphon);
			
			assertThat(savedUser.getId()).isGreaterThan(0);
	
	}
	@Test
	public void testCreateNewUserWithTwoUser() {
		User userGavin = new User("gavinp@gmail.com", "P@ssw0rd", "Gavin", "Peanpraditkul");
		Role roleEditor = new Role(3);
		Role roleAssitant = new Role(5);
		
		userGavin.addRole(roleEditor);
		userGavin.addRole(roleAssitant);
		
		User savedUser = repo.save(userGavin);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	@Test
	public void testGetUserById( ) {
		User userTheeraphon = repo.findById(1).get();
		System.out.println(userTheeraphon);
		assertThat(userTheeraphon).isNotNull();
	}
	@Test
	public void testUpdateUserDetails( ) {
		User userTheeraphon = repo.findById(1).get();
		userTheeraphon.setEnabled(true);
		userTheeraphon.setEmail("tpgitz_test@gmail.com");
		
		repo.save(userTheeraphon);
	}
	@Test
	public void testUpdateUserRoles() {
		User userGavin = repo.findById(2).get();
		Role roleEditor = new Role(4);
		Role roleSalesperson = new Role(3);
		
		userGavin.getRoles().remove(roleEditor);
		userGavin.addRole(roleSalesperson);
		
		repo.save(userGavin);
	}
	@Test
	public void testDeleteUser( ) {
		Integer userId = 1;
		repo.deleteById(userId);
		
	}
	@Test
	public void testGetUserByEmail( ) {
		String email = "tes3@gmail.com";
		User user = repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
	}
	@Test
	public void testCountById() {
		Integer id = 3;
		Long countById = repo.countById(id);
		
		assertThat(countById).isNotNull().isGreaterThan(0);
	}
	@Test
	public void testDisableUser() {
		Integer id  = 3;
		repo.updateEnabledStatus(id, false);
		
	}
	@Test
	public void testEnableUser() {
		Integer id  = 3;
		repo.updateEnabledStatus(id, false);
		
	}
	
	
}
