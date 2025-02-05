package yeras1k.project.library.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yeras1k.project.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitleContainingIgnoreCase(String keyword);
    List<Book> findByGenreIgnoreCase(String genre);

    @Query("SELECT b FROM Book b WHERE b.isbn = :isbn")
    Optional<Book> findByIsbn(@Param("isbn") String isbn);

    @Modifying
    @Transactional
    @Query("DELETE FROM Book b WHERE b.isbn = :isbn")
    void deleteByIsbn(@Param("isbn") String isbn);
}