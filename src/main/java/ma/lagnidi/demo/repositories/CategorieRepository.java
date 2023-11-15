package ma.lagnidi.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.lagnidi.demo.entities.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
	
  public List<Categorie> findByCategorieparent(Categorie categorieparent);
   Categorie findById(long id);
}

