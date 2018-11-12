package hh.swd20.moviedb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.moviedb.webcontrol.MovieController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviedbApplicationTests {

	@Autowired
	private MovieController moviecontroller;
	
	/** Smoketest for moviecontroller **/
	@Test
	public void contextLoads() throws Exception {
		assertThat(moviecontroller).isNotNull();
	}
}
