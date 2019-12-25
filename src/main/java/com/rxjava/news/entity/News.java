package com.rxjava.news.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="news")
@EqualsAndHashCode(callSuper=false)
public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "news_id", nullable = false, unique = true)
	private long Id;

	@Column(name = "author")
	private String author;
	
	@Column(name = "title", columnDefinition = "LONGTEXT")
	private String title;
	
	@Column(name = "description", columnDefinition = "LONGTEXT")
	private String description;
	
	@Column(name = "url", columnDefinition = "LONGTEXT")
	private String url;

	@Column(name = "urlToImage", columnDefinition = "LONGTEXT")
	private String urlToImage;
	
	@Column(name = "content", columnDefinition = "LONGTEXT")
	private String content;
	
	@OneToMany(mappedBy="news", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private Set<CountryWiseNews> countryWiseNewsList = new HashSet<CountryWiseNews>();
	
	@OneToMany(mappedBy="news", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private Set<CategoryWiseNews> categoryWiseNewsList = new HashSet<CategoryWiseNews>();
	
	@OneToMany(mappedBy="news", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private Set<LanguageWiseNews> languageWiseNewsList = new HashSet<LanguageWiseNews>();
	
}
