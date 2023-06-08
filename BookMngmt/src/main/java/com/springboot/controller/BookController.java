package com.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Book;
import com.springboot.model.User;
import com.springboot.repository.BookRepository;
import com.springboot.repository.UserRepository;
import com.springboot.service.BookService;
@RestController
@RequestMapping("/books")
public class BookController
{
	@Autowired
	private BookService bookservice;
	
	@Autowired
	private BookRepository bookrepository;
	
	@Autowired
	private UserRepository userrepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity <Book> create(@RequestBody Book book ) 
	{
		Optional<User> optionalUser = userrepository.findById(book.getUser().getId());
	    if (optionalUser.isEmpty()) {
	        return ResponseEntity.badRequest().build();
	    }
	    User user = optionalUser.get();

	    // Set the retrieved user as the user for the book
	    book.setUser(user);

	    // Save the book in the database
	    Book savedBook = bookrepository.save(book);
		return ResponseEntity.ok(savedBook);
	}
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Book> read() {
		return bookservice.getAllBooks();
	}

	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public Book read(@PathVariable Long id) {
		return bookservice.getBookById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		bookservice.deleteBook(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{id}")
	public Book update(@PathVariable Long id, @RequestBody Book book) {
		return bookservice.updateBook(book, id);
	}
}
