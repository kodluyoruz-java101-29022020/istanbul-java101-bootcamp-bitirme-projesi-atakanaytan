package com.thesis.project.service;

import com.thesis.project.entity.Book;

import java.util.List;


public interface BookService {

    public Book addBook(Book book);

    public Book findByBookId(Long bookId);

    public List<Book> getAllBookList();

    public Book updateBook(Book book, Long book_id);

    public void deleteBook(Long book_id);

    public List<Book> searchBookByName(String title);

}
