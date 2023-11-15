package ma.lagnidi.demo.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Categorie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nom;
	@JsonIgnore
	@OneToMany(mappedBy = "categorie")
	private List<Article> articles;
	
	@JsonIgnore
	@ManyToOne
	private Categorie categorieparent;
	
	
	public Categorie() {
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public List<Article> getArticles() {
		return articles;
	}


	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}


	public Categorie getCategorieparent() {
		return categorieparent;
	}


	public void setCategorieparent(Categorie categorieparent) {
		this.categorieparent = categorieparent;
	}
	
	
	
	
	

}
