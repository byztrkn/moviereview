package com.Softito.MovieCatalog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String name;

    private Integer releaseYear;

    private String story;

    private byte[] poster;

    private String language;

    private String genre;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Actor> actors;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Review> reviews;
}
