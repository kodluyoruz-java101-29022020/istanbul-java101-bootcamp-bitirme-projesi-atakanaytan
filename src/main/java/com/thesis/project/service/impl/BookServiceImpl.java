package com.thesis.project.service.impl;

import com.thesis.project.entity.Book;
import com.thesis.project.dao.BookRepository;
import com.thesis.project.service.BookService;
import com.thesis.project.annotation.MethodRunningTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    @MethodRunningTime(active = true)
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    @MethodRunningTime(active = true)
    public Book findByBookId(Long bookId) {
        return bookRepository.findWithBookId(bookId);
    }

    @Override
    @Transactional
    @MethodRunningTime(active = true)
    public List<Book> getAllBookList() {
        return bookRepository.findAllBooks();
    }

    @Override
    public Book updateBook(Book book, Long book_id) {

        Book updateBook = bookRepository.findWithBookId(book_id);
        updateBook = book;

        return bookRepository.save(updateBook);
    }


    @Override
    @MethodRunningTime(active = true)
    public void deleteBook(Long book_id) {

        Book deletedBook = bookRepository.findWithBookId(book_id);
        bookRepository.delete(deletedBook);
    }

    @Override
    @Transactional
    @MethodRunningTime(active = true)
    public List<Book> searchBookByName(String title) {

        return bookRepository.findByName(title);
    }


}
