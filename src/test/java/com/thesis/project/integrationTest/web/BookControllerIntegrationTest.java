package com.thesis.project.integrationTest.web;

import com.thesis.project.entity.Author;
import com.thesis.project.entity.Book;
import com.thesis.project.entity.Category;
import org.junit.jupiter.api.Test;

import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({ "classpath:application.properties"})
class BookControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int tomcatPortNo;

    @Test
    @Order(1)
    @Rollback(true)
    public void testFindByBookId() {

        String url = prepareEmployeeRestServiceRootUrl() +"book/4";

        ResponseEntity<Book> response = testRestTemplate.getForEntity(url, Book.class);

        Book book = response.getBody();
        Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
        Assert.assertTrue( 4 == book.getId());
    }


    @Test
    @Order(2)
    public void testGetAllBooksList() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = prepareEmployeeRestServiceRootUrl() + "book/list";

        List<Book> bookList = new ArrayList<Book>();
        Book book = generateBook();

        HttpEntity<Object> requestEntity = new HttpEntity<Object>(bookList,headers);

        ResponseEntity<List<Book>> response = testRestTemplate.exchange(url, HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<List<Book>>() {});

        Assert.assertTrue(response.getBody().size() > 0 );
    }


    @Test
    @Order(3)
    public void testAddBook() {

        String url = prepareEmployeeRestServiceRootUrl() +"book";

        Book book = generateBook();

        ResponseEntity<Book> response = testRestTemplate.postForEntity(url, book, Book.class);

        Assert.assertTrue(HttpStatus.CREATED.equals(response.getStatusCode()));
        Assert.assertTrue(response.getBody().getCategory().size() == 2 );

    }


    @Test
    @Order(4)
    public void testUpdateBook() {
        String url = prepareEmployeeRestServiceRootUrl() +"book/6";

        Book book = testRestTemplate.getForObject(url, Book.class);
        book.setName("Harry Potter");
        book.setDescription("Changed Description");

        testRestTemplate.put(url, book);
        Book updatedBook = testRestTemplate.getForObject(url, Book.class);
        Assert.assertNotNull(updatedBook);
    }


    @Test
    @Order(5)
    public void testDeleteBook() {

        String url = prepareEmployeeRestServiceRootUrl() +"book/5";

        Book book = testRestTemplate.getForObject(url, Book.class);
        Assert.assertNotNull(book);
        testRestTemplate.delete(url);

        try {
            book = testRestTemplate.getForObject(url, Book.class);
        } catch (final HttpClientErrorException e) {

            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }


    @Test
    @Order(7)
    public void testSearchBook() {

        String url = prepareEmployeeRestServiceRootUrl() + "book/search/title/Punishment";
        String getBookUrl = prepareEmployeeRestServiceRootUrl() +"book/10";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Book dostoevskyCrimeAndPunishment = testRestTemplate.getForObject(getBookUrl,Book.class);
        Assert.assertNotNull(dostoevskyCrimeAndPunishment);

        List<Book> books = new ArrayList<Book>();

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(books, headers);

        ResponseEntity<List<Book>> response = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<Book>>() {} );

        List<Book> retrievedBooks = response.getBody();

        Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
        Assert.assertTrue(retrievedBooks.get(0).getName().contains(dostoevskyCrimeAndPunishment.getName()));
    }

    private String prepareEmployeeRestServiceRootUrl() {

        return "http://localhost:" + tomcatPortNo + "/library/";
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

}