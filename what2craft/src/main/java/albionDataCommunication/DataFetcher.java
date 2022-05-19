package albionDataCommunication;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
	
	
	
	public PriceResponse fetchActualData(ItemCombined item, String city) throws JsonMappingException, JsonProcessingException, RestClientException {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<PriceResponse[]> response = restTemplate.getForEntity(buildURL(item, city), PriceResponse[].class);
		PriceResponse[] prices = response.getBody();
		
		return prices[0];
	}
	
	
}
