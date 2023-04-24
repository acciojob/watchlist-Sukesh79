package com.driver;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {
    MovieRepository movieRepository= new MovieRepository();
    public String addMovie(Movie movie){
        String answer=movieRepository.addMovie(movie);
        return answer;
    }
    public String addDirector(Director director){
        String answer=movieRepository.addDirector(director);
        return answer;
    }
    public Optional<Movie> getMovieByName(String name){
        List<Movie> listMovies=movieRepository.getAllMovies();
        for(Movie movie:listMovies){
            if(name.equals(movie.getName())){
                return Optional.of(movie);
            }
        }
        return Optional.empty();
    }
    public Optional<Director> getDirectorByName(String name){
        List<Director> listDirectors=movieRepository.getAllDirectors();
        for(Director director:listDirectors){
            if(name.equals(director.getName())){
                return Optional.of(director);
            }
        }
        return Optional.empty();
    }
    public List<String> findAllMovies(){
        List<String> movieList= movieRepository.findAllMovies();
        return movieList;
    }

    public List<String> getMoviesByDirectorName(String name) {
        List<String> movieList= movieRepository.getMoviesByDirectorName(name);
        return movieList;
    }

    public String addMovieDirectorPair(String movieName, String directorName) {
        String answer= movieRepository.addMovieDirectorPair(movieName,directorName);
        Optional<Director> op=getDirectorByName(directorName);
        op.get().setNumberOfMovies(op.get().getNumberOfMovies()+1);
        return answer;
    }

    public String deleteDirectorByName(String name) {
        return movieRepository.deleteDirectorByName(name);
    }

    public String deleteAllDirectors() {
        return movieRepository.deleteAllDirectors();
    }
}