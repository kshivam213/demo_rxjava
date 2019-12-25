package com.rxjava.news.cron.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rxjava.news.entity.CategoryWiseNews;

public interface CategoryWiseNewsRepo extends JpaRepository<CategoryWiseNews, Long>{

	List<CategoryWiseNews> findByCreatedDateBetween(Long start, Long end);
}