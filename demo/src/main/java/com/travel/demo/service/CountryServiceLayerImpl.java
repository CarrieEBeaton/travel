package com.travel.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.travel.demo.dao.CountryDao;
import com.travel.demo.dto.Country;
import com.travel.demo.dto.User;

@Service
public class CountryServiceLayerImpl implements CountryServiceLayer {

    private CountryDao countryDao;
	
	public CountryServiceLayerImpl(CountryDao countryDao) {
		this.countryDao = countryDao;
	}
	
	private void findMostConsectiveDays(ArrayList<LocalDate> localDates) {
		HashMap<LocalDate, Integer> consectiveDates = new HashMap<>();
		Collections.sort(localDates);
		for (int i = 0; i < localDates.size(); i++) {
			LocalDate key = localDates.get(i);
            LocalDate yesterday = key.minusDays(1);

            if (consectiveDates.containsKey(yesterday))
            {
                int consecutiveDay = Collections.frequency(localDates, key);
                int priorDay = Collections.frequency(localDates, yesterday);

                if (consecutiveDay >= priorDay) {
                    consectiveDates.put(key, priorDay);
                } else {
                    consectiveDates.put(key, consecutiveDay);
                }
            } else {
                consectiveDates.put(key, 1);
            }
		}
	}
	
	//one country they could
	//attend and what dates they could attend that country. Find which dates for each country
	//that the most people could attend in two consecutive days and return the dates as strings
	//that they would be attending them. POST the countries with the dates as JSON properties
	//to their server until you get a status of 200.

	public String getMostConsectiveDatesAttended(HashMap<LocalDate, Integer> hm) {

		List<Map.Entry<LocalDate, Integer>> list = new LinkedList<Map.Entry<LocalDate, Integer>>(hm.entrySet());


		Collections.sort(list, new Comparator<Map.Entry<LocalDate, Integer>>() {
			public int compare(Map.Entry<LocalDate, Integer> o1, Map.Entry<LocalDate, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});


		HashMap<LocalDate, Integer> temp = new LinkedHashMap<LocalDate, Integer>();
		for (Map.Entry<LocalDate, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		for (Map.Entry<LocalDate, Integer> entry : temp.entrySet()) {
			LocalDate yesterday =  entry.getKey().minusDays(1);

            return "The most attended consective days are  " + entry.getKey().toString() +
                    " prior day  " + yesterday.toString();
        }
		
		return "";
	}

	@Override
	public Country saveCountry(Country country) {
		return this.countryDao.save(country);
	}

	@Override
	public Collection<Country> findAllCountries() {
		return countryDao.findAll().stream()
				.collect(Collectors.toList());
	}

	@Override
	public Country updateCountry(Country country) {
		return this.countryDao.save(country);
	}

	@Override
	public void deleteById(Long countryId) {
		this.countryDao.deleteById(countryId);
	}
	
	@Override
	public Country getCountryById(Long countryId) {
		return this.countryDao.getOne(countryId);
	}
}
