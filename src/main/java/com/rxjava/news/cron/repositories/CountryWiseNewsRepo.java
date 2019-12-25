package com.rxjava.news.cron.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rxjava.news.entity.CountryWiseNews;

public interface CountryWiseNewsRepo extends JpaRepository<CountryWiseNews, Long>{
	
	List<CountryWiseNews> findByCreatedDateBetween(Long start, Long end);
}