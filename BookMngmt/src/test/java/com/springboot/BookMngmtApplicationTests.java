package com.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.model.Book;
import com.springboot.repository.BookRepository;

@SpringBootTest
class BookMngmtApplicationTests
{

	@Autowired
    private EntityManager entityManager;

    @Autowired
    private BookRepository bookrepository;
    
	@Test
	void contextLoads()
	{
		Book book = new Book();
		book.setTitle("Spiderman");
		book.setAname("Peter");
		
		Book savedbook = bookrepository.save(book);
		
		assertNotNull(savedbook.getId());
		
		Book retrievedBook = entityManager.find(Book.class, savedbook.getId());

        // Verify that the retrieved book matches the saved book
        assertEquals(savedbook.getId(), retrievedBook.getId());
        assertEquals(savedbook.getTitle(), retrievedBook.getTitle());
        assertEquals(savedbook.getAname(), retrievedBook.getAname());
	}

}
