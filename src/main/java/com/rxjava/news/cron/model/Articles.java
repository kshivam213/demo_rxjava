package com.rxjava.news.cron.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Articles implements Serializable{
	
	/**
	 *  Default Id 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("author")
	private String author;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("urlToImage")
	private String urlToImage;
	
	@JsonProperty("publishedAt")
	private String publishedAt;  
	
	@JsonProperty("content")
	private String content;
	
}
