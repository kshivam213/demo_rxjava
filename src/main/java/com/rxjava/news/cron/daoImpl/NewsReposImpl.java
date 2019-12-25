package com.rxjava.news.cron.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rxjava.news.cron.repositories.CategoryWiseNewsRepo;
import com.rxjava.news.cron.repositories.CountryWiseNewsRepo;
import com.rxjava.news.cron.repositories.LanguageWiseNewsRepo;
import com.rxjava.news.cron.repositories.NewsRepo;
import com.rxjava.news.entity.CategoryWiseNews;
import com.rxjava.news.entity.CountryWiseNews;
import com.rxjava.news.entity.LanguageWiseNews;
import com.rxjava.news.entity.News;

@Service
public class NewsReposImpl {

	@Autowired
	private NewsRepo newsRepo;

	@Autowired
	private CountryWiseNewsRepo countryRepo;

	@Autowired
	private CategoryWiseNewsRepo categoryRepo;

	@Autowired
	private LanguageWiseNewsRepo languageRepo;

	public News saveNews(News news) {
		return newsRepo.save(news);
	}

	public News findNewsById(Long id) {
		return newsRepo.findOne(id);
	}

	public CountryWiseNews saveCountryWiseNews(CountryWiseNews news) {
		return countryRepo.save(news);
	}

	public CountryWiseNews findCountryWiseNewsById(Long id) {
		return countryRepo.findOne(id);
	}

	public CategoryWiseNews saveCategoryWiseNews(CategoryWiseNews news) {
		return categoryRepo.save(news);
	}

	public CategoryWiseNews findCategoryWiseNewsById(Long id) {
		return categoryRepo.findOne(id);
	}

	public LanguageWiseNews saveLanguageWiseNews(LanguageWiseNews news) {
		return languageRepo.save(news);
	}

	public LanguageWiseNews findLanguageWiseNewsById(Long id) {
		return languageRepo.findOne(id);
	}

	// Find Latest News
	
	public List<CountryWiseNews> findLatestCountryWiseNews(Long start, Long end) {

		return countryRepo.findByCreatedDateBetween(start, end);
	}

	public List<CategoryWiseNews> findLatestCategoryWiseNews(Long start, Long end) {

		return categoryRepo.findByCreatedDateBetween(start, end);
	}

	public List<LanguageWiseNews> findLatestLanguageWiseNews(Long start, Long end) {

		return languageRepo.findByCreatedDateBetween(start, end);
	}
}
