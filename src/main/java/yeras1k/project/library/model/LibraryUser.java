package yeras1k.project.library.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class LibraryUser {
    @Id
    private String userId;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "borrowed_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id",  referencedColumnName = "isbn")
    )
    private List<Book> borrowedItems = new ArrayList<>();
    // Constructors, Getters, and Setters
    public LibraryUser() {}

    public LibraryUser(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Book> getBorrowedItems() { return borrowedItems; }
    public void setBorrowedItems(List<Book> borrowedItems) { this.borrowedItems = borrowedItems; }
}