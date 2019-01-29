package com.felipe.uolChallenge.client;

import java.net.URI;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.felipe.uolChallenge.domain.IpVigilante;

public class LocationByIpCliente {
		
		private RestTemplate restTemplate;
		private String URI_BASE;
		private String URN_BASE;
		
		public LocationByIpCliente(String url, String urn) {
			restTemplate = new RestTemplate();
			this.URN_BASE = urn;
			URI_BASE = url.concat(URN_BASE);
		}
		
		public IpVigilante listar() {
			RequestEntity<Void> request = RequestEntity
					.get(URI.create(URI_BASE))
					.build();
			
			ResponseEntity<IpVigilante> response = restTemplate.exchange(request, IpVigilante.class);
			
			return response.getBody();
		}
}
