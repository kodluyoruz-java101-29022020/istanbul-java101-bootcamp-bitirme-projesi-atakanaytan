package com.thesis.project.exceptions;

public class BookSearchNotFoundExceptionResponse {

    private String searchedBookName;

    public BookSearchNotFoundExceptionResponse(String searchedBookName) {
        this.searchedBookName = searchedBookName;
    }

    public String getSearchedBookName() {
        return searchedBookName;
    }

    public void setSearchedBookName(String searchedBookName) {
        this.searchedBookName = searchedBookName;
    }
}
