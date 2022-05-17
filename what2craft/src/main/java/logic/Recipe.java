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
	
	
	
	public int totalMaterials() {
		return planks + cloth + ingots + leather;
	}
	
}
