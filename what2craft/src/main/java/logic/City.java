package logic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City {

	@Id
	private String name;
	
	private int mageTax;
	private int hunterTax;
	private int warriorTax;
	
	private String[] bonusNodes;
	private double bonusNodesValue;
	
	public City() {
		
	}
	
	public City(String name, String[] nodes) {
		this.name = name;
		this.bonusNodes = nodes;
		this.bonusNodesValue = 1.33;
	}
	
	public String[] getBonusNodes() {
		return bonusNodes;
	}
	
	public int getMageTax() {
		return mageTax;
	}
	
	public int getHunterTax() {
		return hunterTax;
	}
	
	public int getWarriorTax() {
		return warriorTax;
	}
	
}
