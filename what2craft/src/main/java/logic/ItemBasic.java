package logic;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	private String[] recipe;
	
	private boolean containsArtifact;
//	private String artifact;
	
	@OneToOne(targetEntity = Artifact.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = true, name = "artifact_id")
	private Artifact artifact;
	
	public ItemBasic() {
		
	}
	
	public ItemBasic(String humanName, String requestName, String craftNode, String craftBranch) {
		this.humanName = humanName;
		this.requestName = requestName;
		this.craftNode = craftNode;
		this.craftBranch = craftBranch;
		this.containsArtifact = false;
	}
	
	public int getBaseArtifactValue() {
		int value = 0;
		
		if(artifact.type() == "rune")
			value = 4;
		if(artifact.type() == "soul")
			value = 12;
		if(artifact.type() == "relic")
			value = 28;
		if(artifact.type() == "shard")
			value = 60;
		
		return getMaterialsAmount() * value;
	}
	
	public int getMaterialsAmount() {
		int mats = 0;
		for(int i = 0; i < recipe.length; i += 2) {
			mats += Integer.parseInt(recipe[i]);
		}
		return mats;
	}
	
	public String getName() {
		return humanName;
	}
	
	public String getRequestName() {
		return this.requestName;
	}
	
	public String[] getRecipe() {
		return recipe;
	}
	
	public String getCraftNode() {
		return craftNode;
	}
	
	public ItemBasic setRecipe(String[] recipe) {		// kinda "fake function" used only on db-setup period, returning self to allow   save ( getByName(name).setRecipe(...) ); syntax
		this.recipe = recipe;
		return this;
	}
	
	public void setArtifact(Artifact art) {
		containsArtifact = true;
		this.artifact = art;
	}
	
	public boolean requiresArtifact() {
		return containsArtifact;
	}
	
	public Artifact getArtifact() {
		return artifact;
	}
//	
//	public String getArtifactName() {
//		return artifact;
//	}

	public String getCraftBranch() {
		return craftBranch;
	}
	
//	public int getGradesToChant() {
//		return recipe.totalMaterials() * 6;
//	}
	
}
