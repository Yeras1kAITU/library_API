package yeras1k.project.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import yeras1k.project.library.model.Book;
import yeras1k.project.library.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:63342")

public class BookController {
    @Autowired
    private BookRepository bookRepository;

    // Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Add a book
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // Delete a book by ISBN
    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        bookRepository.deleteById(isbn);
    }

    // Search books by title
    @GetMapping("/search")
    public List<Book> searchBooksByTitle(@RequestParam String keyword) {
        return bookRepository.findByTitleContainingIgnoreCase(keyword);
    }

    // Filter books by genre
    @GetMapping("/filter")
    public List<Book> filterBooksByGenre(@RequestParam String genre) {
        return bookRepository.findByGenreIgnoreCase(genre);
    }
}