package com.thesis.project.dao;

import com.thesis.project.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

     @Query("SELECT e FROM Book e WHERE e.id = :id")
     public Book findWithBookId(@Param("id") Long id);

     @Query("SELECT e FROM Book e")
     public List<Book> findAllBooks();

     @Query("SELECT e FROM Book e WHERE LOWER(e.name) LIKE lOWER(CONCAT('%', :name, '%'))")
     public List<Book> findByName(@Param("name") String name);

}
