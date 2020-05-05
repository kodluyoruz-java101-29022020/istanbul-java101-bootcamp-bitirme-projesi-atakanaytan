package com.thesis.project.mockTest.service;

import com.thesis.project.dao.BookRepository;
import com.thesis.project.entity.Author;
import com.thesis.project.entity.Book;
import com.thesis.project.entity.Category;
import com.thesis.project.service.impl.BookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class BookServiceImplMockTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Order(1)
    public void testAddBook() {

        Book book = generateBook();

        when(bookRepository.save(book))
                .thenReturn(book);

        Long bookId = bookServiceImpl.addBook(book).getId();

        Assert.assertEquals(bookId, book.getId());
    }

    @Test
    @Order(2)
    public void testFindByBookId() {

        Book book = generateBook();

        when(bookRepository.findWithBookId(7L))
                .thenReturn(book);

        Book retrievedBook = bookServiceImpl.findByBookId(7L);

        Assert.assertNotNull(retrievedBook);
        Assert.assertTrue(retrievedBook.getId() == 7L);
    }

    @Test
    @Order(3)
    public void testGetAllBookList() {

        Book book = generateBook();
        Book book2 = generateSecondBook();

        List<Book> books = Arrays.asList(book, book2);

        when(bookRepository.findAllBooks())
                .thenReturn(books);

        List<Book> books2 = bookServiceImpl.getAllBookList();

        Assert.assertNotNull(book2);
        Assert.assertTrue(books2.size() > 0);
    }

    @Test
    @Order(4)
    public void testUpdateBook() {

        Book book = generateBook();

        when(bookRepository.findWithBookId(book.getId()))
                .thenReturn(book);

        book.setDescription("Update the description");

        when(bookRepository.save(book)).thenReturn(book);

        Book retrievedBook = bookServiceImpl.updateBook(book, book.getId());

        Assert.assertEquals(book.getDescription(), "Update the description");
        Assert.assertEquals(book.getId(), retrievedBook.getId());
    }

    @Test
    @Order(5)
    public void testDeleteBook() {

        Book book = generateBook();

        when(bookRepository.findWithBookId(book.getId()))
                .thenReturn(book);
        bookServiceImpl.deleteBook(book.getId());

        verify(bookRepository,times(1)).delete(book);
    }

    @Test
    @Order(6)
    public void testSearchBookByName() {

        Book book = generateBook();

        List<Book> books = Arrays.asList(book);
        when(bookRepository.findByName(book.getName()))
                .thenReturn(books);

        List<Book> retrievedBooks = bookServiceImpl.searchBookByName(book.getName());

        Assert.assertNotNull(retrievedBooks);
        Assert.assertEquals(retrievedBooks.get(0).getName(), book.getName());
    }

    private Book generateBook(){
        Book book = new Book();

        book.setId(7L);
        Author author = new Author();
        author.setName("Leo Tolstoy");

        Set<Author> authors = new HashSet<Author>();
        authors.add(author);

        Category category1 = new Category();
        category1.setName("Classics");

        Category category2 = new Category();
        category2.setName("Russian Literature");

        Set<Category> categories = new HashSet<Category>();
        categories.add(category1);
        categories.add(category2);

        book.setName("War and Peace");
        book.setAuthors(authors);
        book.setCategory(categories);
        book.setPublicationYear(1869);
        book.setDescription("War and Peace (pre-reform Russian: Война и миръ; post-reform Russian: Война и мир, romanized: Vojna i mir [vɐjˈna i ˈmʲir]) is a novel by the Russian author Leo Tolstoy, published serially, then in its entirety in 1869. " +
                "It is regarded as one of Tolstoy's finest literary achievements and remains a classic of world literature\n" +
                "The novel chronicles the French invasion of Russia and the impact of the Napoleonic era on Tsarist society " +
                "through the stories of five Russian aristocratic families.");
        book.setNote("Have not read it yet. Next month i shold start it to read it.");

        return book;
    }
    private Book generateSecondBook(){
        Book book2 = new Book();

        book2.setId(11L);
        Author secondBookAuthor = new Author();
        secondBookAuthor.setName("Franz Kafka");

        Set<Author> secondBookAuthors = new HashSet<Author>();
        secondBookAuthors.add(secondBookAuthor);

        Category secondBookCategory1 = new Category();
        secondBookCategory1.setName("Classics");

        Category secondBookCategory2 = new Category();
        secondBookCategory2.setName("Russian Literature");

        Set<Category> secondBookCategories = new HashSet<Category>();
        secondBookCategories.add(secondBookCategory1);
        secondBookCategories.add(secondBookCategory2);

        book2.setName("The Metamorphosis");
        book2.setAuthors(secondBookAuthors);
        book2.setCategory(secondBookCategories);
        book2.setPublicationYear(1869);
        book2.setDescription("The Metamorphosis (German: Die Verwandlung) is a novella written by Franz Kafka which was first published in 1915. " +
                "One of Kafka's best-known works, The Metamorphosis tells the story of salesman Gregor Samsa " +
                "who wakes one morning to find himself inexplicably " +
                "transformed into a huge insect (German ungeheures Ungeziefer, literally \"monstrous vermin\"), " +
                "subsequently struggling to adjust to this new condition." +
                " The novella has been widely discussed among literary critics, with differing interpretations being offered.");
        book2.setNote("One of the well-known piece of Franz Kafka's");

        return book2;
    }
}