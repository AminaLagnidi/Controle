package ma.lagnidi.demo.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.lagnidi.demo.entities.Article;
import ma.lagnidi.demo.entities.Categorie;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
	   @Query("SELECT a FROM Article a WHERE a.date BETWEEN :date1 AND :date2 AND a.categorie = :categorie")
	    List<Article> findBetweenDate(
	        @Param("date1") Date date1,
	        @Param("date2") Date date2,
	        @Param("categorie") Categorie categorie
	    );
}
