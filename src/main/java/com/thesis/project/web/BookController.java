package com.thesis.project.web;

import com.thesis.project.entity.Book;
import com.thesis.project.service.BookService;
import com.thesis.project.service.validation.ValidationErrorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ValidationErrorServices validationErrorServices;

    @GetMapping("/book/{book_id}")
    public ResponseEntity<?> findByBookId(@PathVariable Long book_id) {

        Book returnBook = bookService.findByBookId(book_id);
        return new ResponseEntity<Book>(returnBook, HttpStatus.OK);
    }

    @GetMapping("/book/list")
    public ResponseEntity<?> getAllBooksList(){

        List<Book> returnBooks = bookService.getAllBookList();
        return new ResponseEntity<List<Book>>(returnBooks, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book, BindingResult result) {

        ResponseEntity<?> errorMap = validationErrorServices.ValidationErrorServices(result);

        if (errorMap != null) {
            return errorMap;
        }

        Book newBook = bookService.addBook(book);
        return new ResponseEntity<Book>(newBook, HttpStatus.CREATED);
    }

    @PatchMapping("/book/{book_id}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable Long book_id) {

        Book updateBook = bookService.updateBook(book, book_id);

        return new ResponseEntity<Book>(updateBook, HttpStatus.OK);
    }


    @DeleteMapping("/book/{book_id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long book_id) {

        bookService.deleteBook(book_id);

        return new ResponseEntity<String>("Book with id: '"+book_id+"' was deleted succesfully", HttpStatus.OK);
    }

    @GetMapping("/book/search/title/{book_title}")
     public ResponseEntity<?> searchBook(@PathVariable String book_title){

        List<Book> books = bookService.searchBookByName(book_title);

        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

}
