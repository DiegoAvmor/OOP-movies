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

    @PostConstruct
    public void printData() {

        if(movieGenreRepository.findAll().size() != 0)
            for(MovieGenre movieGenre : movieGenreRepository.findAll())
                System.out.println(movieGenre);
        else
            System.out.println("WARN: No records found in movies-service.movies_genres table.");
    }
}
