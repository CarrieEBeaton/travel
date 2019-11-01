package com.travel.demo.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
@Entity
public class Country {

	public Country(String country) {
		this.country = country;
	}
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long countryId;


	@NonNull
	private String country;
	
	@ElementCollection
	@CollectionTable(name="TripDates", joinColumns=@JoinColumn(name="countryId"))
	@Column(name="trip_dates")
	private List<LocalDate> tripDates;
	

	public List<LocalDate> getTripDates() {
		return this.tripDates;
	}
	   
	public void setTripDates(List<LocalDate> tripDates) {
		this.tripDates = tripDates;
	}
	
}




