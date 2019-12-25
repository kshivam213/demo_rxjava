package com.rxjava.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * The Class WebServiceUtils.
 */
public class WebServiceUtils {
	
	/**The server url with endpoint*/
	private String url;
	
	/**The query params for url*/
	private Map<String,String> queryParams;
	
	/**The url/path params for url*/
	private Map<String,String> urlParams;
	
	/**The request headers*/
	private HttpHeaders httpHeaders;

	/**
	 * no args constructor
	 */
	public WebServiceUtils() {
		super();
	}
	
	/**
	 * Constructor with server url
	 * @param url The server url with endpoint
	 */
	public WebServiceUtils(final String url) {
		this.url = url;
	}
	
	/**
	 * 
	 * @param url the url with endpoint
	 * @param queryParams The query params
	 * @param urlParams the url params
	 * @param headers the request headers
	 */
	public WebServiceUtils(final String url, final Map<String, String> queryParams,
			final Map<String, String> urlParams, final Map<String, String> headers) {
		super();
		this.url = url;
		this.queryParams = queryParams;
		this.urlParams = urlParams;
		if(headers !=null) {
			this.httpHeaders = addHeaders(headers);
		}
		
	}

	/**
	 * HTTP POST method
	 * @param <T> The request object type
	 * @param <V> The response object type
	 * @param payload the request details
	 * @return the V type response
	 */
	public <T, V> V post(final T payload, final Class<V> responseType) {
		return new RestTemplate().postForObject(buildUrl(), new HttpEntity<T>(payload,httpHeaders), responseType);
	}
	
	/**
	 * 
	 * @param payload POST a string or text 
	 * @param responseType the response type from the server
	 * @return the response object
	 */
	public <V> V postStr(final String payload, final Class<V> responseType) {
		httpHeaders.add("Content-Type", "application/json");
		return new RestTemplate().postForObject(buildUrl(), new HttpEntity<String>(payload,httpHeaders), responseType);
	}
	
	/**
	 * 
	 * @param payload POST a string or text 
	 * @param responseType the response type from the server
	 * @return the response object
	 */
	public <V> V postEncodedObj(final String payload, final Class<V> responseType) {
		httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
		return new RestTemplate().postForObject(buildUrl(), new HttpEntity<String>(payload,httpHeaders), responseType);
	}
	
	
	/**
	 * HTTP POST method in async operation
	 * @param payload the post body
	 * @param responseType the type of response from the API
	 * @return the response data
	 */
	public <T, V> ListenableFuture<ResponseEntity<V>> postAsync(final T payload, final Class<V> responseType) {
		return  new AsyncRestTemplate().postForEntity(buildUrl(), new HttpEntity<T>(payload,httpHeaders), responseType);
	}
	
	/**
	 * HTTP POST method in async operation
	 * @param payload the post body
	 * @param responseType the type of response from the API
	 * @return the response data
	 */
	public <T, V> ListenableFuture<ResponseEntity<V>> postAsyncWithAuth(final T payload, final Class<V> responseType, final String token) {
		httpHeaders.add("Content-Type", "application/json");
		httpHeaders.add("Authorization", "Bearer "+token);
		return  new AsyncRestTemplate().postForEntity(buildUrl(), new HttpEntity<T>(payload,httpHeaders), responseType);
	}
	
	
	/**
	 * HTTP GET request with path and query params
	 * @param responseType The API response type
	 * @return The API response
	 */
	public  <V> V get(final Class<V> responseType) {
		return  new RestTemplate().exchange(buildUrl(),HttpMethod.GET,new HttpEntity<Object>(httpHeaders), 
				responseType,new HashMap<String, Object>()).getBody();
	}
	
	/**
	 * HTTP GET method in async operation
	 * @param responseType the response type from the api
	 * @return a Listenable that will have callbacks
	 */
	public <V> ListenableFuture<ResponseEntity<V>> getAsync(final Class<V> responseType) {
		AsyncRestTemplate async = new AsyncRestTemplate();
		return async.exchange(buildUrl(), HttpMethod.GET,new HttpEntity<Object>(httpHeaders), 
				responseType,new HashMap<String, Object>());
	}
	
	/**
	 * Build server url with params
	 * @return The final url
	 */
	private String buildUrl() {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
		if(queryParams ==null || queryParams.isEmpty()) {
			url = builder.toUriString();
		}
		else {
			for(Map.Entry<String, String> entry : queryParams.entrySet()) {
				builder.queryParam(entry.getKey(),entry.getValue());
			}
		}
		if(urlParams==null || urlParams.isEmpty()) {
			url = builder.toUriString();
		}
		else {
			url = builder.buildAndExpand(urlParams).toUriString();
		}
		return url;
	}
	
	private HttpHeaders addHeaders(final Map<String, String> headers) {
		HttpHeaders httpHeaders = new HttpHeaders();
		for(Map.Entry<String, String> header : headers.entrySet()) {
			httpHeaders.add(header.getKey(), header.getValue());
		}
		return httpHeaders;
	}
}