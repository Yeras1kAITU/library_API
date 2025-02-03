package yeras1k.project.library.controller;

import yeras1k.project.library.model.Book;
import yeras1k.project.library.model.LibraryUser;
import yeras1k.project.library.repository.BookRepository;
import yeras1k.project.library.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserController(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    // Get all users
    @GetMapping
    public List<LibraryUser> getAllUsers() {
        return userRepository.findAll();
    }

    // Add a user
    @PostMapping
    public LibraryUser addUser(@RequestBody LibraryUser user) {
        return userRepository.save(user);
    }

    // Delete a user by ID
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userRepository.deleteById(userId);
    }

    // Assign a book to a user
    @PostMapping("/{userId}/borrow")
    public void assignBorrowedBook(@PathVariable String userId, @RequestParam String isbn) {
        LibraryUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new RuntimeException("Book not found"));
        user.getBorrowedItems().add(book);
        userRepository.save(user);
    }

    // Get borrowed books by a user
    @GetMapping("/{userId}/borrowed-books")
    public List<Book> getUserBorrowedBooks(@PathVariable String userId) {
        LibraryUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getBorrowedItems();
    }
}