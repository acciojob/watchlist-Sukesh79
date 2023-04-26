package com.driver;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
@Repository
public class MovieRepository {
    private HashMap<String,Movie> movieMap;
    private HashMap<String,Director> directorMap;
    private HashMap<String, List<String>> DirectorMovieMap;

    public MovieRepository() {
        this.movieMap = new HashMap<String,Movie>();
        this.directorMap = new HashMap<String,Director>();
        DirectorMovieMap = new HashMap<String,List<String>>();
    }
    public void saveMovie(Movie movie){
        movieMap.put(movie.getName(),movie);
    }
    public void saveDirector(Director director){
        directorMap.put(director.getName(),director);

    }
    public void saveMovieDirectorPair(String movie,String director){
        if(movieMap.containsKey((movie)) && directorMap.containsKey(director)){
            List<String> currList=new ArrayList<>();
            if(DirectorMovieMap.containsKey(director)) currList=DirectorMovieMap.get(director);
            currList.add(movie);
            DirectorMovieMap.put(director,currList);
        }

    }
    public Movie findMovies(String name){
        return movieMap.get(name);
    }
    public Director findDirector(String director){
        return directorMap.get(director);
    }
    public List<String> findMoviesFromDirector(String director){
        return DirectorMovieMap.get(director);
    }
    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }
    public void deleteDirector(String director) {
        List<String> movie = new ArrayList<>();
        if (DirectorMovieMap.containsKey(director)) {
            movie = DirectorMovieMap.get(director);
            for (String movies : movie) {
                if (movieMap.containsKey(movies)) {
                    movieMap.remove(movies);
                }
            }
            DirectorMovieMap.remove(director);

        }
        if (directorMap.containsKey(director)) {
            directorMap.remove(director);
        }
    }
    public void deleteAllDirectors() {
        HashSet<String> set = new HashSet<>();
        for (String director : DirectorMovieMap.keySet()) {
            for (String movie : DirectorMovieMap.get(director)) {
                set.add(movie);
            }
        }
        for (String movies : set) {
            if (movieMap.containsKey(movies))
                movieMap.remove(movies);
        }
    }
}