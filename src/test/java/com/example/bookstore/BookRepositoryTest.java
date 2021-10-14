package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;

import com.example.bookstore.Genre;
import com.example.bookstore.Book;
import com.example.bookstore.BookRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;

	@Test
	public void findByLastnameShouldReturnStudent() {
		List<Book> books = repository.findBylastName("Johnstone");

		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("The legend of Perley Gates");
	}

	@Test
	public void createNewBook() {
		Book Book = new Book("Stephen", "King", "IT", "45674567-90", 1990, new Genre("Horror"));
		repository.save(Book);
		assertThat(Book.getId()).isNotNull();
	}

}