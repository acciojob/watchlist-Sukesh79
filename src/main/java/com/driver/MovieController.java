package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        String ans = movieService.addMovie(movie);
        return new ResponseEntity(ans, HttpStatus.OK);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
            String ans = movieService.addDirector(director);
            return new ResponseEntity<>(ans, HttpStatus.OK);
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String moviename, @RequestParam String directorname){
        movieService.addMovieDirectorPair(moviename, directorname);
        return new ResponseEntity<>("Pair is add successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
       Movie movie = movieService.getMovieByName(name);
       if(movie!=null) return new ResponseEntity<>(movie, HttpStatus.FOUND);
       else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
       Director director = movieService.getDirectorByName(name);
       if(director!=null) return new ResponseEntity<>(director, HttpStatus.FOUND);
       else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director){
        List<String> list = movieService.getMoviesByDirectorName(director);
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> list = movieService.findAllMovies();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String directorname){
        movieService.deleteDirectorByName(directorname);
        return new ResponseEntity<>("Director and files are deleted", HttpStatus.OK);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All are deleted", HttpStatus.OK);
    }



}
