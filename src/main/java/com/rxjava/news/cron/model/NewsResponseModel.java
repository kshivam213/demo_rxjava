package com.rxjava.news.cron.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NewsResponseModel implements Serializable{
	
	/**
	 *  Default serialization Id
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("status")
	private String status;
	
	@JsonProperty("totalResults")
	private int totalResults;
	
	private String country;
	private String language;
	private String category;
	
	@JsonProperty("articles")
	private List<Articles> sources;
}
