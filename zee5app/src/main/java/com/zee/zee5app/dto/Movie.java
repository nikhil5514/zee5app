package com.zee.zee5app.dto;


import java.util.Arrays;
import java.util.Objects;

import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.InvalidNameException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
//@EqualsAndHashCode

public class Movie {
    
	private String actors[];
    private String movieName;
    private String director;
    private Genres genre;
    private String production;
    private String languages[];
    private float movieLength;
	private String movieId;
	private String trailer;  //preferred way (It holds file location)
	//private byte[] trailer2;
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}
	
	public Movie(String[] actors, String movieName, String director, Genres genre, String production,
			String[] languages, float movieLength, String trailer) throws InvalidIdException, InvalidNameException {
		
		super();
		
		//this.setMovieId(movieId);
		this.actors = actors;
		this.setMovieName(movieName);
		this.director = director;
		this.setGenre(genre);
		this.production = production;
		this.setLanguages(languages);
		this.movieLength = movieLength;
		this.trailer = trailer;
	}

	
    public void setMovieId(String movieId) throws InvalidIdException {
    	//movieId shud be min 5 chars and max 7
    	int length = movieId.length();
    	
    	if(length >= 5 && length <=7) {
    		this.movieId = movieId;
    	}
    	else {
    		//raise an exception
    		//data is not validated
    		throw new InvalidIdException("Invalid movie id");
    		
    	}
		
	}
    
    
	public void setActors(String[] actors) {
		this.actors = actors;
	}
	
	
	
	public void setMovieName(String movieName) throws InvalidNameException {
		
		if(movieName == null || movieName.length() < 3) {
			throw new InvalidNameException("Invalid Movie Name");
			
		}
		else {
			this.movieName = movieName;
		}
	}
	
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	
	public void setProduction(String production) {
		this.production = production;
	}
	
	
	public void setLanguages(String[] languages) throws InvalidNameException {
		int count =0;
        for (String string : languages) {
            //System.out.println(Languages.valueOf(string));
            	
                try {
					if(Languages.valueOf(string)==null) {
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
	
	
	public void setMovieLength(float movieLength) {
		this.movieLength = movieLength;
	}

	public void setGenre(Genres genre) {
		this.genre = genre;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(actors);
		result = prime * result + Arrays.hashCode(languages);
		result = prime * result + Objects.hash(director, genre, movieId, movieLength, movieName, production);
		return result;
	}

	//int temp = Integer.parseInt("1");
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Arrays.equals(actors, other.actors) && Objects.equals(director, other.director) && genre == other.genre
				&& Arrays.equals(languages, other.languages) && Objects.equals(movieId, other.movieId)
				&& Float.floatToIntBits(movieLength) == Float.floatToIntBits(other.movieLength)
				&& Objects.equals(movieName, other.movieName) && Objects.equals(production, other.production);
	}
	
	
	
    
}

