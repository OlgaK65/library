package library.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"name"})

@Entity

public class Author implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	String name;
	
	@OneToMany(mappedBy="author", cascade = {CascadeType.REMOVE})
	Set<Book> books;
	
	public Author(String name){
		this.name = name;
		books = new HashSet<Book>();
	}
	
	public boolean addBook(Book book) {
		return books.add(book);
	}	
	
}
