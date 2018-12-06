package webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.controller.AccountController;
import webservice.repository.AccountRepository;
import webservice.repository.MovieGenreRepository;

/**
 * Provee una capa de separación entre su controlador (aún no implementado) y
 * le interfaz {@link AccountRepository}.
 */
@Service
public class MovieGenreService {
    @Autowired
    MovieGenreRepository movieGenreRepository;
}
