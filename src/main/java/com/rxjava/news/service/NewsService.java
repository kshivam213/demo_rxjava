package com.rxjava.news.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rxjava.news.cron.daoImpl.NewsReposImpl;
import com.rxjava.news.response.models.AppResponse;

@Service
public class NewsService {

	@Autowired
	private NewsReposImpl newsRepoImpl;

	public AppResponse doFetchAllLatestNews() {

		Map<String, Object> response = new HashMap<>();
		LocalDateTime now = LocalDateTime.now();
		long currentDate = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

		response.put("countryWise", newsRepoImpl.findLatestCountryWiseNews(currentDate - 86400000, currentDate));
		response.put("categoryWise", newsRepoImpl.findLatestCategoryWiseNews(currentDate - 86400000, currentDate));
		response.put("languageWise", newsRepoImpl.findLatestLanguageWiseNews(currentDate - 86400000, currentDate));

		return AppResponse.builder().success(true).description("latest news").result(Arrays.asList(response)).build();
	}

	public AppResponse doFetchLatestCountryWise(final String country) {

		LocalDateTime now = LocalDateTime.now();
		long currentDate = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

		Map<String, Object> response = new HashMap<>();
		response.put("countryWise", newsRepoImpl.findLatestCountryWiseNews(currentDate - 86400000, currentDate));

		return AppResponse.builder().success(true).description("latest news").result(Arrays.asList(response)).build();
	}

	public AppResponse doFetchLatestCategoryWise(final String category) {

		LocalDateTime now = LocalDateTime.now();
		long currentDate = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

		Map<String, Object> response = new HashMap<>();
		response.put("categoryWise", newsRepoImpl.findLatestCategoryWiseNews(currentDate - 86400000, currentDate));

		return AppResponse.builder().success(true).description("latest news").result(Arrays.asList(response)).build();
	}
	
	public AppResponse doFetchLatestLanguageWise(final String language) {

		LocalDateTime now = LocalDateTime.now();
		long currentDate = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

		Map<String, Object> response = new HashMap<>();
		response.put("languageWise", newsRepoImpl.findLatestLanguageWiseNews(currentDate - 86400000, currentDate));

		return AppResponse.builder().success(true).description("latest news").result(Arrays.asList(response)).build();
	}
	
}