package logic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int planks;
	private int cloth;
	private int ingots;
	private int leather;
	
	private String verbalisation;

	public Recipe() {
		
	}
	
	public Recipe(int planks, int cloth, int ingots, int leather, String verbalisation) {
		super();
		this.planks = planks;
		this.cloth = cloth;
		this.ingots = ingots;
		this.leather = leather;
		this.verbalisation = verbalisation;
	}
		
	public int totalMaterials() {
		return planks + cloth + ingots + leather;
	}
	
}
