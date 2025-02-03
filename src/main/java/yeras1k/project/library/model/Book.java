package yeras1k.project.library.model;

import jakarta.persistence.*;
import java.util.List;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String genre;

    @ManyToMany(mappedBy = "borrowedItems")
    @JsonIgnore // Prevents this field from being serialized
    private List<LibraryUser> users = new ArrayList<>();

    // Constructors, Getters, and Setters
    public Book() {}

    public Book(String isbn, String title, String author, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public List<LibraryUser> getUsers() { return users; }
    public void setUsers(List<LibraryUser> users) { this.users = users; }
}