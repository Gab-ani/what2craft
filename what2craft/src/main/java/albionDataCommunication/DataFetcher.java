package albionDataCommunication;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import logic.Artifact;
import logic.ItemCombined;

@Service
public class DataFetcher {	
	
	private String buildURL(ItemCombined item, String city) {
		String url = "https://www.albion-online-data.com/api/v2/stats/prices/";
		url += "T" + item.getTier() + "_";
		url += item.getRequestName();
		if(item.getChant() != 0)
			url += "@" + item.getChant();
		url += ".json?locations=" + city;
		url += "&qualities=" + item.getQuality();
		return url;
	}
	
	private String buildURL(String material, int tier, int chant, String city) {
		String url = "https://www.albion-online-data.com/api/v2/stats/prices/";
		url += "T" + tier + "_";
		if(material == "ingots") {
			url += "METALBAR";
		} else {
			url += material.toUpperCase();
		}
		if(chant != 0)
			url += "_LEVEL" + chant + "@" + chant;
		url += ".json?locations=" + city;
		return url;
	}
	
	private String buildURL(String branch, String status, int tier, String city) {
		String url = "https://www.albion-online-data.com/api/v2/stats/prices/";
		url += "T" + tier + "_";
		url += "JOURNAL_" + branch.toUpperCase() + "_" + status.toUpperCase();
		url += ".json?locations=" + city;
		return url;
	}
	
	private String buildURL(Artifact art, int tier, String city) {
		String url = "https://www.albion-online-data.com/api/v2/stats/prices/";
		url += "T" + tier + "_";
		url += art.requestName();
		url += ".json?locations=" + city;
		return url;
	}
	
	public PriceResponse fetchActualData(Artifact artifact, int tier, String city) throws JsonMappingException, JsonProcessingException, RestClientException {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<PriceResponse[]> response = restTemplate.getForEntity(buildURL(artifact, tier, city), PriceResponse[].class);
		PriceResponse[] prices = response.getBody();
		
		return prices[0];
	}
	
	public PriceResponse fetchActualData(String branch, String status, int tier, String city) throws JsonMappingException, JsonProcessingException, RestClientException {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<PriceResponse[]> response = restTemplate.getForEntity(buildURL(branch, status, tier, city), PriceResponse[].class);
		PriceResponse[] prices = response.getBody();
		
		return prices[0];
	}
	
	public PriceResponse fetchActualData(String material, int tier, int chant, String city) throws JsonMappingException, JsonProcessingException, RestClientException {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<PriceResponse[]> response = restTemplate.getForEntity(buildURL(material, tier, chant, city), PriceResponse[].class);
		PriceResponse[] prices = response.getBody();
		
		return prices[0];
	}
	
	public PriceResponse fetchActualData(ItemCombined item, String city) throws JsonMappingException, JsonProcessingException, RestClientException {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<PriceResponse[]> response = restTemplate.getForEntity(buildURL(item, city), PriceResponse[].class);
		PriceResponse[] prices = response.getBody();
		
		return prices[0];
	}
	
	
}
