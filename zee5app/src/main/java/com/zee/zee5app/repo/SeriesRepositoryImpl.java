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

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.InvalidNameException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class SeriesRepositoryImpl implements SeriesRepository {

//	private static SeriesRepository seriesRepo;
//
//	private SeriesRepositoryImpl() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public static SeriesRepository getInstance() {
//		if (seriesRepo == null) {
//			seriesRepo = new SeriesRepositoryImpl();
//		}
//		return seriesRepo;
//	}

	@Autowired
	private DBUtils dbUtils;
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public Series insertSeries(Series series) throws UnableToGenerateIdException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		// In jdbc, qs mark count will start with 1
		String insertStatement = "insert into series_table"
				+ " (seriesid, actors, seriesname, director, genre, production, languages, serieslength, trailer)"
				+ " values(?,?,?,?,?,?,?,?,?)";
		
		//connection object
		//connection = dataSource.getConnection();
		
		//statement object(prepared)
		try {
			
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(insertStatement);
			
			preparedStatement.setString(1, dbUtils.seriesIdGenerator(series.getSeriesName()));
			
			String actors = String.join(",", series.getActors());
			preparedStatement.setString(2, actors);
			
			preparedStatement.setString(3, series.getSeriesName());
			preparedStatement.setString(4, series.getDirector());
			preparedStatement.setString(5, series.getGenre().toString());
			preparedStatement.setString(6, series.getProduction());
			
			String languages = String.join(",", series.getLanguages());
			preparedStatement.setString(7, languages);
			
			preparedStatement.setFloat(8, series.getSeriesLength());
			preparedStatement.setString(9, series.getTrailer());
			
			int result = preparedStatement.executeUpdate();
			if(result > 0) {
				return series;
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
	public Series updateSeries(String seriesId, Series series) throws InvalidIdException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateQuery = "upadte series_table "
				+ "set actors=?, seriesname=?, director=?, genre=?, production=?, languages=?, serieslength=?, trailer=? "
				+ "where seriesid=?";

		//connection = dataSource.getConnection();
		try {
			
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, seriesId);
			preparedStatement.setString(2, String.join(",", series.getActors()));
			preparedStatement.setString(3, series.getSeriesName());
			preparedStatement.setString(4, series.getDirector());
			preparedStatement.setString(5, series.getGenre().toString());
			preparedStatement.setString(6, series.getProduction());
			preparedStatement.setString(7, String.join(",", String.join(",", series.getLanguages())));
			preparedStatement.setFloat(8, series.getSeriesLength());
			preparedStatement.setString(9, series.getTrailer());
			
			int result = preparedStatement.executeUpdate();
			
			if (result > 0) {
				return series;
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
	public Optional<Series> getSeriesBySeriesId(String seriesId) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "select * from series_table where seriesId=?";
		
		//connection = dataSource.getConnection();
		
		try {
			
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, seriesId);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				Series movie = new Series();
				
				movie.setSeriesId(seriesId);
				movie.setActors(resultSet.getString("actors").split(","));
				movie.setSeriesName(resultSet.getString("seriesname"));
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(Genres.valueOf(resultSet.getString("genre")));
				movie.setProduction(resultSet.getString("production"));
				movie.setLanguages(resultSet.getString("languages").split(","));
				movie.setSeriesLength(resultSet.getFloat("serieslength"));
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
	public Optional<List<Series>> getAllSeries() {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Series> serieses = new ArrayList<Series>();
		
		String query = "select * from series_table";
		
		//connection = dataSource.getConnection();
		
		try {
			
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(query);
			//preparedStatement.setString(1, seriesId);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Series series = new Series();
				
				series.setSeriesId(resultSet.getString("seriesid"));
				series.setActors(resultSet.getString("actors").split(","));
				series.setSeriesName(resultSet.getString("seriesname"));
				series.setDirector(resultSet.getString("director"));
				series.setGenre(Genres.valueOf(resultSet.getString("genre")));
				series.setProduction(resultSet.getString("production"));
				series.setLanguages(resultSet.getString("languages").split(","));
				series.setSeriesLength(resultSet.getFloat("serieslength"));
				series.setTrailer(resultSet.getString("trailer"));
				
				serieses.add(series);
			}
			return Optional.of(serieses);
			
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
	public Optional<List<Series>> getAllSeriesByGenre(Genres genre) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		List<Series> serieses = new ArrayList<Series>();
		
		String query = "select * from series_table where genre = ?";
		
		//connection = dataSource.getConnection();
		
		try {
			
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(5, genre.toString());
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Series series = new Series();
				
				series.setSeriesId(resultSet.getString("seriesid"));
				series.setActors(resultSet.getString("actors").split(","));
				series.setSeriesName(resultSet.getString("seriesname"));
				series.setDirector(resultSet.getString("director"));
				series.setGenre(genre);
				series.setProduction(resultSet.getString("production"));
				series.setLanguages(resultSet.getString("languages").split(","));
				series.setSeriesLength(resultSet.getFloat("serieslength"));
				series.setTrailer(resultSet.getString("trailer"));
				
				serieses.add(series);
			}
			return Optional.of(serieses);
			
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
	public Optional<List<Series>> getAllSeriesByName(String seriesName) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		List<Series> serieses = new ArrayList<Series>();
		
		String query = "select * from series_table where seriesname = ?";
		
		//connection = dataSource.getConnection();
		
		try {
			
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(3, seriesName);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Series series = new Series();
				
				series.setSeriesId(resultSet.getString("seriesid"));
				series.setActors(resultSet.getString("actors").split(","));
				series.setSeriesName(seriesName);
				series.setDirector(resultSet.getString("director"));
				series.setGenre(Genres.valueOf(resultSet.getString("genre")));
				series.setProduction(resultSet.getString("production"));
				series.setLanguages(resultSet.getString("languages").split(","));
				series.setSeriesLength(resultSet.getFloat("serieslength"));
				series.setTrailer(resultSet.getString("trailer"));
				
				serieses.add(series);
			}
			return Optional.of(serieses);
			
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
	public Optional<List<Series>> findByOrderBySeriesNameDsc() {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Series> serieses = new ArrayList<Series>();

		String query = "select * from series_table order by seriesname desc";

		//connection = dataSource.getConnection();

		try {
			
			connection = dataSource.getConnection();
			
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, movieId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Series series = new Series();

				series.setSeriesId(resultSet.getString("seriesid"));
				series.setActors(resultSet.getString("actors").split(","));
				series.setSeriesName(resultSet.getString("seriesname"));
				series.setDirector(resultSet.getString("director"));
				series.setGenre(Genres.valueOf(resultSet.getString("genre")));
				series.setProduction(resultSet.getString("production"));
				series.setLanguages(resultSet.getString("languages").split(","));
				series.setSeriesLength(resultSet.getFloat("serieslength"));
				series.setTrailer(resultSet.getString("trailer"));

				serieses.add(series);
			}
			return Optional.of(serieses);

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
	public String deleteSeriesBySeriesId(String seriesId) throws Exception {
		// TODO Auto-generated method stub
		
		String deleteStatement = "delete from series_table where seriesid=?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = dataSource.getConnection();

		preparedStatement = connection.prepareStatement(deleteStatement);

		preparedStatement.setString(1, seriesId);

		int result = preparedStatement.executeUpdate();

		if (result > 0) {
			return "success";
		} else {
			throw new NoDataFoundException("seriesid not present");
		}

	}

}
