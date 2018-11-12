package hh.swd20.moviedb;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.moviedb.domain.User;
import hh.swd20.moviedb.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userrepository;
	
	/** Test for search by username **/
	@Test
	public void findByUsernameShouldReturnUser() {
		User users = userrepository.findByUsername("user");
		assertThat(users.getUsername()).isEqualTo("user");
	}
	
	/** Test that creates a new user and deletes it **/
	@Test
	public void deleteUseR() {
		User user = new User("kayttaja", "$2a$10$7DKtUpFV0SS9xTFL6.SVouob3EpYzAfu1ZJYndQKRNQCHGo8ntExa", "USER");
		
		Long id = user.getId();
		userrepository.deleteById(id);
		
		Optional<User> deletedUser = userrepository.findById(id);
		assertThat(deletedUser).isEmpty();
	}
}
