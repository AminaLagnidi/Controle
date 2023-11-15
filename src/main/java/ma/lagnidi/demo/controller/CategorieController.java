package ma.lagnidi.demo.controller;

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

import ma.lagnidi.demo.services.CategorieService;

import ma.lagnidi.demo.entities.Categorie;

@RestController
@RequestMapping("/api/v1/categories")
public class CategorieController {
    @Autowired
    private CategorieService service;

    @GetMapping
    public List<Categorie> findAllCategories() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Categorie categorie = service.findById(id);
        if (categorie == null) {
            return new ResponseEntity<Object>("Categorie avec Id " + id + " nexiste pas", HttpStatus.BAD_REQUEST);

        } else {
            return ResponseEntity.ok(categorie);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletCategorie(@PathVariable Long id) {
        Categorie categorie = service.findById(id);
        if (categorie == null) {
            return new ResponseEntity<Object>("Categorie avec Id " + id + " nexiste pas", HttpStatus.BAD_REQUEST);
        } else {
            service.delete(categorie);
            return ResponseEntity.ok("categorie avec id " + id + " suprime");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategorie(@PathVariable Long id, @RequestBody Categorie newcategorie) {

        Categorie oldCategorie = service.findById(id);
        if (oldCategorie == null) {
            return new ResponseEntity<Object>("categorie avec id" + id + "nexiste pas ", HttpStatus.BAD_REQUEST);

        } else {
            newcategorie.setId(id);
            return ResponseEntity.ok(service.update(newcategorie));
        }
    }

    @PostMapping
    public Categorie creatCategorie(@RequestBody Categorie categorie) {
        categorie.setId(0L);
        return service.create(categorie);
    }
    
    
    
   
}
