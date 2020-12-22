package com.example.Restful.API;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;


import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import model.getFilmsDao;
import model.loginDao;
import model.registerUserDao;
import model.userInfoDao;
import objects.film;
import objects.loginUser;
import objects.msgRes;
import objects.user;



@RestController
public class requestHandler {

	
	 @SuppressWarnings("deprecation")
	 @Bean
	    public WebMvcConfigurerAdapter corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**").allowedOrigins("*");
	            }
	        };
	    }

	
	
	// USER handler
	//(login ,register)
	@PostMapping(value = "/loginUser")
	public msgRes login(@RequestBody loginUser u) {
    	
	System.out.println("Entering login API ");
	
	loginDao Dao = new loginDao();
	
	System.out.println("Check username and password  - "+u.getUsername()+ " // "+u.getPassword());
	boolean result = Dao.check_client(u.getUsername(), u.getPassword());
	
	
	if(result) {
		System.out.println("Username and Password Match :) ");
		return new msgRes(" OK "," success ");
	}else {
		System.out.println("Username and Password not Matched !!! ");
		return new msgRes(" FAIL "," username and password is wrong ");
		
	}
	

	
		 
	}
	@PostMapping(value = "/registerUser")
	public msgRes register(@RequestBody user u) {
    	
		System.out.println("Entering register user API ");
		
		registerUserDao Dao = new registerUserDao();
		
		System.out.println("Check username and email  - "+u.getUsername()+ " // "+u.getEmail());
		
		if(Dao.isUsernameExist(u)) {
			System.out.println("Username is already exsist :) ");
			return new msgRes(" FAIL "," Username is already exsist !! ");
		}else if(Dao.isEmailExist(u)) { 
			System.out.println("Email address is already exsist :) ");
			return new msgRes(" FAIL "," Email address is already exsist !! ");
		}else {
			System.out.println("username and email address are uniqe  -- OK  ");
			return new msgRes("OK"," DONE ");	
		}
			 
		}

	// FILM handler	
	// (getAllFilms,getFilm,recommondedFilms)
	
	@GetMapping(value = "/film/{id}")
	public film getFilm(@PathVariable("id")  int id) {
    	
    	
		System.out.println("Entering get single film in API ");
		getFilmsDao Dao = new getFilmsDao();

		ResultSet rs = Dao.getFilm(""+id);
		
		film f = null;
	
	try {
		System.out.println("Start filling the film object ");
		rs.next();
			
		f = new film(rs.getString("title"),rs.getInt("FID"),
				rs.getString("dirName"),rs.getString("genre"),
				rs.getString("year"),
				null,null,null,rs.getDouble("rates"));
		 
		System.out.println("Finished !!");	
		System.out.println("Response with film in JSON file !!");
		
		return f;
	} catch (SQLException e) {	 
		e.printStackTrace();
	}
		System.out.println("Return film Object");
		return f;	 
	}
	
	
	
	@GetMapping(value = "/films")
	public ArrayList<film> getFilms() {
    	
    	
		System.out.println("Entering get all films API ");
		getFilmsDao Dao = new getFilmsDao();

		ResultSet rs = Dao.getRecommondedFilms_guest();
		ArrayList<film> films= new ArrayList<film>();
	
	try {
		System.out.println("Start filling the ArrayList ");
		
		while(rs.next()) {
			
		film f = new film(rs.getString("title"),rs.getInt("FID"),
				rs.getString("dirName"),rs.getString("genre"),
				rs.getString("year"),
				null,null,null,rs.getDouble("rates"));
		 
		films.add(f);
		}
		System.out.println("Finished !!");
		
		System.out.println("Response with film JSON file !!");
		
		return films;
	} catch (SQLException e) {	 
		e.printStackTrace();
	}
	
	
		System.out.println("Return film Object");
		return films;
		 
	}
	
	
	@GetMapping(value = "/recommondedfilms/{username}") // WORKING !!!
	public ArrayList<film> getRecommondedFilms(@PathVariable("username") String username) {
    	
    	
		System.out.println("Entering get recommonded films API ");
		getFilmsDao Dao = new getFilmsDao();

		ResultSet rs = Dao.getRecommondedFilms_user(username);
		ArrayList<film> films= new ArrayList<film>();
	
	try {
		System.out.println("Start filling the ArrayList ");
		
		while(rs.next()) {
			
		film f = new film(rs.getString("title"),rs.getInt("FID"),
				rs.getString("dirName"),rs.getString("genre"),
				rs.getString("year"),
				null,null,null,rs.getDouble("rates"));
		 
		films.add(f);
		}
		System.out.println("Finished !!");
		
		System.out.println("Response with film JSON file !!");
		
		return films;
	} catch (SQLException e) {	 
		e.printStackTrace();
	}
	
	
		System.out.println("Return film Object");
		return films;
		 
	}
	
}
