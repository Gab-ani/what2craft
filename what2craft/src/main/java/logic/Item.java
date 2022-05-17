package logic;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Reifying;

@Entity
@Table(name = "items")
public class Item {
	
	@Id
	private int id;	// not generated, I plan to use ids from formatted/items list
	
	private int tier;
	private int grade;
	private int quality;
	private String humanName;
	
	private String requestName;
	
	private Recipe recipe;
	private String artifact;
	
	@OneToOne(targetEntity = PricesRecord.class, fetch = FetchType.EAGER)
	private PricesRecord prices;
	
	private String gradeType;
//	private int gradesToEnchant;
	
	private int actualPrice;
	private boolean trusted;
	
	public Item() {
		
	}
	
	public static Item parseAmmunition(String description) {
		Item resulting = new Item();
		
		String[] parts = description.split(":");
		
		resulting.id = Integer.parseInt(parts[0]);
		
		parts[1] = parts[1].replaceAll("\\s+", "");
		String[] cutEnchantment = parts[1].split("@");
		resulting.requestName = cutEnchantment[0];
		if(cutEnchantment.length > 1) {
			resulting.grade = Integer.parseInt(cutEnchantment[1]);
		}
		
		String[] idParts = cutEnchantment[0].split("_");
		System.out.println(idParts[0].substring(1));
		resulting.tier = Integer.parseInt(idParts[0].substring(1));
		if(idParts.length > 3) {
			System.out.println(idParts[3].toLowerCase());
			resulting.gradeType = idParts[3].toLowerCase();
		}
				
		resulting.humanName = parts[2].substring(1);
		
		return null;
	}

	public int getQuality() {
		return quality;
	}

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String id) {
		this.requestName = id;	
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}
	
	public Recipe getRecipe() {
		return recipe;
	}
	
	public int getGradesToChant() {
		return recipe.totalMaterials() * 6;
	}
	
}
