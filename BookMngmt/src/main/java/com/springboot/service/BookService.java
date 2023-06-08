package com.springboot.service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.model.Book;
import com.springboot.model.User;
import com.springboot.repository.BookRepository;
import com.springboot.repository.UserRepository;

@Service
public class BookService 
{
	
	@Autowired
    private BookRepository bookrepository;
	
	@Autowired
    private UserRepository userrepository;
	
	public Book createBook(Book book) {
        return bookrepository.save(book);
    }

    public Book updateBook(Book book, Long id)
    {
    
    	Book existingbook = bookrepository.findById(id).get();
    	existingbook.setTitle(book.getTitle());
    	existingbook.setAname(book.getAname());
        return bookrepository.save(existingbook);
    }
    
    public void assignUsertoBook(Long userId, Long bookId )
    {
    	Optional<User> userOptional = userrepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        User user = userOptional.get();

        // Retrieve the book from the database
        Optional<Book> bookOptional = bookrepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            throw new IllegalArgumentException("Book not found");
        }
        Book book = bookOptional.get();

        // Assign the user to the book
        book.setUser(user);

        // Save the updated book back to the database
         bookrepository.save(book);
    }
    

    public void deleteBook(Long Id) {
    	Book existingbook = bookrepository.findById(Id).get();
        bookrepository.delete(existingbook);
    }

    public List<Book> getAllBooks() {
        return bookrepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookrepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));
    }
}
