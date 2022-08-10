package com.zee.zee5app.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.repo.MovieRepository;
import com.zee.zee5app.repo.MovieRepositoryImpl;
import com.zee.zee5app.repo.SeriesRepository;
import com.zee.zee5app.repo.SeriesRepositoryImpl;

public class SeriesServiceImpl implements SeriesService {

	private SeriesServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private static SeriesService seriesService;
	
	public static SeriesService getInstance() {
		
		
		if(seriesService == null) {
			seriesService = new SeriesServiceImpl();
			
		}
		
		return seriesService;
	}
	
	private SeriesRepository seriesRepository = SeriesRepositoryImpl.getInstance();
	
	@Override
	public Series insertSeries(Series series) throws FileNotFoundException, UnableToGenerateIdException {
		// TODO Auto-generated method stub
		
		File file = new File(series.getTrailer());
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		System.out.println(file.getName());
		
		try {
			if(series.getTrailer() == null || 
					series.getTrailer() == "" || 
					!file.exists()) 
			{
				throw new FileNotFoundException("file does not exist");
			}
			else {
				bufferedInputStream = new BufferedInputStream(
						new FileInputStream(file));
				bufferedOutputStream = new BufferedOutputStream(
						new FileOutputStream("D:\\zee5app\\trailer\\" + file.getName()));
				
				
				bufferedOutputStream.write(bufferedInputStream.readAllBytes());
				
				System.out.println("file exists");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				bufferedInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//shift that file to zee5app/trailer folder
		//provide the location to trailer field
		
		// then take the path and store it in db --> handled by REPO
		
		return seriesRepository.insertSeries(series);

		
	}

	@Override
	public Series updateSeries(String seriesId, Series series) throws InvalidIdException {
		// TODO Auto-generated method stub
		return seriesRepository.updateSeries(seriesId, series);
	}

	@Override
	public Optional<Series> getSeriesBySeriesId(String seriesId) {
		// TODO Auto-generated method stub
		return seriesRepository.getSeriesBySeriesId(seriesId);
	}

	@Override
	public Optional<List<Series>> getAllSeries() {
		// TODO Auto-generated method stub
		return seriesRepository.getAllSeries();
	}

	@Override
	public Optional<List<Series>> getAllSeriesByGenre(Genres genre) {
		// TODO Auto-generated method stub
		return seriesRepository.getAllSeriesByGenre(genre);
	}

	@Override
	public Optional<List<Series>> getAllSeriesByName(String seriesName) {
		// TODO Auto-generated method stub
		return seriesRepository.getAllSeriesByName(seriesName);
	}

	@Override
	public Optional<List<Series>> findByOrderBySeriesNameDsc() {
		// TODO Auto-generated method stub
		return seriesRepository.findByOrderBySeriesNameDsc();
	}

	@Override
	public String deleteSeriesBySeriesId(String seriesId) throws Exception {
		// TODO Auto-generated method stub
		return seriesRepository.deleteSeriesBySeriesId(seriesId);
	}

}
