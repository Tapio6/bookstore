package com.example.bookstore;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class BookstoreController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private GenreRepository grepository;
	
	//LIST
	@RequestMapping(value= {"/", "/booklist"})
	public String bookList(Model model ) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//ADD
	@RequestMapping(value = "/add")
	
	public String addBook(Model model) {
		model.addAttribute("book" , new Book());
		model.addAttribute("genres", grepository.findAll());
		return "addbook";
	}
	
	//MODIFY
	@RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	
	public String modiftBook(@PathVariable("id") Long bookId, Model model) {
		java.util.Optional<Book> book = repository.findById(bookId);
		model.addAttribute("book", book);
		model.addAttribute("genres", grepository.findAll());
		return "modifybook";
		
	}
	
	//SAVE
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	//DELETE
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	//REST BOOKS
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}
	
	//REST BOOK ID
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
	}
	
	//LOGIN
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
}
