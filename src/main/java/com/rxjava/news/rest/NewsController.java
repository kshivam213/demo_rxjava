package com.rxjava.news.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rxjava.news.response.models.AppResponse;
import com.rxjava.news.service.NewsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="/api/v1/news")
@Slf4j
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	/**
	 * Fetch All Latest news
	 * @return List of all latest news
	 */
	@GetMapping(path = "/", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<AppResponse> getAllNewsToday(){
		log.info("Reading All News today ..");
		
		AppResponse res = newsService.doFetchAllLatestNews();
		
		return ResponseEntity.ok(res);
	}
	
	@GetMapping(path = "/country/{country}", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<AppResponse> getCountryWiseNewsToday(@PathVariable("country") String country){
		log.info("Reading All Country wise news today ..");
		
		AppResponse res = newsService.doFetchLatestCountryWise(country);
		return ResponseEntity.ok(res);
	}
	
	@GetMapping(path = "/category/{category}", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<AppResponse> getCategoryWiseNewsToday(@PathVariable("category") String category){
		log.info("Reading All Category wise news today ..");
		
		AppResponse res = newsService.doFetchLatestCategoryWise(category);
		return ResponseEntity.ok(res);
	}
	
	@GetMapping(path = "/language/{language}", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<AppResponse> getLangugaeWiseNewsToday(@PathVariable("language") String language){
		log.info("Reading All Language wise news today ..");
		
		AppResponse res = newsService.doFetchLatestLanguageWise(language);
		return ResponseEntity.ok(res);
	}	
}