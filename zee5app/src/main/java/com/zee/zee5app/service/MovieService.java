package com.zee.zee5app.service;

import java.io.FileNotFoundException;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;

public interface MovieService {

	public Movie insertMovie(Movie movie) throws FileNotFoundException, UnableToGenerateIdException;
    public Movie updateMovie(String movieId, Movie movie);

    public Optional<Movie> getMovieByMovieId(String movieId);
    public Optional<Movie[]> getAllMovies();
    public Movie[] getAllMoviesByGenre(String genre);
    public Movie[] getAllMoviesByName(String movieName);
    
    public String deleteMovieByMovieId(String movieId);

}
