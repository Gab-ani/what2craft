package database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import logic.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	
	
}
