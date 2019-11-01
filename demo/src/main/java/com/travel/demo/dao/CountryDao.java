package com.travel.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.travel.demo.dto.Country;
import com.travel.demo.dto.User;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface CountryDao extends JpaRepository<Country, Long> {

}
