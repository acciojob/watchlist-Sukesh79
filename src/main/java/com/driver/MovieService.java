package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;


    public String addMovie(Movie movie) {

        return movieRepository.addMovie(movie);
    }

    public String addDirector(Director director) {

        return movieRepository.addDirector(director);
    }

    public String addMovieDirectorPair(String movieName, String directorName) {

        return movieRepository.addMovieDirectorPair(movieName, directorName);
    }

    public Movie getMovieByName(String movieName) {

        List<Movie> movieList = movieRepository.getAllMovies();

        for (Movie movie : movieList) {

            if (movie.getName().equals(movieName)) {
                return movie;
            }
        }

        return null;
    }

    public Director getDirectorByName(String directorName) {

        return movieRepository.getDirectorByName(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName) {

        return movieRepository.getMoviesByDirectorName(directorName);
    }

    public List<String> findAllMovies() {

        List<String> movies = new ArrayList<>();

        List<Movie> movieList = movieRepository.getAllMovies();

        for (Movie movie : movieList) {

            movies.add(movie.getName());
        }

        return movies;
    }

    public String deleteDirectorByName(String directorName) {

        return movieRepository.deleteDirectorByName(directorName);
    }

    public String deleteAllDirectors() {

        for (String directorName : movieRepository.directorHashMap.keySet()) {

            movieRepository.deleteDirectorByName(directorName);
        }

        return "all directors' records deleted successfully";
    }
}