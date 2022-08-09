package com.zee.zee5app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.InvalidNameException;

public class ExceptionMain {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		String[] actors = {"a","b","c"};
		//Enum to String
		String[] languages = {Languages.KANNADA.name(), Languages.TAMIL.name(),Languages.TELUGU.name(),
                Languages.HINDI.name()};
		
		
			/*try {
				Movie movie = new Movie(actors, "Vikranth Rona", "dir", Genres.ACTION, "kfp", languages, 2.2f);
				//System.out.println(movie.hashCode());
			} catch (InvalidIdException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			
			
		
		}		
	}


