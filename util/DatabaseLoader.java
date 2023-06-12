package com.Softito.MovieCatalog.util;

import com.Softito.MovieCatalog.enums.Role;
import com.Softito.MovieCatalog.model.Actor;
import com.Softito.MovieCatalog.model.Movie;
import com.Softito.MovieCatalog.model.Review;
import com.Softito.MovieCatalog.model.User;
import com.Softito.MovieCatalog.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Autowired

    public DatabaseLoader(ActorRepository actorRepository, MovieRepository movieRepository, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }



    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setPassword("123");
        user.setRole(Role.ADMIN);

        userRepository.save(user);

        Movie movie = new Movie();
        movie.setName("Exorcist");
        movie.setGenre("Horror");
        movie.setLanguage("English");
        movie.setReleaseYear(1973);
        movie.setStory("When a teenage girl is possessed by a mysterious entity, her mother seeks the help of two priests to save her daughter.");

        movieRepository.save(movie);

        List<Actor> actors = new ArrayList<>();

        Actor actor1 = new Actor();
        actor1.setMovie(movieRepository.getById(movie.getMovieId()));
        actor1.setFullName("Ellen Burstyn");
        actor1.setActorRole("Chris MacNeil");

        actorRepository.save(actor1);

        Actor actor2 = new Actor();
        actor2.setMovie(movieRepository.getById(movie.getMovieId()));
        actor2.setFullName("Max von Sydow");
        actor2.setActorRole("Father Merrin");

        actorRepository.save(actor2);

        movie.setActors(actors);

        List<Review> reviews = new ArrayList<>();

        Review review1 = new Review();
        review1.setComment("Nice one");
        review1.setMovie(movieRepository.getById(movie.getMovieId()));
        review1.setUser(userRepository.getById(user.getUserId()));

        reviewRepository.save(review1);

        Review review2 = new Review();
        review2.setComment("Scared the shit out of me");
        review2.setMovie(movieRepository.getById(movie.getMovieId()));
        review2.setUser(userRepository.getById(user.getUserId()));

        reviewRepository.save(review2);

        Review review3 = new Review();
        review3.setComment("Overrated");
        review3.setMovie(movieRepository.getById(movie.getMovieId()));
        review3.setUser(userRepository.getById(user.getUserId()));

        reviewRepository.save(review3);



    }
}
