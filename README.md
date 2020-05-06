
# Spring Boot RESTful API - JPA Hibernate MySQL Example Thesis Project #

RESTful API using Spring Boot, JPA hibernate and Mysql, ManyToMany bidirectional mapping

&nbsp;

## Relation ## 

### Bidirectional Mapping ### 

* Book - Author (Many-To-Many)
* Book - Category (Many-To-Many)

&nbsp;

## RESTful API Server ##
API Description for the Project**

&nbsp;

## Book Controller ##
METHOD | PATH | DESCRIPTION 
------------|-----|------------
GET | /library/book/{book_id} | Gets a Book with the given id
GET | /library/book/list | Gets the total list of books
GET | /book/search/title/{book_title} | Gets a book or book with a searched title.
POST | /library/book | Saves a book
PATCH | /library/book/{book_id} | Updates the book with the given id
DELETE | /library/book/{book_id} | deletes a book with the given id
 
