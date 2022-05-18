package logic;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Reifying;

@Entity
@Table(name = "item_basic")
public class ItemBasic {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;	
	
	private String humanName;
	
	private String requestName;
	
	private String craftNode;
	private String craftBranch;
	
	public ItemBasic(String humanName, String requestName, String craftNode, String craftBranch) {
		this.humanName = humanName;
		this.requestName = requestName;
		this.craftNode = craftNode;
		this.craftBranch = craftBranch;
	}
	
//	public static Item parseAmmunition(String description) {
//		Item resulting = new Item();
//		
//		String[] parts = description.split(":");
//		
//		resulting.id = Integer.parseInt(parts[0]);
//		
//		parts[1] = parts[1].replaceAll("\\s+", "");
//		String[] cutEnchantment = parts[1].split("@");
//		resulting.requestName = cutEnchantment[0];
//		if(cutEnchantment.length > 1) {
//			resulting.grade = Integer.parseInt(cutEnchantment[1]);
//		}
//		
//		String[] idParts = cutEnchantment[0].split("_");
//		resulting.tier = Integer.parseInt(idParts[0].substring(1));
//		if(idParts.length > 3) {
//			resulting.gradeType = idParts[3].toLowerCase();
//		}
//				
//		resulting.humanName = parts[2].substring(1).split(" ", 2)[1];
//		
//		return resulting;
//	}
	
//	public Recipe getRecipe() {
//		return recipe;
//	}
//	
//	public int getGradesToChant() {
//		return recipe.totalMaterials() * 6;
//	}
	
}
