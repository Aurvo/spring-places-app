package com.samplewebapp.places;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

@Service
public class PlacesService {

	private static LinkedList<Place> places = new LinkedList<>();
	private static int curID = 0;
	
	static {
		addPlace("a", "Macy", "I love this place.", "asdf", true, new Date());
		addPlace("b", "Macy", "Looks cool!", "asdf", false, new Date());
		addPlace("c", "Brian", "Makes me feal like a kid.", "asdf", false, new Date());
		addPlace("d", "Brian", "Beautiful...", "asdf", false, new Date());
	}
	
	public static void addPlace(String name, String user, String description,
			String image, boolean beenThere, Date targetDate) {
		Place newPlace = new Place(curID++, name, user, description,
				image, beenThere, targetDate);
		places.add(newPlace);
	}
	
	public static void addPlace(Place newPlace) {
		newPlace.setId(curID++);
		places.add(newPlace);
	}
	
	public Place getPlace(int id) {
		for (Place place : places) {
			if (place.getId() == id) return place;
		}
		return null;
	}
	
	public void updatePlace(Place updatedPlace) {
		Place place = getPlace(updatedPlace.getId());
		place.setUser(updatedPlace.getUser());
		place.setName(updatedPlace.getName());
		place.setDescription(updatedPlace.getDescription());
		place.setImage(updatedPlace.getImage());
		place.setBeenThere(updatedPlace.isBeenThere());
		place.setTargetDate(updatedPlace.getTargetDate());
	}
	
	public boolean removePlace(int id) {
		Iterator<Place> iterator = places.iterator();
		while (iterator.hasNext()) {
			Place place = iterator.next();
			if (place.getId() == id) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}
	
	public static LinkedList<Place> getItemsForUser(String user) {
		LinkedList<Place> placesForUser = new LinkedList<>();
		for (Place place : places) {
			if (place.getUser().equals(user))
				placesForUser.add(place);
		}
		return placesForUser;
	}
}
