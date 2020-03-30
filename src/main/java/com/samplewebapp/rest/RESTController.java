package com.samplewebapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samplewebapp.GetUserService;
import com.samplewebapp.places.Place;
import com.samplewebapp.places.PlacesService;

@RestController
public class RESTController {

	@Autowired
	private PlacesService placesService;
	
	@Autowired
	private GetUserService getUserService;

	@RequestMapping(value = "/places/", method = RequestMethod.GET)
	public List<Place> listPlaces() {
		System.out.println("Getting ALL places");
		return placesService.getItemsForUser(getUserService.getLoggedInUserName());
	}
	
	@RequestMapping(value = "/places/{id}", method = RequestMethod.GET)
	public Place individualPlace(@PathVariable("id") int id) {
		Place place = placesService.getPlace(id);

		if (place != null && place.getUser().equals(getUserService.getLoggedInUserName())) {
			return place;
		}
		return null;
	}
}
