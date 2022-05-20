package database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import logic.City;

public interface CityRepository extends JpaRepository<City, String>{

//	@Query(value = "UPDATE items SET craft_node = :node WHERE human_name = :where", nativeQuery = true)
//	public void setCraftNode(@Param("where") String where, @Param("node") String craftnode);
	
	@Query(value = "UPDATE cities SET mage_tax = :tax WHERE name = :where", nativeQuery = true)
	public void setMageTaxIn(@Param("where") String in, @Param("tax") int tax);
	
	@Query(value = "UPDATE cities SET hunter_tax = :tax WHERE name = :where", nativeQuery = true)
	public void setHunterTaxIn(@Param("where") String in, @Param("tax") int tax);
	
	@Query(value = "UPDATE cities SET warrior_tax = :tax WHERE name = :where", nativeQuery = true)
	public void setWarriorTaxIn(@Param("where") String in, @Param("tax") int tax);
	
	public City findByName(String name);
	
}
