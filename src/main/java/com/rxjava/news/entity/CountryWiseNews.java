package com.rxjava.news.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="country_wise_news")
@EqualsAndHashCode(callSuper=false)
public class CountryWiseNews {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private long Id;

	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "createdDate", nullable = false) 
	private long createdDate;

	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name = "news_id")
	private News news;
}
