package com.thesis.project.web;

import com.thesis.project.entity.Book;
import com.thesis.project.exceptions.BookNotFoundException;
import com.thesis.project.exceptions.BookSearchNotFoundException;
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


    /**
     * Fetches the book with related id
     *
     * @param book_id - Id of the expected book
     *
     * @return Expected book
     *
     * @throws BookNotFoundException exception
     *          if book_id does not exist.
     *
     */
    @GetMapping("/book/{book_id}")
    public ResponseEntity<?> findByBookId(@PathVariable Long book_id) {

        Book returnBook = bookService.findByBookId(book_id);

            return new ResponseEntity<Book>(returnBook, HttpStatus.OK);
    }


    /**
     * Fetch list of books
     *
     * @return Fetched books in the library
     *
     * @throws BookNotFoundException exception
     *          if there is no any saved book in the library
     *
     */
    @GetMapping("/book/list")
    public ResponseEntity<?> getAllBooksList(){

        List<Book> returnBooks = bookService.getAllBookList();

          return new ResponseEntity<List<Book>>(returnBooks, HttpStatus.OK);
    }


    /**
     * Creates new book
     *
     * @return New book
     *
     * @throws BookNotFoundException exception
     *          if given book is null
     *
     *          errorMap-> if mandatory fields are null it returns jpa validation errors of Book.Class
     *
     */
    @PostMapping("/book")
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book,
                                     BindingResult result) {

        ResponseEntity<?> errorMap = validationErrorServices.ValidationErrorServices(result);
        if (errorMap != null) { return errorMap; }

        Book newBook = bookService.addBook(book);

            return new ResponseEntity<Book>(newBook, HttpStatus.CREATED);
    }


    /**
     * Update an existing book
     *
     * @param book_id - The id of the book will updated
     *
     * @return Updated book
     *
     * @throws BookNotFoundException
     *           if given book_id is null
     *      *
     *      *          errorMap-> if mandatory fields are null it returns jpa validation errors of Book.Class
     */
    @PatchMapping("/book/{book_id}")
    public ResponseEntity<?> updateBook(@Valid @RequestBody Book book,
                                        @PathVariable Long book_id, BindingResult result) {

        ResponseEntity<?> errorMap = validationErrorServices.ValidationErrorServices(result);
        if (errorMap != null) { return errorMap; }

        Book updateBook = bookService.updateBook(book, book_id);

            return new ResponseEntity<Book>(updateBook, HttpStatus.OK);
    }


    /**
     * Deletes a book
     *
     * @param book_id- The id of the book will deleted
     *
     * @return If delete operation is successful it returns message with deleted book_id.
     *
     * @throws BookNotFoundException
     *          if book_id does not exist in the database
     *
     */
    @DeleteMapping("/book/{book_id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long book_id) {

        bookService.deleteBook(book_id);

            return new ResponseEntity<String>("Book with id: '"+book_id+"' was deleted succesfully", HttpStatus.OK);
    }


    /**
     * Deletes a book
     *
     * @param book_title- The title of the book or books will fetch
     *
     * @return List of books related to the searched book_title
     *
     * @throws BookSearchNotFoundException
     *          if there is no anly book with searched book_title
     *
     */
    @GetMapping("/book/search/title/{book_title}")
     public ResponseEntity<?> searchBook(@PathVariable String book_title){

        List<Book> books = bookService.searchBookByName(book_title);

            return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

}
