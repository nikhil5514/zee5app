package com.zee.zee5app.repo;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;

public interface MovieRepository {
	
	public Movie insertMovie(Movie movie) throws UnableToGenerateIdException;
    public Movie updateMovie(String movieId, Movie movie);

    public Optional<Movie> getMovieByMovieId(String movieId);
    public Optional<Movie[]> getAllMovies();
    public Movie[] getAllMoviesByGenre(String genre);
    public Movie[] getAllMoviesByName(String movieName);
    
    public List<Movie> findByOrderByMovieNameDsc();
    
    public String deleteMovieByMovieId(String movieId);
	
	
}
