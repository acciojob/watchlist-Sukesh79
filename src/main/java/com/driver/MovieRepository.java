package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

@Repository
public class MovieRepository {
    HashMap<String, Movie> movieDB = new HashMap<>();
    HashMap<String, Director> directorDB = new HashMap<>();
    HashMap<String, List<String>> directorMovieDB = new HashMap<>();

    public String addMovie(Movie movie) {
        String moviename = movie.getName();

        movieDB.put(moviename, movie);
        return "successfully movie added";
    }

    public String addDirector(Director director) {
        String directorname = director.getName();
        directorDB.put(directorname, director);
        return "successfully director added";
    }

    public List<Movie> getAllMovies() {
        List<Movie> list = new ArrayList<>();
        for (Movie movie : movieDB.values()) {
            list.add(movie);
        }
        return list;
    }

    public List<Director> getAllDirectors() {
        List<Director> list = new ArrayList<>();
        for (Director director : directorDB.values()) {
            list.add(director);
        }
        return list;
    }

    public List<String> findAllMovies() {
        List<String> list = new ArrayList<>();
        for (String s : movieDB.keySet()) {
            list.add(s);
        }
        return list;
    }

    public List<String> getMoviesByDirectorName(String name) {
        List<String> movieList =new ArrayList<>();
        for(String directorname:directorMovieDB.keySet())
            if (directorname.equals(name)) {
                return directorMovieDB.get(directorname);
            }
        return movieList;
    }

    public String addMovieDirectorPair(String movieName, String directorName) {
        if(!directorMovieDB.containsKey(directorName)){
            List<String> movieList= new ArrayList<>();
            movieList.add(movieName);
            directorMovieDB.put(directorName,movieList);
        }
        else{
            directorMovieDB.get(directorName).add(movieName);
        }

        return "successfully added";
    }

    public  String deleteDirectorByName(String directorName) {
        if(directorMovieDB.containsKey(directorName)){
            List<String> moviesByDirector=directorMovieDB.get(directorName);
            for(String movieName: moviesByDirector){
                deleteByMovieName(movieName);
            }
            directorMovieDB.remove(directorName);
        }
        if(directorDB.containsKey(directorName))
            directorDB.remove(directorName);
        return "successfully deleted";
    }

    public void deleteByMovieName(String movieName) {
        if(movieDB.containsKey(movieName)){
            movieDB.remove(movieName);
        }
    }
    public String deleteAllDirectors() {
        List<String> directorsList=new ArrayList<>();
        for(String directorname:directorDB.keySet()){
            directorsList.add(directorname);
        }
        for(String directorName:directorsList){
            if(directorMovieDB.containsKey(directorName)){
                List<String> moviesByDirector=directorMovieDB.get(directorName);
                for(String movieName: moviesByDirector){
                    deleteByMovieName(movieName);
                }
            }
            directorDB.clear();
            directorMovieDB.clear();}
        return "successfully deleted all directors";
    }
}