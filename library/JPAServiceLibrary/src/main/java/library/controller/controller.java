package library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Service.IBookService;
import library.domain.Book;
import library.dto.BookDto;

@RestController
@RequestMapping("/library")

public class controller {
	@Autowired
	IBookService bookService;
	
	@PostMapping
	public boolean addBook(@RequestBody BookDto book) {
		return bookService.addBook(book);		
	}
	
	@GetMapping("/{title}/{name}")
	public Book getBook(@PathVariable String title, @PathVariable String name) {
		return bookService.getBook(title, name);
	}
	
	@DeleteMapping("/{title}/{name}")
	public Book removeBook(@PathVariable String title, @PathVariable String name) {
		return bookService.removeBook(title, name);
	}
	
	@GetMapping("/cheap/{price}/{page}/{size}")
	public Iterable<Book> getCheapBooks(@PathVariable Double minPrice, int page, int size) {
		return bookService.getOnlyCheapBooks(minPrice, page, size);
	}
	
	@GetMapping("/author/{name}")
	public Iterable<Book> getBooksByAuthor(@PathVariable String authorName){
		return bookService.getBooksByAuthor(authorName);
	}	
	
}
