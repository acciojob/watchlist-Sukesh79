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
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {

        String message = movieService.addMovie(movie);

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {

        String message = movieService.addDirector(director);

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName") String movieName,
                                                       @RequestParam("directorName") String directorName) {

        String message = movieService.addMovieDirectorPair(movieName, directorName);

        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name) {

        Movie movie = movieService.getMovieByName(name);

        return new ResponseEntity<>(movie, HttpStatus.FOUND);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name) {

        Director director = movieService.getDirectorByName(name);

        return new ResponseEntity<>(director, HttpStatus.FOUND);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String director) {

        List<String> movies = movieService.getMoviesByDirectorName(director);

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {

        List<String> movies = movieService.findAllMovies();

        return new ResponseEntity<>(movies, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("directorName") String directorName) {

        String message = movieService.deleteDirectorByName(directorName);

        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {

        String message = movieService.deleteAllDirectors();

        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

}