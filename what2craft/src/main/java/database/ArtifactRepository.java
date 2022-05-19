package database;

import org.springframework.data.jpa.repository.JpaRepository;

import logic.Artifact;

public interface ArtifactRepository extends JpaRepository<Artifact, Integer>{
	
	public Artifact findByName(String name);
	
	public Artifact findByPartOf(String partOf);
	
}
