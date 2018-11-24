package moviewebservice.service;

import moviewebservice.model.MovieGenre;
import moviewebservice.repository.MovieGenreRepository;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MovieGenreService {
    @Autowired
    MovieGenreRepository movieGenreRepository;
}
