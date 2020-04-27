package com.thesis.project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 2963836225863110941L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", length = 11, nullable = false)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author",
            joinColumns = {@JoinColumn(name = "book_id") },
            inverseJoinColumns = {@JoinColumn(name = "author_id") }
    )
    private Set<Author> authors;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_categories",
            joinColumns = {@JoinColumn(name = "book_id") },
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private Set<Category> category;


    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @Column(name = "description", length = 2000, nullable = false)
    private String description;

    @Column(name = "note", length = 1000, nullable = false)
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

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
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
