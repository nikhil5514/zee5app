package com.zee.zee5app.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.InvalidNameException;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString

public class Series {

	private String actors[];
	private String seriesName;
	private String director;
	private Genres genre;
	private String production;
	private String languages[];
	private float seriesLength;
	private String seriesId;
	private String trailer;

	public Series() {
		// TODO Auto-generated constructor stub
	}

	public Series(String[] actors, String seriesName, String director, Genres genre, String production,
			String[] languages, float seriesLength, String trailer) throws InvalidIdException, InvalidNameException {
		
		super();
		this.actors = actors;
		this.setSeriesName(seriesName);
		this.director = director;
		this.setGenre(genre);
		this.production = production;
		this.setLanguages(languages);
		this.seriesLength = seriesLength;
		this.trailer = trailer;
	}

	public void setSeriesId(String seriesId) throws InvalidIdException {
		// movieId shud be min 5 chars and max 7
		int length = seriesId.length();

		if (length >= 5 && length <= 7) {
			this.seriesId = seriesId;
		} else {
			// raise an exception
			// data is not validated
			throw new InvalidIdException("Invalid series id");

		}

	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	public void setSeriesName(String seriesName) throws InvalidNameException {

		if (seriesName == null || seriesName.length() < 3) {
			throw new InvalidNameException("Invalid series Name");

		} else {
			this.seriesName = seriesName;
		}
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public void setLanguages(String[] languages) throws InvalidNameException {
		int count = 0;
		for (String string : languages) {
			// System.out.println(Languages.valueOf(string));

			try {
				if (Languages.valueOf(string) == null) {
					throw new InvalidNameException("Invalid language set");
				}
			} catch (InvalidNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//                try {
//					Languages.valueOf(string);
//						
//					
//				} catch ( IllegalArgumentException e) {
//					// TODO Auto-generated catch block
//					throw new InvalidNameException("Invalid language set");
//				}
		}
		this.languages = languages;

	}

	public void setSeriesLength(float seriesLength) {
		this.seriesLength = seriesLength;
	}

	public void setGenre(Genres genre) {
		this.genre = genre;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(actors);
		result = prime * result + Arrays.hashCode(languages);
		result = prime * result
				+ Objects.hash(director, genre, production, seriesId, seriesLength, seriesName, trailer);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Series other = (Series) obj;
		return Arrays.equals(actors, other.actors) && Objects.equals(director, other.director) && genre == other.genre
				&& Arrays.equals(languages, other.languages) && Objects.equals(production, other.production)
				&& Objects.equals(seriesId, other.seriesId)
				&& Float.floatToIntBits(seriesLength) == Float.floatToIntBits(other.seriesLength)
				&& Objects.equals(seriesName, other.seriesName) && Objects.equals(trailer, other.trailer);
	}

}
