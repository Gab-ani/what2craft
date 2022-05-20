package database;

import org.springframework.data.jpa.repository.JpaRepository;

import logic.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>{

	Recipe findByVerbalisation(String verbalisation);
	
}
