package com.zee.zee5app.service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;

public interface SeriesService {
	public Series insertSeries(Series series) throws UnableToGenerateIdException, FileNotFoundException;
    public Series updateSeries(String seriesId, Series series) throws InvalidIdException;

    public Optional<Series> getSeriesBySeriesId(String seriesId);
    public Optional<List<Series>> getAllSeries();
    public Optional<List<Series>> getAllSeriesByGenre(Genres genre);
    public Optional<List<Series>> getAllSeriesByName(String seriesName);
    
    public Optional<List<Series>> findByOrderBySeriesNameDsc();
    
    public String deleteSeriesBySeriesId(String seriesId) throws Exception;

}
