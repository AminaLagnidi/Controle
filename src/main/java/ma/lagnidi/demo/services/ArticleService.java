package ma.lagnidi.demo.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.lagnidi.demo.dao.IDao;
import ma.lagnidi.demo.entities.Article;
import ma.lagnidi.demo.entities.Categorie;
import ma.lagnidi.demo.repositories.ArticleRepository;

@Service
public class ArticleService implements IDao<Article> {

	@Autowired
	private ArticleRepository repository;

	@Override
	public Article create(Article o) {
		return repository.save(o);
	}

	@Override
	public List<Article> findAll() {
		return repository.findAll();
	}

	@Override
	public Article update(Article o) {
		return repository.save(o);
	}

	@Override
	public boolean delete(Article o) {
		try {
			repository.delete(o);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	@Override
	public Article findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Article>findBetweenDate(Date date1, Date date2, Categorie categorie) {
		return repository.findBetweenDate(date1, date2, categorie);
	}
}
