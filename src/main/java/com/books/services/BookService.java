package com.books.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.models.Book;
import com.books.repositories.BookRepository;

@Service
public class BookService {
	// Injects the book repository dependency here
	@Autowired
	private BookRepository bookRepository;
	
	// returns all the books
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }

    // update a book
    public Book updateBook(Book b) {
    	return bookRepository.save(b);
    }
    
    // delete a book
    public void deleteBook(Long id) {
    	bookRepository.deleteById(id);
    }
}
