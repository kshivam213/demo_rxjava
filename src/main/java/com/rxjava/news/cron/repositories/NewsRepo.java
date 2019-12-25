package com.rxjava.news.cron.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rxjava.news.entity.News;

public interface NewsRepo extends JpaRepository<News, Long>{

}