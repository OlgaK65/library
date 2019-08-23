package Service;

import java.awt.print.Pageable;

import library.domain.Book;
import library.dto.BookDto;

public interface IBookService {
	
	 boolean addBook(BookDto book);	 
	 Book getBook(String title, String name);	 
     Book removeBook(String title, String name);
     
     Iterable<Book> getOnlyCheapBooks(Double minPrice, int page, int size);
     Iterable<Book> getBooksByAuthor(String author);
    
}
