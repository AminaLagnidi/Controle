package ma.lagnidi.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ma.lagnidi.demo.entities.Article;
import ma.lagnidi.demo.entities.Categorie;
import ma.lagnidi.demo.services.ArticleService;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

	@Autowired
	private ArticleService service;

	@GetMapping
	public List<Article> articleList() {
		return service.findAll();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateArticle(@PathVariable Long id, @RequestBody Article newArticle) {

		Article oldArticle = service.findById(id);
		if (oldArticle == null) {
			return new ResponseEntity<Object>("L'article avec id" + id + "n'existe pas ", HttpStatus.BAD_REQUEST);

		} else {
			newArticle.setId(id);
			return ResponseEntity.ok(service.update(newArticle));
		}
	}

	@PostMapping
	public Article creatProfessseur(@RequestBody Article article) {
		article.setId(0L);
		return service.create(article);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Article article = service.findById(id);
		if (article == null) {
			return new ResponseEntity<Object>("L'article avec Id " + id + " n'existe pas", HttpStatus.BAD_REQUEST);

		} else {
			return ResponseEntity.ok(article);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletProfessseur(@PathVariable Long id) {
		Article article = service.findById(id);
		if (article == null) {
			return new ResponseEntity<Object>("L'article avec Id " + id + " nexiste pas", HttpStatus.BAD_REQUEST);
		} else {
			service.delete(article);
			return ResponseEntity.ok("L'article avec id " + id + "a été suprimé");
		}
	}
	@GetMapping("/{date1}/{date2}/{categorie}")
	public ResponseEntity<Object> findBetweenDate(@PathVariable Date date1, @PathVariable Date date2, @PathVariable Categorie categorie) {
	    try {
	        List<Article> articles = service.findBetweenDate(date1, date2, categorie);
	        if (articles == null || articles.isEmpty()) {
	            return new ResponseEntity<>("Aucun article trouvé pour les dates spécifiées", HttpStatus.NOT_FOUND);
	        } else {
	            return ResponseEntity.ok(articles);
	        }
	    } catch (Exception e) {
	        // Log the exception for debugging purposes
	        e.printStackTrace();
	        return new ResponseEntity<>("Une erreur s'est produite lors de la recherche des articles", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	

}
