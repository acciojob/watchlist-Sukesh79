package com.driver;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {

    // movieName, movie hashmap
    HashMap<String, Movie> movieHashMap = new HashMap<>();

    // directorName, director hashmap
    HashMap<String, Director> directorHashMap = new HashMap<>();

    // directorName, movieList hashmap
    HashMap<String, List<Movie>> directorMoviePairHashMap = new HashMap<>();


    public String addMovie(Movie movie) {

        // add movie in hashmap
        String key = movie.getName();

        movieHashMap.put(key, movie);

        return "Movie added successfully";
    }

    public String addDirector(Director director) {

        String key = director.getName();

        directorHashMap.put(key, director);

        return "Director added successfully";
    }

    public String addMovieDirectorPair(String movieName, String directorName) {

        List<Movie> movies = new ArrayList<>();

        if (directorMoviePairHashMap.containsKey(directorName)) {

            movies = directorMoviePairHashMap.get(directorName);
        }

        Movie movie = movieHashMap.get(movieName);

        movies.add(movie);

        // putting list of movies in director-movie pair hashmap
        directorMoviePairHashMap.put(directorName, movies);

        return "Pairing done";
    }


    public List<Movie> getAllMovies() {

        List<Movie> movieList = new ArrayList<>();

        for (Movie movie : movieHashMap.values()) {

            movieList.add(movie);
        }

        return movieList;
    }

    public Director getDirectorByName(String directorName) {

        return directorHashMap.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName) {

        List<String> movies = new ArrayList<>();

        for (Movie movie : directorMoviePairHashMap.get(directorName)) {

            movies.add(movie.getName());
        }

        return movies;
    }


    public String deleteDirectorByName(String directorName) {

        // delete all records of director from all 3 hashmaps

        directorHashMap.remove(directorName);

        List<Movie> movies = directorMoviePairHashMap.get(directorName);

        for (Movie movie : movies) {

            String key = movie.getName();

            movieHashMap.remove(key);
        }

        directorMoviePairHashMap.remove(directorName);

        return "director removed successfully";
    }



}