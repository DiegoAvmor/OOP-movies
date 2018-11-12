package com.cervera.moviesservice.Repository;

import com.cervera.moviesservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
