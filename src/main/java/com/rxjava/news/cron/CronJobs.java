package com.rxjava.news.cron;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rxjava.constants.CommonConstants;
import com.rxjava.news.cron.daoImpl.NewsReposImpl;
import com.rxjava.news.cron.model.Articles;
import com.rxjava.news.cron.model.NewsResponseModel;
import com.rxjava.news.cron.service.CronService;
import com.rxjava.news.entity.CategoryWiseNews;
import com.rxjava.news.entity.CountryWiseNews;
import com.rxjava.news.entity.LanguageWiseNews;
import com.rxjava.news.entity.News;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CronJobs {

	@Autowired
	private CronService cronService;

	@Autowired
	private NewsReposImpl newsDaoImpl;

	/**
	 *  Cron for every morning 12 noon to get latest news
	 */
	@Scheduled(cron = "0 0 12 * * ?")
	public void scheduleTaskWithCronExpression() {

		log.info("Cron Task :: Ticked on - {}", LocalDateTime.now());

		List<String> countryList = Arrays.asList("ae", "ar", "at", "au", "be", "bg", "br", "ca", "ch", "cn", "co", "cu",
				"cz", "de", "eg", "fr", "gb", "gr", "hk", "hu", "id", "ie", "il", "in", "it", "jp", "kr", "lt", "lv",
				"ma", "mx", "my", "ng", "nl", "no", "nz", "ph", "pl", "pt", "ro", "rs", "ru", "sa", "se", "sg", "si",
				"sk", "th", "tr", "tw", "ua", "us", "ve", "za");
		Observable<String> countryObservables = Observable.fromIterable(countryList);
		countryObservables.flatMap(country -> Observable.create(emmiter -> {
			try {
				NewsResponseModel response = cronService.doGetHeadlines(country, CommonConstants.COUNTRY);
				response.setCountry(country);

				emmiter.onNext(response);
				emmiter.onComplete();
			} catch (Exception ex) {
				emmiter.onError(ex);
			}
		})).subscribe(resp -> {
			NewsResponseModel model = (NewsResponseModel) resp;
			String country = model.getCountry();
			
			LocalDateTime now = LocalDateTime.now();

			long createdDate = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
			
			for (Articles article : model.getSources()) {

				News news = new News();

				doCreateNews(news, article);

				CountryWiseNews countyWiseNews = new CountryWiseNews();
				countyWiseNews.setCountry(country);
				countyWiseNews.setNews(news);
				countyWiseNews.setCreatedDate(createdDate);

				newsDaoImpl.saveCountryWiseNews(countyWiseNews);
			}
		});

		List<String> categoryList = Arrays.asList("entertainment", "general", "health", "science", "sports",
				"technology");
		Observable<String> categoryObservables = Observable.fromIterable(categoryList);

		categoryObservables.flatMap(category -> Observable.create(emmiter -> {
			try {
				NewsResponseModel response = cronService.doGetHeadlines(category, CommonConstants.COUNTRY);
				response.setCategory(category);

				emmiter.onNext(response);
				emmiter.onComplete();
			} catch (Exception ex) {
				emmiter.onError(ex);
			}
		})).subscribe(resp -> {
			NewsResponseModel model = (NewsResponseModel) resp;
			String category = model.getCategory();
			
			LocalDateTime now = LocalDateTime.now();

			long createdDate = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
			
			for (Articles article : model.getSources()) {

				News news = new News();

				doCreateNews(news, article);

				CategoryWiseNews categoryWiseNews = new CategoryWiseNews();
				categoryWiseNews.setCategory(category);
				categoryWiseNews.setNews(news);
				categoryWiseNews.setCreatedDate(createdDate);

				newsDaoImpl.saveCategoryWiseNews(categoryWiseNews);
			}
		});

		List<String> languageList = Arrays.asList("fr", "he", "it", "nl", "no", "pt", "ru", "se", "ud", "zh");
		Observable<String> languageObservables = Observable.fromIterable(languageList);

		languageObservables.flatMap(language -> Observable.create(emmiter -> {
			try {
				NewsResponseModel response = cronService.doGetHeadlines(language, CommonConstants.COUNTRY);
				response.setLanguage(language);

				emmiter.onNext(response);
				emmiter.onComplete();
			} catch (Exception ex) {
				emmiter.onError(ex);
			}
		})).subscribe(resp -> {
			NewsResponseModel model = (NewsResponseModel) resp;
			String language = model.getLanguage();
			
			LocalDateTime now = LocalDateTime.now();

			long createdDate = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

			for (Articles article : model.getSources()) {

				News news = new News();

				doCreateNews(news, article);

				LanguageWiseNews languageWiseNews = new LanguageWiseNews();
				languageWiseNews.setLanguage(language);
				languageWiseNews.setNews(news);
				languageWiseNews.setCreatedDate(createdDate);

				newsDaoImpl.saveLanguageWiseNews(languageWiseNews);
			}
		});
		
	}

	public void doCreateNews(News news, Articles article) {

		news.setAuthor(article.getAuthor());
		news.setContent(article.getContent());
		news.setDescription(article.getDescription());
		news.setTitle(article.getTitle());
		news.setUrl(article.getUrl());
		news.setUrlToImage(article.getUrlToImage());
	}
}
