package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    Map<String, Movie> moviedb;
    Map<String, Director> directordb;
    Map<String, List<String>> movieDirectorPair;

    public MovieRepository(){
        moviedb = new HashMap<>();
        directordb = new HashMap<>();
        movieDirectorPair = new HashMap<>();
    }

    public boolean addMovie(Movie movie){
        moviedb.put(movie.getName(), movie);
        return true;
    }

    public Optional<Movie> getMovieByName(String name){
        if(moviedb.containsKey(name))
            return Optional.of(moviedb.get(name));
        return Optional.empty();
    }

    public Optional<List<String>> getAllMovies(){
        return Optional.ofNullable(moviedb.keySet().stream().toList());
    }
    public void removeMovieByName(String name){
        moviedb.remove(name);
    }

    public boolean addDirector(Director director){
        directordb.put(director.getName(), director);
        return true;
    }

    public Optional<Director> getDirectorByName(String name){
        if(directordb.containsKey(name))
            return Optional.of(directordb.get(name));
        return Optional.empty();
    }

    public Optional<List<String>> getAllDirectors(){
        return Optional.ofNullable(directordb.keySet().stream().toList());
    }
    public void removeDirectorByName(String name){
        directordb.remove(name);
    }

    public boolean addMovieDirectorPair(String moviename, String directorname){
        List<String> list;
        if(movieDirectorPair.containsKey(directorname))
            list = movieDirectorPair.get(directorname);
        else list = new ArrayList<>();

        list.add(moviename);
        movieDirectorPair.put(directorname,list);

        return true;
    }

    public Optional<List<String>> getMoviesByDirectorName(String directorname){
        if(movieDirectorPair.containsKey(directorname))
            return Optional.of(movieDirectorPair.get(directorname));
        else return Optional.empty();
    }

    public void deleteDirectorRecords(String directorname){
        if(movieDirectorPair.containsKey(directorname))
            movieDirectorPair.remove(directorname);
        if(directordb.containsKey(directorname))
            directordb.remove(directorname);
    }

    public void deleteAllMoviesandDirectors(){
        moviedb.clear();
        movieDirectorPair.clear();
        directordb.clear();
    }
}
