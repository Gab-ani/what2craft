package application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import albionDataCommunication.DataFetcher;
import albionDataCommunication.PriceResponse;
import logic.Item;

@Configuration
public class EntryPoint {
	
	@Autowired
	DataFetcher dataFetcher;
	
	@Bean
	public DataFetcher dataFetcher() {
		return new DataFetcher();
	}

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException, RestClientException {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EntryPoint.class);
		
		EntryPoint app = context.getBean(EntryPoint.class);
		app.startUp();
		
	}
	
	public void startUp() throws JsonMappingException, JsonProcessingException, RestClientException {
		
		Item item = new Item();
		item.setRequestName("T5_BAG");
		item.setQuality(1);
		
		
		PriceResponse[] responseForm = dataFetcher.fetchActualData(item, "Bridgewatch");
		System.out.println(responseForm[0]);
	}

}
