package webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.repository.MovieGenreRepository;

@Service
public class MovieGenreService {
    @Autowired
    MovieGenreRepository movieGenreRepository;
}
