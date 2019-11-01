package com.travel.demo.service;

import java.util.Collection;

import com.travel.demo.dto.Country;

public interface CountryServiceLayer {

	public Country saveCountry(Country country);
	
	public Country getCountryById(Long id);
	
	public Collection<Country> findAllCountries();
	
	public Country updateCountry(Country country);
	
	public void deleteById(Long countryId);
}
