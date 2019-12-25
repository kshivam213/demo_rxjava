package com.rxjava.news.cron.enums;

public enum LanguageEnum {
	
	fr, he, it, nl, no, pt, ru, se, ud, zh;
	
	static public boolean isMember(final String lang) {
		boolean result = false;
	       for (LanguageEnum handler : LanguageEnum.values()){
	      	 if (handler.name().equals(lang))
             result = true;
	       }
	       return result;
	}
}
