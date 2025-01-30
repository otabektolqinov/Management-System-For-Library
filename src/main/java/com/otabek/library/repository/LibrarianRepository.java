package com.otabek.library.repository;

import com.otabek.library.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {

    Optional<Librarian> findByIdAndDeletedAtIsNull(Integer id);

    List<Librarian> findAllByDeletedAtIsNull();

}
