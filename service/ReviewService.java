package com.Softito.MovieCatalog.service;

import com.Softito.MovieCatalog.enums.Role;
import com.Softito.MovieCatalog.model.Movie;
import com.Softito.MovieCatalog.model.Review;
import com.Softito.MovieCatalog.model.User;
import com.Softito.MovieCatalog.repository.MovieRepository;
import com.Softito.MovieCatalog.repository.ReviewRepository;
import com.Softito.MovieCatalog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    private UserRepository userRepository;

    private MovieRepository movieRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }



    public Review saveReview(Review review) {

        return reviewRepository.save(review);
    }



    public Review getReviewById(Long id) {
        return reviewRepository.getById(id);
    }

    public Review updateReview(Long id, Review updatedReview) {

        Review review = getReviewById(id);

        review.setComment(updatedReview.getComment());

        return reviewRepository.save(review);
    }



    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

}
