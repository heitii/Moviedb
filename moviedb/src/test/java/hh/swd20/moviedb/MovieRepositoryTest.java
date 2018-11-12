package hh.swd20.moviedb;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.moviedb.domain.Category;
import hh.swd20.moviedb.domain.Movie;
import hh.swd20.moviedb.domain.MovieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository movierepository;
	
	/** Test for search by movie title **/
	@Test
	public void findByTitleShouldReturnMovie() {
		List<Movie> movies = movierepository.findByTitle("Lord of the Rings: The Fellowship of the Ring");
		assertThat(movies).hasSize(1);
		assertThat(movies.get(0).getDirector()).isEqualTo("Peter Jackson");
	}
	
	/** Creates a new movie and deletes it **/
	@Test
	public void deleteMovie() {
		Movie movie = new Movie("The Hobbit: An Unexpected Journey", "Peter Jackson", 2012, 8, 169, new Category("Adventure"));
	
		Long id = movie.getId();
		movierepository.deleteById(id);
		
		Optional<Movie> deletedMovie = movierepository.findById(id);
		assertThat(deletedMovie).isEmpty();
	}
}
