package library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import library.domain.Author;

public interface IAuthorRepository extends JpaRepository<Author, String>{

}
