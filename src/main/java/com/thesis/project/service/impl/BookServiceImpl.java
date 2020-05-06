package com.thesis.project.service.impl;

import com.thesis.project.entity.Book;
import com.thesis.project.dao.BookRepository;
import com.thesis.project.exceptions.BookNotFoundException;
import com.thesis.project.exceptions.BookSearchNotFoundException;
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

    //Adds new Book to library
    @Override
    @MethodRunningTime(active = true)
    public Book addBook(Book book) {

        Book saveBook =  bookRepository.save(book);

        if (book == null){
            throw new BookNotFoundException("Book with id: "+book.getId()+ " could not find");
        }

        return saveBook;
    }

    //Fetches book with given Id as a parameter
    @Override
    @Transactional
    @MethodRunningTime(active = true)
    public Book findByBookId(Long bookId) {

        Book book = bookRepository.findWithBookId(bookId);

        if (book == null) {
            throw new BookNotFoundException("Book with id: "+bookId+ " could not find");
        }

        return book;
    }

    //Fetches all the books saved from the library.
    @Override
    @Transactional
    @MethodRunningTime(active = true)
    public List<Book> getAllBookList() {

        List<Book> books = bookRepository.findAllBooks();

        if (books.size() <1){
            throw new BookNotFoundException("There are no any saved book");
        }

        return books;
    }

    //Updates the book
    @Override
    @MethodRunningTime(active = true)
    public Book updateBook(Book book, Long bookId) {


        Book updateBook = bookRepository.findWithBookId(bookId);

        if (updateBook == null) {
            throw new BookNotFoundException("Book with id: "+bookId+ " could not find");
        }

        updateBook = book;

        return bookRepository.save(updateBook);
    }

    //Deletes book
    @Override
    @MethodRunningTime(active = true)
    public void deleteBook(Long bookId) {

        Book deletedBook= findByBookId(bookId);

        if (deletedBook == null){
            throw new BookNotFoundException("Book with id: "+bookId+ " could not find");
        }

        deletedBook = bookRepository.findWithBookId(bookId);
        bookRepository.delete(deletedBook);
    }

    //Searches books with the given title
    @Override
    @Transactional
    @MethodRunningTime(active = true)
    public List<Book> searchBookByName(String title) {

        List<Book> filteredBooks = bookRepository.findByName(title);

        if (filteredBooks.size() < 1) {
            throw new BookSearchNotFoundException("There is no any book or books with the title: "+title);
        }

        return filteredBooks;
    }

}
