package com.rxjava.news.response.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppErrorResponse {
	
	private int errorCode;
	private String message;
	
}
