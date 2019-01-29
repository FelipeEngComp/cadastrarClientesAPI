package com.felipe.uolChallenge.client;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.felipe.uolChallenge.domain.CityInformation;

public class LocationByCoordinates {
	private RestTemplate restTemplate;
	private String URI_BASE;
	private String URN_BASE;
	
	public LocationByCoordinates(String url, String urn) {
		restTemplate = new RestTemplate();
		this.URN_BASE = urn;
		URI_BASE = url.concat(URN_BASE);
	}
	public List<CityInformation> listar() {
		RequestEntity<Void> request = RequestEntity
				.get(URI.create(URI_BASE))
				.build();
		
		ResponseEntity<CityInformation[]> response = restTemplate.exchange(request, CityInformation[].class);
		
		return Arrays.asList(response.getBody());
	}
}
