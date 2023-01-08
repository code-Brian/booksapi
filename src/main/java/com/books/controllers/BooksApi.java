package com.books.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.books.models.Book;
import com.books.services.BookService;

@RestController
@RequestMapping("/api")
public class BooksApi {
    private final BookService bookService;
    public BooksApi(BookService bookService){
        this.bookService = bookService;
    }
    @GetMapping("/books")
    public List<Book> index() {
        return bookService.allBooks();
    }
    
    @PostMapping("/books")
    public Book create(@RequestParam(value="title") String title, @RequestParam(value="description") String desc, 
    		@RequestParam(value="language") String lang, 
    		@RequestParam(value="pages") Integer numOfPages) {
        Book book = new Book(title, desc, lang, numOfPages);
        return bookService.createBook(book);
    }
    
    @GetMapping("/books/{id}")
    public Book show(@PathVariable("id") Long id) {
        Book book = bookService.findBook(id);
        return book;
    }
    
    
    @DeleteMapping("/books/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
    	bookService.deleteBook(id);
    	return "redirect:/";
    }
    
    // This route takes us to the edit book page, and would populate the form with the data
    // We need a Post or Put mapping route to actually change something in the database
	/*
	 * @GetMapping("/{id}/update") public String editForm(@PathVariable("id") Long
	 * id, Model model) { model.addAttribute("drink",bookService.findBook(id));
	 * return "editDrink.jsp"; }
	 */
	
	// Let's make another route that actually updates the book
	@PutMapping("/books/{id}/update")
	public String update(@Valid @PathVariable("id") Long id, @ModelAttribute("book") Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "index.jsp";
		} else {
			bookService.updateBook(book); 
			return "redirect:/";
		}
	}
}
    
