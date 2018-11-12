package hh.swd20.moviedb.webcontrol;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.moviedb.domain.CategoryRepository;
import hh.swd20.moviedb.domain.Movie;
import hh.swd20.moviedb.domain.MovieRepository;

@Controller
public class MovieController {
	@Autowired
	private MovieRepository movierepository;
	
	@Autowired
	private CategoryRepository categoryrepository;
	
	/** Log in **/
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	/** Lists all the movies from the db **/
	@RequestMapping(value="/movielist", method=RequestMethod.GET)
	public String movieList(Model model) {
		model.addAttribute("movies", movierepository.findAll());
		return "movielist";
	}
	
	/** RESTful -service that lists all the movies **/
	@RequestMapping(value="/movies", method=RequestMethod.GET)
	public @ResponseBody List<Movie> movieListRest() {
		return (List<Movie>) movierepository.findAll();
	}
	
	/** RESTful service that returns a movie by id **/
	@RequestMapping(value="/movies/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Movie> findMovieRest(@PathVariable("id") Long movieId) {
		return movierepository.findById(movieId);
	}
	
	/** Returns a empty form to add a new movie **/
	@RequestMapping(value="/addmovie")
	public String addMovie(Model model) {
		model.addAttribute("movie", new Movie());
		model.addAttribute("categorylist", categoryrepository.findAll());
		return "addmovie";
	}
	
	/** Saves the info that was typed into a form **/
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@Valid Movie movie, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "addmovie";
		}
		model.addAttribute("movie", movie);
		return "redirect:movielist";
	}
	
	/** Deletes a movie by id **/
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteMovie(@PathVariable("id") Long movieId, Model model) {
		movierepository.deleteById(movieId);
		return "redirect:../movielist";
	}
	
	/** Edits a movie by id **/
	 @RequestMapping(value="/edit/{id}")
	 public String editMovie(@PathVariable("id") Long movieId, Model model ) {
		 model.addAttribute("movie", movierepository.findById(movieId));
		 model.addAttribute("categorylist", categoryrepository.findAll());
		 return "editmovie";
	 }
}