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
	
	private String buildURL(int quality, String itemName, String city) {
		return "https://www.albion-online-data.com/api/v2/stats/prices/" + itemName + ".json?locations=" + city + "&qualities=" + quality;
	}
	
//	public PriceResponse[] fetchActualData(ItemBasic item, String city) throws JsonMappingException, JsonProcessingException, RestClientException {
//		RestTemplate restTemplate = new RestTemplate();
//		
//		ResponseEntity<PriceResponse[]> response = restTemplate.getForEntity(buildURL(item.getQuality(), item.getRequestName(), city), PriceResponse[].class);
//		PriceResponse[] prices = response.getBody();
//		
//		return prices;
//	}
	
	
}
