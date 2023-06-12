package com.Softito.MovieCatalog.service;

import com.Softito.MovieCatalog.enums.Role;
import com.Softito.MovieCatalog.model.Actor;
import com.Softito.MovieCatalog.model.Movie;
import com.Softito.MovieCatalog.model.User;
import com.Softito.MovieCatalog.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    private ActorRepository actorRepository;
    private MovieRepository movieRepository;
    private ReviewRepository reviewRepository;
    private ActorService actorService;

    @Autowired
    public MovieService(ActorRepository actorRepository, MovieRepository movieRepository, ReviewRepository reviewRepository, ActorService actorService) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
        this.actorService = actorService;
    }



    public Movie saveMovieWithPoster(Movie movie, MultipartFile poster) throws IOException {
        if (poster != null && !poster.isEmpty()) {
            movie.setPoster(poster.getBytes());
        }
        return movieRepository.save(movie);
    }



    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }



    public Movie getMovieById(Long id) {
        return movieRepository.getById(id);
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {

        Movie movie = getMovieById(id);

        movie.setName(updatedMovie.getName());
        movie.setReleaseYear(updatedMovie.getReleaseYear());
        movie.setStory(updatedMovie.getStory());
        movie.setLanguage(updatedMovie.getLanguage());
        movie.setGenre(updatedMovie.getGenre());

        return movieRepository.save(movie);
    }



    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

}
