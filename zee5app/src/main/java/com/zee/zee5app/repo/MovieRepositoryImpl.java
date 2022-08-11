package com.zee.zee5app.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.InvalidNameException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

//	private static MovieRepository movieRepo;
//	
//	private MovieRepositoryImpl() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	public static MovieRepository getInstance() {
//		if(movieRepo == null) {
//			movieRepo = new MovieRepositoryImpl();
//		}
//		return movieRepo;
//	}
	
	@Autowired
	private DBUtils dbUtils;
	
	@Autowired
	private DataSource dataSource;
	
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
		//connection = dbUtils.getConnection();
		
		//statement object(prepared)
		try {
			
			connection = dataSource.getConnection();
			
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
	
	public Movie updateMovie(String movieId, Movie movie) throws InvalidIdException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateQuery = "upadte movie_table "
				+ "set actors=?, moviename=?, director=?, genre=?, production=?, languages=?, movielength=?, trailer=? "
				+ "where movieid=?";

		//connection = dbUtils.getConnection();
		try {
			
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, movieId);
			preparedStatement.setString(2, String.join(",", movie.getActors()));
			preparedStatement.setString(3, movie.getMovieName());
			preparedStatement.setString(4, movie.getDirector());
			preparedStatement.setString(5, movie.getGenre().toString());
			preparedStatement.setString(6, movie.getProduction());
			preparedStatement.setString(7, String.join(",", String.join(",", movie.getLanguages())));
			preparedStatement.setFloat(8, movie.getMovieLength());
			preparedStatement.setString(9, movie.getTrailer());
			
			int result = preparedStatement.executeUpdate();
			
			if (result > 0) {
				return movie;
			} else {
				throw new InvalidIdException("movieId not found");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}

		return null;
	}

	@Override
	public Optional<Movie> getMovieByMovieId(String movieId) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "select * from movie_table where movieId=?";
		
		//connection = dbUtils.getConnection();
		
		try {
			
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, movieId);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				Movie movie = new Movie();
				
				movie.setMovieId(movieId);
				movie.setActors(resultSet.getString("actors").split(","));
				movie.setMovieName(resultSet.getString("moviename"));
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(Genres.valueOf(resultSet.getString("genre")));
				movie.setProduction(resultSet.getString("production"));
				movie.setLanguages(resultSet.getString("languages").split(","));
				movie.setMovieLength(resultSet.getFloat("movielength"));
				movie.setTrailer(resultSet.getString("trailer"));
				
				return Optional.of(movie);
			}
			else {
				return Optional.empty();
			}
			
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<List<Movie>> getAllMovies() {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Movie> movies = new ArrayList<Movie>();
		
		String query = "select * from movie_table";
		
		//connection = dbUtils.getConnection();
		
		try {
			
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(query);
			//preparedStatement.setString(1, movieId);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Movie movie = new Movie();
				
				movie.setMovieId(resultSet.getString("movieid"));
				movie.setActors(resultSet.getString("actors").split(","));
				movie.setMovieName(resultSet.getString("moviename"));
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(Genres.valueOf(resultSet.getString("genre")));
				movie.setProduction(resultSet.getString("production"));
				movie.setLanguages(resultSet.getString("languages").split(","));
				movie.setMovieLength(resultSet.getFloat("movielength"));
				movie.setTrailer(resultSet.getString("trailer"));
				
				movies.add(movie);
			}
			return Optional.of(movies);
			
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<List<Movie>> getAllMoviesByGenre(Genres genre) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		List<Movie> movies = new ArrayList<Movie>();
		
		String query = "select * from movie_table where genre = ?";
		
		//connection = dbUtils.getConnection();
		
		try {
			
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(5, genre.toString());
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Movie movie = new Movie();
				
				movie.setMovieId(resultSet.getString("movieid"));
				movie.setActors(resultSet.getString("actors").split(","));
				movie.setMovieName(resultSet.getString("moviename"));
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(genre);
				movie.setProduction(resultSet.getString("production"));
				movie.setLanguages(resultSet.getString("languages").split(","));
				movie.setMovieLength(resultSet.getFloat("movielength"));
				movie.setTrailer(resultSet.getString("trailer"));
				
				movies.add(movie);
			}
			return Optional.of(movies);
			
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		
		return Optional.empty();

	}

	@Override
	public Optional<List<Movie>> getAllMoviesByName(String movieName) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		List<Movie> movies = new ArrayList<Movie>();
		
		String query = "select * from movie_table where moviename = ?";
		
		//connection = dbUtils.getConnection();
		
		try {
			
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(3, movieName);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Movie movie = new Movie();
				
				movie.setMovieId(resultSet.getString("movieid"));
				movie.setActors(resultSet.getString("actors").split(","));
				movie.setMovieName(movieName);
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(Genres.valueOf(resultSet.getString("genre")));
				movie.setProduction(resultSet.getString("production"));
				movie.setLanguages(resultSet.getString("languages").split(","));
				movie.setMovieLength(resultSet.getFloat("movielength"));
				movie.setTrailer(resultSet.getString("trailer"));
				
				movies.add(movie);
			}
			return Optional.of(movies);
			
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		
		return Optional.empty();

		
	}

	@Override
	public Optional<List<Movie>> findByOrderByMovieNameDsc() {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Movie> movies = new ArrayList<Movie>();

		String query = "select * from movie_table order by moviename desc";

		//connection = dbUtils.getConnection();

		try {
			
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, movieId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Movie movie = new Movie();

				movie.setMovieId(resultSet.getString("movieid"));
				movie.setActors(resultSet.getString("actors").split(","));
				movie.setMovieName(resultSet.getString("moviename"));
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(Genres.valueOf(resultSet.getString("genre")));
				movie.setProduction(resultSet.getString("production"));
				movie.setLanguages(resultSet.getString("languages").split(","));
				movie.setMovieLength(resultSet.getFloat("movielength"));
				movie.setTrailer(resultSet.getString("trailer"));

				movies.add(movie);
			}
			return Optional.of(movies);

		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}

		return Optional.empty();

	}

	@Override
	public String deleteMovieByMovieId(String movieId) throws Exception {
		// TODO Auto-generated method stub

		String deleteStatement = "delete from movie_table where movieid=?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = dataSource.getConnection();

		preparedStatement = connection.prepareStatement(deleteStatement);

		preparedStatement.setString(1, movieId);

		int result = preparedStatement.executeUpdate();

		if (result > 0) {
			return "success";
		} else {
			throw new NoDataFoundException("movieid not present");
		}

	}

}
