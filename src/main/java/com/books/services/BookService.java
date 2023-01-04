package com.books.services;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.books.models.Book;
import com.books.repositories.BookRepository;

@Service
public class BookService {
	public BookService(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}
	// inject the book repository here
	private final BookRepository bookRepository;
	
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
}
