package com.zee.zee5app.repo;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;

public interface MovieRepository {
	
	public Movie insertMovie(Movie movie) throws UnableToGenerateIdException;
    public Movie updateMovie(String movieId, Movie movie) throws InvalidIdException;

    public Optional<Movie> getMovieByMovieId(String movieId);
    public Optional<List<Movie>> getAllMovies();
    public Optional<List<Movie>> getAllMoviesByGenre(Genres genre);
    public Optional<List<Movie>> getAllMoviesByName(String movieName);
    
    public Optional<List<Movie>> findByOrderByMovieNameDsc();
    
    public String deleteMovieByMovieId(String movieId) throws Exception;
	
	
}
