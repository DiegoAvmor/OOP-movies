package moviewebservice.controller;

import moviewebservice.model.Genre;
import moviewebservice.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    GenreService genreService;

    @GetMapping
    public List<Genre> getGenres() {
        return genreService.getAllGenres();
    }
}
