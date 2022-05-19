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
public class ItemBasic {					// representation of abstract "type" of item (see as "ingame sprite" property), in other words, "all claymores" shared properties etc.
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;	
	
	private String humanName;
	
	private String requestName;
	
	private String craftNode;
	private String craftBranch;
	
	public ItemBasic() {
		
	}
	
	public ItemBasic(String humanName, String requestName, String craftNode, String craftBranch) {
		this.humanName = humanName;
		this.requestName = requestName;
		this.craftNode = craftNode;
		this.craftBranch = craftBranch;
	}
	
	public String getName() {
		return humanName;
	}
	
	public String getRequestName() {
		return this.requestName;
	}
	
//	public Recipe getRecipe() {
//		return recipe;
//	}
//	
//	public int getGradesToChant() {
//		return recipe.totalMaterials() * 6;
//	}
	
}
