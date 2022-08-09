package com.zee.zee5app;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.InvalidNameException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.repo.MovieRepository;
import com.zee.zee5app.repo.MovieRepositoryImpl;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.MovieServiceImpl;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.UserServiceImpl;
import com.zee.zee5app.utils.IdComparator;

public class Main {
	
	public static void main(String[] args) {
		
		//UserService userService = UserServiceImpl.getInstance();
		
		MovieService movieService = MovieServiceImpl.getInstance();
		
		
		
		/*try {
			userService.insertUser(new User("abhi","chivate","abhi10@gmail.com",LocalDate.now(),LocalDate.of(1988, 12, 9)));
			
			userService.insertUser(new User("abhii","chivatee","abhii20@gmail.com",LocalDate.now(),LocalDate.of(1988, 12, 10)));
		} catch (UnableToGenerateIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String[] actors = {"ds", "mt"};
		String[] languages = {Languages.TELUGU.toString()};
		
		
		try {
			movieService.insertMovie(new Movie(actors, "SitaRamam", "Hanu", Genres.ROMANCE, "swapna",languages , 242.2f, "C:\\Users\\rathod.naik\\Videos\\videoplayback.mp4"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnableToGenerateIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		/*Optional<User> result = userService.getUserByUserId("ab1");
		
		
		if(!result.isPresent()) {
			System.out.println("no record found");
		}
		else {
			User user = result.get();
			System.out.println("record inserted");
			System.out.println(user);
		}*/
		
		/*Optional<User[]> allUsers = userService.getAllUsers();
		
		if(!allUsers.isPresent()) {
			System.out.println("no users found");
		}
		else {
			for (User user : allUsers.get()) {
				System.out.println(user);
			}
			
			
		}
		
		try {
			
			String isDeleted = userService.deleteUser("ab002");
			
		} catch (NoDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("checking if deleted");
		
		Optional<User[]> allUserss = userService.getAllUsers();
		
		if(!allUserss.isPresent()) {
			System.out.println("no users found");
		}
		else {
			for (User user : allUserss.get()) {
				System.out.println(user);
			}
			
			
		}
		
		System.out.println("sorted array");
		List<User> u = Arrays.asList(allUsers.get());
		
		
		// sorting method 1
		//Collections.sort(u);
		
		
		Collections.sort(u, new IdComparator()); //sorting method 2
		u.forEach(e -> System.out.println(e));*/
		
		
		
		
/*		int contentType = 1;
//		1 : User
//		2 : Movie
		
		switch (contentType) {
			
			case 1:
				
				int operation = 1;
//				1 : create
//				2: retrieveall
//				3: retrievebyid
//				4: delete
//				5: update
				
				switch (operation) {
				
				//inserting user
					case 1:
						
						User result = userService.insertUser(user);
						if(result != null ) {
							System.out.println("user record inserted");
							//User[] allusers =  userService.getallusers();
							//System.out.println(allusers[0]);
						}
						else {
							System.out.println("user record insertion failed");
						}
						
						break;
					
					//retrieving all users
					case 2:
						
						User[] allusers =  userService.getAllUsers();
						if(allusers == null) {
							System.out.println("No record found");
						}
						else {
							for (User tempuser : allusers) {
								if(tempuser != null) {
									System.out.println(tempuser);
								}
							}
						}
						
						break;
					
					//retrieving user by Id
					case 3:
						
						User userDetail = userService.getUserByUserId(userId);
						if(userDetail == null) {
							System.out.println("No user record");
						}
						else {
							System.out.println(userDetail);
						}
						
						break;
						
					//Delete user
					case 4:
						
						String isDeleted = userService.deleteUser(userId);
						if("Success".equals(isDeleted)) {
							System.out.println("record successfully deleted");
						}
						else {
							System.out.println("record not found");
						}
						
						break;
						
					
					//Update user
					case 5:
						
						User newUser = new User();
						User isUpdated = userService.updateUser(userId, newUser);
						if(user != newUser) {
							System.out.println("record successfully updated");
						}
						else {
							System.out.println("record not found");
						}
						
						break;
					
					default:
						System.out.println("Enter correct operation");
						break;

				
				}
				
				break;
				
			case 2:
				
				int op = 1;
				
				switch (op) {
				
				//inserting movie
					case 1:
						
						Movie result = movieService.insertMovie(movie);
						if(result != null ) {
							System.out.println("movie record inserted");
						}
						else {
							System.out.println("movie record insertion failed");
						}
						
						break;
					
					//retrieving all users
					case 2:
						
						Movie[] allmovies =  movieService.getAllMovies();
						if(allmovies == null) {
							System.out.println("No record found");
						}
						else {
							for (Movie tempmovie : allmovies) {
								if(tempmovie != null) {
									System.out.println(tempmovie);
								}
							}
						}
						
						break;
					
					//retrieving movie by Id
					case 3:
						
						Movie movieDetail = movieService.getMovieByMovieId(movieId);
						if(movieDetail == null) {
							System.out.println("No user record");
						}
						else {
							System.out.println(movieDetail);
						}
						
						break;
						
					//Delete movie
					case 4:
						
						String isDeleted = movieService.deleteMovieByMovieId(movieId);
						if("Success".equals(isDeleted)) {
							System.out.println("record successfully deleted");
						}
						else {
							System.out.println("record not found");
						}
						
						break;
						
					
					//Update movie
					case 5:
						
						Movie newMovie = new Movie();
						Movie isUpdated = movieService.updateMovie(movieId, newMovie);
						if(movie != newMovie) {
							System.out.println("record successfully updated");
						}
						else {
							System.out.println("record not found");
						}
						
						break;
					
					default:
						System.out.println("Enter correct operation");
						break;

				
				}
				
				break;
				
			default:
				
				System.out.println("Enter correct contentType");
				break;
				
		}
*/
		//System.out.println(user);
		//System.out.println(user.toString());
		// System : class
		//out : static ref
		//println : its method from Printstream class
	}
}
