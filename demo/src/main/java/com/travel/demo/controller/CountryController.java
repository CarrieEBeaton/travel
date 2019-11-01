package com.travel.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.demo.dto.Country;
import com.travel.demo.dto.User;
import com.travel.demo.service.CountryServiceLayer;

@RestController
public class CountryController {
	
	@Autowired
	private CountryServiceLayer service;

	public CountryController(CountryServiceLayer service) {
		this.service = service;
	}

	@GetMapping("/country")
	@CrossOrigin(origins = "http://localhost:4200")
	public Collection<Country> countries() {
		return service.findAllCountries();
	}
	
	@GetMapping("/country/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Country getCountryById(@PathVariable Long id) {
		return service.getCountryById(id);
	}

	@PostMapping("/country")
	public Country addCountry(@RequestBody Country newCountry) {
		return service.saveCountry(newCountry);
	}

	@PutMapping("/country")
	Country updateCountry(@RequestBody Country existingCountry) {
		return service.updateCountry(existingCountry);
	}

	@DeleteMapping("/country/{id}")
	void deleteCountry(@PathVariable Long id) {
		service.deleteById(id);
	}
}
