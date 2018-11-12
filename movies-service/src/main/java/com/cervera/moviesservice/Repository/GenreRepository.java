package com.cervera.moviesservice.Repository;

import com.cervera.moviesservice.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
