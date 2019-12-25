package com.rxjava.news.cron.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rxjava.constants.CommonConstants;
import com.rxjava.news.cron.model.NewsResponseModel;
import com.rxjava.utils.WebServiceUtils;

@Service
public class CronService {

	@Value("${api.news.token}")
	private String apiToken;
	
	@Value("${api.news.headline.url}")
	private String url;
	
	public NewsResponseModel doGetHeadlines(String entity, String basedOn) {
		
		String constUrl = "";
		if(basedOn.equalsIgnoreCase(CommonConstants.COUNTRY))
			constUrl = url+"/?country="+entity+"&apiKey="+apiToken; 
		else if(basedOn.equalsIgnoreCase(CommonConstants.CATEGORY))
			constUrl = url+"/?category="+entity+"&apiKey="+apiToken;
		else if(basedOn.equalsIgnoreCase(CommonConstants.LANGUAGE))
			constUrl = url+"/?language="+entity+"&apiKey="+apiToken;
		
		if(!constUrl.isEmpty()) {
			WebServiceUtils webUtils = new WebServiceUtils(constUrl);
			
			return webUtils.get(NewsResponseModel.class);
		}
		
		return null;
	}
}
