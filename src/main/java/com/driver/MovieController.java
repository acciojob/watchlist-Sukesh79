package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/movies")
@Controller
public class MovieController {


    MovieService movieService= new MovieService();
    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String answer=movieService.addMovie(movie);
        return new ResponseEntity(answer, HttpStatus.CREATED);
    }
    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String answer=movieService.addDirector(director);
        return new ResponseEntity(answer,HttpStatus.CREATED);
    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name){
        Optional<Movie> op=movieService.getMovieByName(name);
        if(op.isPresent()){
            return new ResponseEntity(op.get(),HttpStatus.FOUND);
        }
        return new ResponseEntity("Movie Not found",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String name){
        Optional<Director> op=movieService.getDirectorByName(name);
        if(op.isPresent()){
            return new ResponseEntity(op.get(),HttpStatus.FOUND);
        }
        return new ResponseEntity("Director Not found",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/get-movies-by-director-name/{name}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable String name){
        List<String> movieList=movieService.getMoviesByDirectorName(name);
        return new ResponseEntity(movieList,HttpStatus.FOUND);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String> movieList=movieService.findAllMovies();
        return new ResponseEntity(movieList,HttpStatus.FOUND);
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam String movieName,@RequestParam String directorName){
        String answer=movieService.addMovieDirectorPair(movieName,directorName);
        return new ResponseEntity(answer,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String name){
        String answer=movieService.deleteDirectorByName(name);
        return new ResponseEntity(answer,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String answer=movieService.deleteAllDirectors();
        return new ResponseEntity(answer,HttpStatus.ACCEPTED);
    }
}