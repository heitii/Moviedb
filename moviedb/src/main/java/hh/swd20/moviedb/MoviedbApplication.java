package hh.swd20.moviedb;

import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hh.swd20.moviedb.domain.Category;
import hh.swd20.moviedb.domain.CategoryRepository;
import hh.swd20.moviedb.domain.Movie;
import hh.swd20.moviedb.domain.MovieRepository;
import hh.swd20.moviedb.domain.User;
import hh.swd20.moviedb.domain.UserRepository;

@SpringBootApplication
public class MoviedbApplication {
	private static final Logger log = LoggerFactory.getLogger(MoviedbApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MoviedbApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner movieDemo(MovieRepository movierepository, CategoryRepository categoryrepository, UserRepository userrepository) {
		return (args) -> {
			log.info("save few categories");
			categoryrepository.save(new Category("Fantasy"));
			categoryrepository.save(new Category("Horror"));
			categoryrepository.save(new Category("Comedy"));
			
			log.info("save few movies");
			movierepository.save(new Movie("Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", 2001, 9, 178, categoryrepository.findByName("Fantasy").get(0)));
			movierepository.save(new Movie("Lord of the Rings: The Two Towers", "Peter Jackson", 2002, 9, 179, categoryrepository.findByName("Fantasy").get(0)));
			movierepository.save(new Movie("Lord of the Rings: The Return of the King", "Peter Jackson", 2003, 9, 201, categoryrepository.findByName("Fantasy").get(0)));
			
			// Adding few users
			User user1 = new User("user", "$2a$04$TRUQ.T4ysP09jApN3zFJhuQa7XHOuxzGhB47U41QMn5Fcrj8fTZ56", "USER");
			User user2 = new User("admin", "$2a$04$AMSGA7cez1Zh2Q/irFeAsuNSAtCK8jLXaManf/Z.w.E4J3aSuQHli", "ADMIN");
			userrepository.save(user1);
			userrepository.save(user2);
		};
	}
}
