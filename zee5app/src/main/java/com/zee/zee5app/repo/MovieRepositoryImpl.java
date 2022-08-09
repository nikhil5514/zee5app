package com.zee.zee5app.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.utils.DBUtils;

public class MovieRepositoryImpl implements MovieRepository {

	private static MovieRepository movieRepo;
	
	private MovieRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static MovieRepository getInstance() {
		if(movieRepo == null) {
			movieRepo = new MovieRepositoryImpl();
		}
		return movieRepo;
	}
	
	private DBUtils dbUtils = DBUtils.getInstance();
	
	@Override
	public Movie insertMovie(Movie movie) throws UnableToGenerateIdException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		// In jdbc, qs mark count will start with 1
		String insertStatement = "insert into movie_table"
				+ " (movieid, actors, moviename, director, genre, production, languages, movielength, trailer)"
				+ " values(?,?,?,?,?,?,?,?,?)";
		
		//connection object
		connection = dbUtils.getConnection();
		
		//statement object(prepared)
		try {
			
			preparedStatement = connection.prepareStatement(insertStatement);
			
			preparedStatement.setString(1, dbUtils.movieIdGenerator(movie.getMovieName()));
			
			String actors = String.join(",", movie.getActors());
			preparedStatement.setString(2, actors);
			
			preparedStatement.setString(3, movie.getMovieName());
			preparedStatement.setString(4, movie.getDirector());
			preparedStatement.setString(5, movie.getGenre().toString());
			preparedStatement.setString(6, movie.getProduction());
			
			String languages = String.join(",", movie.getLanguages());
			preparedStatement.setString(7, languages);
			
			preparedStatement.setFloat(8, movie.getMovieLength());
			preparedStatement.setString(9, movie.getTrailer());
			
			int result = preparedStatement.executeUpdate();
			if(result > 0) {
				return movie;
			}
			else {
				return null;
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			dbUtils.closeConnection(connection);
		}
		
		//we should get the result, based on that we will return the result
		
		return null;

	}

	@Override
	public Movie updateMovie(String movieId, Movie movie) {
		// TODO Auto-generated method stub
		
		
		
		return null;
	}

	@Override
	public Optional<Movie> getMovieByMovieId(String movieId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Movie[]> getAllMovies() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Movie[] getAllMoviesByGenre(String genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie[] getAllMoviesByName(String movieName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findByOrderByMovieNameDsc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteMovieByMovieId(String movieId) {
		// TODO Auto-generated method stub
		return null;
	}

}
