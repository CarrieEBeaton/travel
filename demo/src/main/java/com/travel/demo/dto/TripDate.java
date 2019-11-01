package com.travel.demo.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class TripDate {


	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long dateId;

    @ManyToOne(targetEntity = Country.class)
	private LocalDate tripDates;
    
	private Long countryId;
    
	public LocalDate getTripDates() {
		return this.tripDates;
	}
	
	public Long getCountry() {
		return this.countryId;
	}
	
	public void setCountry(Long countryId) {
		this.countryId = countryId;
	}
	
}


