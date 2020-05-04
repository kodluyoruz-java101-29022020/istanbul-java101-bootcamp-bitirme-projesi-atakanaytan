package com.thesis.project.exceptions;

public class BookNotFoundExceptionResponse {

    private String bookName;

    public BookNotFoundExceptionResponse(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
