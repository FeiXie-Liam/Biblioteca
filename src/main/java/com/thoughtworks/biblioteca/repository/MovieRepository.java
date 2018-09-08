package com.thoughtworks.biblioteca.repository;

import com.thoughtworks.biblioteca.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieRepository {
    private List<Movie> movies;

    public MovieRepository() {
        initMovies();
    }

    private void initMovies() {
        movies = new ArrayList<>();
        movies.add(Movie
                .builder()
                .id(0)
                .name("肖生克的救赎")
                .director("Frank Darabont")
                .publishYear("1994")
                .rating("9.6")
                .build()
        );
        movies.add(Movie
                .builder()
                .id(1)
                .name("霸王别姬")
                .director("陈凯歌")
                .publishYear("1993")
                .rating("9.5")
                .build()
        );
        movies.add(Movie
                .builder()
                .id(2)
                .name("这个杀手不太冷")
                .director("Luc Besson")
                .publishYear("1994")
                .rating("9.4")
                .build()
        );
    }

    public List<Movie> getValidMovies() {
        return movies.stream().filter(movie -> !movie.isCheckout()).collect(Collectors.toList());
    }

    public boolean checkoutMovie(int movieId) {
        List<Movie> selectedMovie = movies.stream().filter(movie -> movie.getId() == movieId && !movie.isCheckout())
                .collect
                        (Collectors.toList());
        if (selectedMovie.size() == 0) {
            return false;
        }
        movies.forEach(movie -> {
            if (movie.getId() == movieId) {
                movie.setCheckout(true);
            }
        });
        return true;
    }
}
