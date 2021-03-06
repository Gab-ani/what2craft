package logic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Artifact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String partOf;
	private String requestName;
	private String type;			
	
	public Artifact() {
		
	}
	
	public Artifact(String name, String partOf, String requestName) {
		this.name = name;
		this.partOf = partOf;
		this.requestName = requestName;
	}
	
	public String name() {
		return name;
	}
	
	public String partOf() {
		return partOf;
	}
	
	public String requestName() {
		return requestName;
	}
	
	public String type() {
		return type;
	}
	
	public int getId() {
		return id;
	}
}
