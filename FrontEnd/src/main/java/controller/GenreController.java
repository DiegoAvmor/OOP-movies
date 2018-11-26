package controller;

import com.example.model.Genre;
import com.example.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class GenreController {
    @Autowired
    GenreService genreService;

    public List<Genre> getGenres() {
        return genreService.getAllGenres();
    }
}
