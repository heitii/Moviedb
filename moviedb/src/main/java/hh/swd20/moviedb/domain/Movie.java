package hh.swd20.moviedb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="Title can't be empty")
	private String title;
	
	@NotBlank(message="Director can't be empty")
	private String director;
	
	private int year;
	private int rating;
	private int length;
	
	 @ManyToOne
	 @JsonIgnore
	 @JoinColumn(name = "categoryId")
	 private Category category;
	 
	 public Movie() {}

	public Movie(String title, String director, int year, int rating, int length, Category category) {
		super();
		this.title = title;
		this.director = director;
		this.year = year;
		this.rating = rating;
		this.length = length;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		if (this.category != null)
			return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", year=" + year + ", rating="
				+ rating + ", length=" + length + "category=" + this.getCategory() + "]";
		else
			return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", year=" + year + ", rating="
				+ rating + ", length=" + length + "]";
	}
}
