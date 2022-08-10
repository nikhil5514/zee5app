package com.zee.zee5app.service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;

public interface MovieService {

	public Movie insertMovie(Movie movie) throws FileNotFoundException, UnableToGenerateIdException;
    public Movie updateMovie(String movieId, Movie movie) throws Exception;

    public Optional<Movie> getMovieByMovieId(String movieId);
    public Optional<List<Movie>> getAllMovies();
    public Optional<List<Movie>> getAllMoviesByGenre(Genres genre);
    public Optional<List<Movie>> getAllMoviesByName(String movieName);
    public Optional<List<Movie>> findByOrderByMovieNameDsc();
    public String deleteMovieByMovieId(String movieId) throws Exception;

}
