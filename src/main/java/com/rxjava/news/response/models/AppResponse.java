package com.rxjava.news.response.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppResponse {
	
	private AppResponse() {}
	
	private AppResponse(boolean success, String description, List<Object> result, List<AppErrorResponse> errors) {
		super();
		this.success = success;
		this.description = description;
		this.result = result;
		this.errors = errors;
	}

	private boolean success;
	
	private String description;

	private List<Object> result;

	private List<AppErrorResponse> errors;
	
}
