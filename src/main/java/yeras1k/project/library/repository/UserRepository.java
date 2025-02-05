package yeras1k.project.library.repository;

import yeras1k.project.library.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<LibraryUser, String>{
}