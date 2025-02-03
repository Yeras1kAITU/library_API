package yeras1k.project.library.repository;

import yeras1k.project.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitleContainingIgnoreCase(String keyword); // Search by title
    List<Book> findByGenreIgnoreCase(String genre); // Filter by genre
}