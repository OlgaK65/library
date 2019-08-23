package Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import library.configuration.LibraryConfiguration;
import library.dao.IAuthorRepository;
import library.dao.IBookRepository;
import library.domain.Author;
import library.domain.Book;
import library.dto.BookDto;


public class BookServiceImpl implements IBookService {
	@Autowired
	IAuthorRepository authorRepository;
	
	@Autowired
	IBookRepository bookRepository;
	
	
	@Override
	public boolean addBook(BookDto bookDto) {
		
		String title = bookDto.getTitle();
		String name = bookDto.getAuthor();
		
		if(getBook(title, name) != null)
			return false;			
		
		Author author = new Author(name);
		Book book = new Book(title, bookDto.getPrice(), author);
		if(!authorRepository.existsById(name)) {
			
			author.addBook(book);
			authorRepository.save(author);
	    }
		
		bookRepository.save(book);	
		author.addBook(book);
		
		return false;
	}

	@Override
	public Book getBook(String title, String name) {
		Iterable<Book> ibIterable = bookRepository.findByTitle(title);
		for(Book b:ibIterable )
		{
			if(b.getAuthor().getName().equals(name))
				return b;
		}	
		return null;
	}

	@Override
	public Book removeBook(String title, String name) {
		
		Book book = getBook(title, name);	
		if(book == null)
			return null;	
		
		bookRepository.delete(book);
		return book;
	}

	@Override
	public Iterable<Book> getOnlyCheapBooks(Double minPrice, int page, int size) {		
		Pageable pageable = LibraryConfiguration.getPageable();
		pageable = PageRequest.of(page, size);
		List<Book> lBooks = bookRepository.findCheapBooks(minPrice,pageable).getContent();	
		
		return lBooks;		
	}

	@Override
	public Iterable<Book> getBooksByAuthor(String name) {
		
		return bookRepository.findBooksByAuthor(name);
		
	}

}
