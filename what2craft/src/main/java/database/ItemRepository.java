package database;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import logic.ItemBasic;

public interface ItemRepository extends JpaRepository<ItemBasic, Integer>{

//	@Query(value = "UPDATE items SET craft_node = :node WHERE human_name = :where", nativeQuery = true)
//	public void setCraftNode(@Param("where") String where, @Param("node") String craftnode);
	
	public ItemBasic findByHumanName(String name);
	
	public ArrayList<ItemBasic> findByCraftNode(String craftNode);
	
	public ArrayList<ItemBasic> findByCraftBranch(String craftBranch);
	
	@Query(value = "SELECT * FROM item_basic WHERE contains_artifact = true", nativeQuery = true)
	public ArrayList<ItemBasic> getArtifactItems();
	
	@Transactional 
	@Modifying
	@Query(value = "UPDATE item_basic SET recipe = :recipe WHERE human_name = :where", nativeQuery = true)
	public void setRecipe(@Param("where") String where, @Param("recipe") String[] recipe);
	
	
}
