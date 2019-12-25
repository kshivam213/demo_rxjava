package com.rxjava.news.cron.enums;

public enum CategoryEnum {

	entertainment, general, health, science, sports, technology;
	
	static public boolean isMember(final String category) {
		boolean result = false;
	       for (CategoryEnum handler : CategoryEnum.values()){
	      	 if (handler.name().equals(category))
             result = true;
	       }
	       return result;
	}
}
