package com.Softito.MovieCatalog.controller;

import com.Softito.MovieCatalog.model.Actor;
import com.Softito.MovieCatalog.model.Movie;
import com.Softito.MovieCatalog.model.Review;
import com.Softito.MovieCatalog.model.User;
import com.Softito.MovieCatalog.repository.ReviewRepository;
import com.Softito.MovieCatalog.service.ActorService;
import com.Softito.MovieCatalog.service.MovieService;
import com.Softito.MovieCatalog.service.ReviewService;
import com.Softito.MovieCatalog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {
    private MovieService movieService;

    private ReviewService reviewService;

    private ActorService actorService;

    @Autowired
    public MovieController(MovieService movieService, ReviewService reviewService, ActorService actorService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
        this.actorService = actorService;
    }



    @GetMapping("/addMovie")
    public String showAddMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "addMovie";
    }

    @PostMapping("/addMovie")
    public String addMovie(@ModelAttribute("movie") Movie movie, MultipartFile poster, Model model) throws IOException {

        model.addAttribute("successMessage", "Movie added successfully");

        movieService.saveMovieWithPoster(movie, poster);
        return "addMovie";
    }



    @GetMapping("/listAllMovies")
    public String getAllMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());

        return "listAllMovies";
    }



    @GetMapping("/updateMovie/{id}")
    public String showUpdateMovieById(@PathVariable("id") Long id, Model model) {

        model.addAttribute("movie", movieService.getMovieById(id));

        return "updateMovie";
    }

    @PostMapping("/updateMovie/{id}")
    public String updateMovieById(@PathVariable("id") Long id, @ModelAttribute("movie") Movie updatedMovie, Model model) {

        model.addAttribute("movie", updatedMovie);

        movieService.updateMovie(id, updatedMovie);
        return "redirect:/listAllMovies";
    }



    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
        return "redirect:/listAllMovies";
    }








    @GetMapping("/addActor/{id}")
    public String showAddActor(@PathVariable("id") Long id, Model model) {

        model.addAttribute("actor", new Actor());
        return "addActor";
    }

    @PostMapping("/addActor/{id}")
    public String AddActor(@PathVariable("id") Long id, @ModelAttribute("actor") Actor actor) {

        actor.setMovie(movieService.getMovieById(id));

        actorService.saveActor(actor);
        return "redirect:/listAllMovies";
    }



    @GetMapping("/updateActor/{id}")
    public String showUpdateActorById(@PathVariable("id") Long id, Model model) {

        model.addAttribute("actor", actorService.getActorById(id));

        return "updateActor";
    }

    @PostMapping("/updateActor/{id}")
    public String updateActorById(@PathVariable("id") Long id, @ModelAttribute("actor") Actor updatedActor, Model model) {

        model.addAttribute("actor", updatedActor);

        actorService.updateActor(id, updatedActor);
        return "redirect:/listAllMovies";
    }



    @GetMapping("/deleteActor/{id}")
    public String deleteActor(@PathVariable("id") Long id) {
        actorService.deleteActor(id);
        return "redirect:/listAllMovies";
    }

















    @GetMapping("/addReview/{id}")
    public String showAddReview(@PathVariable("id") Long id, Model model) {

        model.addAttribute("review", new Review());
        return "addReview";
    }

    @PostMapping("/addReview/{id}")
    public String addReview(@PathVariable("id") Long id, @ModelAttribute("review") Review review) {

        review.setMovie(movieService.getMovieById(id));

        reviewService.saveReview(review);
        return "redirect:/listAllMovies";
    }



    @GetMapping("/updateReview/{id}")
    public String showUpdateReviewById(@PathVariable("id") Long id, Model model) {

        model.addAttribute("review", reviewService.getReviewById(id));

        return "updateReview";
    }

    @PostMapping("/updateReview/{id}")
    public String updateReviewById(@PathVariable("id") Long id, @ModelAttribute("review") Review updatedReview, Model model) {

        model.addAttribute("review", updatedReview);

        reviewService.updateReview(id, updatedReview);
        return "redirect:/listAllMovies";
    }



    @GetMapping("/deleteReview/{id}")
    public String deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);
        return "redirect:/listAllMovies";
    }

}
