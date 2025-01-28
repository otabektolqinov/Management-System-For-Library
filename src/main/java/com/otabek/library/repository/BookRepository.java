package com.otabek.library.repository;

import com.otabek.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByIdAndDeletedAtIsNull(Integer bookId);

    List<Book> findAllByCategory_Name(String categoryName);
}
