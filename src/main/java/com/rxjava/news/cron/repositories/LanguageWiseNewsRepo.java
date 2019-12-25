package com.rxjava.news.cron.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rxjava.news.entity.LanguageWiseNews;

public interface LanguageWiseNewsRepo extends JpaRepository<LanguageWiseNews, Long>{

	List<LanguageWiseNews> findByCreatedDateBetween(Long start, Long end);
}