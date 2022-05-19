package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;

import albionDataCommunication.DataFetcher;
import albionDataCommunication.PriceResponse;
import database.ItemService;

@Service
public class Prices {
	
//	@Autowired
//	ItemService itemService;
	
	@Autowired
	DataFetcher dataFetcher;
	
	private HashMap<String, PriceArchive> prices;
	
	public Prices(List<Artifact> arts) {
		prices = new HashMap<>();
		prices.put("lymhurst", new PriceArchive(arts));
		prices.put("sterling", new PriceArchive(arts));
		prices.put("thedford", new PriceArchive(arts));
		prices.put("martlock", new PriceArchive(arts));
		prices.put("bridgewatch", new PriceArchive(arts));
		prices.put("caerleon", new PriceArchive(arts));
	}
	
	public void memorize(ArrayList<ItemCombined> items, String city) {
		items.forEach((item) -> {
			
			try {
				PriceResponse response = dataFetcher.fetchActualData(item, city);
				if(response.isActual()) {
					prices.get(city).memorize(item, response.getMinSellPrice());
				}
			} catch (JsonProcessingException | RestClientException e) {
				e.printStackTrace();
			}
			
		});
	}
	
	public void update(String city, String resource) {
		
	}
	
	public void update(String city, String resource, int tier) {
		
	}
	
	public void update(String city, String resource, int tier, int chant) {
		
	}

	public void visualiseItemMemory(String city) {
		HashMap<ItemCombined, Integer> itemPrices = prices.get(city).getMemory();
		itemPrices.forEach((item, price) -> System.out.println(item.getName() + " " + price));
	}
	
	public void visualiseMaterial(String city, String resource) {
		int[][] materialPrices = prices.get(city).getPrices(resource);
		for(int i = 0; i < materialPrices[0].length; i++) {
			for(int j = 0; j < materialPrices.length; j++) {
				System.out.printf("%6d", materialPrices[j][i]);
			}
			System.out.println();
		}
	}
	
}
