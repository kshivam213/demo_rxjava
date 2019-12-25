package com.rxjava.news.cron.enums;

public enum CountryEnum {

	ae, ar, at, au, be, bg, br, ca, ch, cn, co, cu, cz, de, eg, fr, gb, gr, hk, hu, id, ie, il, in, it, jp, kr, lt, lv, ma, mx, my, ng, nl, no, nz, ph, pl, pt, ro, rs, ru, sa, se, sg, si, sk, th, tr, tw ,ua, us, ve, za;
	
	static public boolean isMember(final String country) {
		boolean result = false;
	       for (CountryEnum handler : CountryEnum.values()){
	      	 if (handler.name().equals(country))
             result = true;
	       }
	       return result;
	}
}
