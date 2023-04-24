package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(Movie movie){
       Optional<Movie> movieOpt =  movieRepository.getMovieByName(movie.getName());
       if(movieOpt.isEmpty()) {
           movieRepository.addMovie(movie);
           return "Movie added Successfully";
       }
       return "Movie already exists";
    }

    public String addDirector(Director director){
        Optional<Director> directorOpt = movieRepository.getDirectorByName(director.getName());
        if(directorOpt.isEmpty()) {
            movieRepository.addDirector(director);
            return "Director added Successfully";
        }
        return "Director already exists";
    }

    public void addMovieDirectorPair(String moviename, String directorname){
        movieRepository.addMovieDirectorPair(moviename, directorname);
    }

    public Movie getMovieByName(String moviename){
        Optional<Movie> movieOpt = movieRepository.getMovieByName(moviename);
        return movieOpt.orElse(null);
    }

    public Director getDirectorByName(String directorname){
        Optional<Director> directorOpt = movieRepository.getDirectorByName(directorname);
        return directorOpt.orElse(null);
    }

    public List<String> getMoviesByDirectorName(String directorname){
        Optional<List<String>> Opt = movieRepository.getMoviesByDirectorName(directorname);
        return Opt.orElse(new ArrayList<>());
    }

    public List<String> findAllMovies(){
        Optional<List<String>> Opt = movieRepository.getAllMovies();
        return Opt.orElse(new ArrayList<>());
    }

    public void deleteDirectorByName(String directorname){
        movieRepository.deleteDirectorRecords(directorname);
    }

    public void deleteAllDirectors(){
        movieRepository.deleteAllMoviesandDirectors();
    }
}
