package albionDataCommunication;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import logic.ItemBasic;

public class AuctionHousePunching {				// the class intended only as a testing quality of life feature, the software isn't thought of as cheat/bot
												// as this class only job is to autoclick some places, I don't see any reason for it not to be a big static methods pile
	private Robot rob;
	
	private static final Point resetSearch = new Point(602, 242);
	private static final Point resetFilters = new Point(1333, 270);
	
	private static final Point search = new Point(600, 270);
	
	private static final Point tierMenu = new Point(914, 270);
	private static final Point chantMenu = new Point(1064, 270);
	private static final Point qualityMenu = new Point(1206, 270);

	private static final int menuOptionWidth = 27;
	
	public AuctionHousePunching() {
		System.setProperty("java.awt.headless", "false");
		try {
			rob = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void checkListOf(ArrayList<ItemBasic> items, int tier, int chant) {
		setEnchantment(chant);
		setTier(tier);
		items.forEach(item -> {
			search(item.getName());
			delay(2000);
		});
		resetFilters();
		setTier(tier);
		items.forEach(item -> {
			if(item.requiresArtifact()) {
				search(item.getArtifact().name());
				delay(2000);				
			}
		});
	}
	
	private void search(String itemName) {		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(itemName), null);
        
        resetSearch();
		search();
		paste();        
	}
	
	private void resetFilters() {
		click(resetFilters.x, resetFilters.y);
		rob.delay(1000);
	}
	
	private void setTier(int tier) {
		click(tierMenu.x, tierMenu.y);
		rob.delay(100);
		click(tierMenu.x, tierMenu.y + menuOptionWidth * (tier + 1));       // +1 for option "All" in the start of the menu
		rob.delay(100);
	}
	
	private void setEnchantment(int level) {
		click(chantMenu.x, chantMenu.y);
		rob.delay(100);
		click(chantMenu.x, chantMenu.y + menuOptionWidth * (level + 2));       // +2 for option "All" in the start of the menu AND the fact that chant is measured from 0 so 3rd level actually mean 5ft option
		rob.delay(100);
	}
	
	private void setQuality(int quality) {
		click(qualityMenu.x, qualityMenu.y);
		rob.delay(100);
		click(qualityMenu.x, qualityMenu.y + menuOptionWidth * (quality + 1));       // +1 for option "All" in the start of the menu
		rob.delay(100);
	}
	
	private void search() {
		click(search.x, search.y);
		rob.delay(100);
	}
	
	private void resetSearch() {
		click(resetSearch.x, resetSearch.y);
		rob.delay(100);
	}
	
	private void paste() {
		rob.keyPress(KeyEvent.VK_CONTROL);
		rob.delay(30);
		rob.keyPress(KeyEvent.VK_V);
		rob.delay(60);
		rob.keyRelease(KeyEvent.VK_V);
		rob.delay(30);
		rob.keyRelease(KeyEvent.VK_CONTROL);
		rob.delay(100);
	}
	
	private void click(int x, int y) {
		rob.mouseMove(x, y);
		rob.mousePress(MouseEvent.BUTTON1_MASK);
		rob.delay(30);
		rob.mouseRelease(MouseEvent.BUTTON1_MASK);
	}
	
	public void delay(int delay) {
		rob.delay(delay);
	}
	
}
