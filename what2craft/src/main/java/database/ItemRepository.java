package database;

import org.springframework.data.jpa.repository.JpaRepository;

import logic.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	
	
}
