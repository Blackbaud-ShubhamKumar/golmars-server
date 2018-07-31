package com.marse.martian.controllers.base;

import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marse.martian.entities.Hero;

@RestController
public class HerosController {

	@GetMapping("/heros/all")
	@CrossOrigin(origins = "http://localhost:4200")
	public Set<Hero> getAllHeros() {
		return Hero.getAllHeros();
	}

	@GetMapping("/heros/all/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Hero getHero(@PathVariable Integer id) {
		return Hero.getHero(id);
	}

	@PutMapping("/heros/all")
	@CrossOrigin(origins = "http://localhost:4200")
	public Hero updateHero(@RequestBody Hero hero) {
		return Hero.updateHero(hero);
	}
	
	@PostMapping("/heros/all")
	@CrossOrigin(origins = "http://localhost:4200")
	public Hero saveHero(@RequestBody Hero hero) {
		return Hero.saveHero(hero);
	}
	
	@DeleteMapping("/heros/all/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean deleteHero(@PathVariable Integer id) {
		return Hero.deleteHero(id);
	}

	@GetMapping("/heros/all/")
	@CrossOrigin(origins = "http://localhost:4200")
	public Set<Hero> searchHeros(@RequestParam String name){
		return Hero.searchHeros(name);
		
	}
	
	
	
	
}
