package hh.swd20.moviedb;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.moviedb.domain.Category;
import hh.swd20.moviedb.domain.CategoryRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryrepository;
	
	@Test
	public void findByNameShouldReturnKysely() {
		List<Category> categories = categoryrepository.findByName("Romance");
		assertThat(categories).hasSize(1);
	}
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Thriller");
		categoryrepository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}
}
