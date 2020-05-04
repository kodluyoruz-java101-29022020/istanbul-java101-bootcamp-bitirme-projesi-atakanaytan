package com.thesis.project.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 2963836225863110941L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", length = 11, nullable = false)
    private Long id;

    @NotEmpty(message = "Please Enter The Book Name")
    @Size(max = 100, message = "Name of the book can not be more than 100 character long")
    @Column(name = "name")
    private String name;


    @NotNull(message = "Please indicate the author or authors of the book")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author",
            joinColumns = {@JoinColumn(name = "book_id") },
            inverseJoinColumns = {@JoinColumn(name = "author_id") }
    )
    private Set<Author> authors;


    @NotNull(message = "Please indicate the author or authors of the book")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_categories",
            joinColumns = {@JoinColumn(name = "book_id") },
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> category;

    @NotNull(message = "Please enter the publish year of the book")
    @Column(name = "publication_year")
    private int publicationYear;

    @NotEmpty(message = "Please enter the description of the book")
    @Size(max = 2000, message = "Description can not be more than 2000 character long")
    @Column(name = "description")
    private String description;

    @NotEmpty(message = "Please enter the notes that want to attach it")
    @Size(max = 500, message = "Notes can not be more than 500 character long")
    @Column(name = "note")
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
