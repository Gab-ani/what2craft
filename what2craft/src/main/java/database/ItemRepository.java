package database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import logic.ItemBasic;

public interface ItemRepository extends JpaRepository<ItemBasic, Integer>{

	@Query(value = "UPDATE items SET craft_node = :node WHERE human_name = :where", nativeQuery = true)
	public void setCraftNode(@Param("where") String where, @Param("node") String craftnode);
	
}
