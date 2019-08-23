package library.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import library.domain.Book;


public interface IBookRepository  extends JpaRepository<Book, String>{

	@Query(value="select book.title as title from book where title is :bookTitle",nativeQuery=true)
    Iterable<Book> findByTitle(@Param("title")String bookTitle);
	
	@Query(value="select book.title as title from book where book.price < :min",nativeQuery=true)
	Page<Book> findCheapBooks(@Param("min")Double minPrice, Pageable p);
	
	@Query(value="select book.title as title, book.author as author from book where author.name = :name",nativeQuery=true)
	Iterable<Book>  findBooksByAuthor(@Param("name")String authorName);
}
