package logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import database.ItemService;

public class Prices {
	
	@Autowired
	ItemService itemService;
	
	private PriceArchive lymhurst;
	private PriceArchive sterling;
	private PriceArchive thedford;
	private PriceArchive martlock;
	private PriceArchive bridgewatch;
	private PriceArchive caerleon;
	
	public Prices() {
		ArrayList<String> arts = itemService.getArtifactsList();
	}
	
}
