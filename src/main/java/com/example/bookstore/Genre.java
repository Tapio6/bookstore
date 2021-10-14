package com.example.bookstore;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long genreid;
	private String type;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
	private List<Book> books;

	public Genre() {}

	public Genre(String type) {
		super();
		this.type = type;
	}

	public Long getGenreid() {
		return genreid;
	}

	public void setGenreid(Long genreid) {
		this.genreid = genreid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Genre [genreid=" + genreid + ", type=" + type + "]";
	}

}
