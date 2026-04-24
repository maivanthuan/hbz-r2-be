package com.hbz.hbzr2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbz.hbzr2.model.Author;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
     Page<Author> findAllByOrderByIdAsc(Pageable pageable);
}
