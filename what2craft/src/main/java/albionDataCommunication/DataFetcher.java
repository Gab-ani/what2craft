package albionDataCommunication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import logic.ItemBasic;

@Service
public class DataFetcher {	
	
	private String buildURL(ItemBasic base, int tier, int chant, int quality, String city) {
		String url = "https://www.albion-online-data.com/api/v2/stats/prices/";
		url += "T" + tier + "_";
		url += base.getRequestName();
		if(chant != 0)
			url += "@" + chant;
		url += ".json?locations=" + city;
		url += "&qualities=" + quality;
		return url;
	}
	
	
	
	public PriceResponse[] fetchActualData(ItemBasic base, int tier, int chant, int quality, String city) throws JsonMappingException, JsonProcessingException, RestClientException {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<PriceResponse[]> response = restTemplate.getForEntity(buildURL(base, tier, chant, quality, city), PriceResponse[].class);
		PriceResponse[] prices = response.getBody();
		
		return prices;
	}
	
	
}
