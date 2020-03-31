package com.samplewebapp.places;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.samplewebapp.GetUserService;
import com.samplewebapp.places.PlacesService;


@Controller
@SessionAttributes("name")
public class PlacesController {
	
	@Autowired
	GetUserService getUserService;
	@Autowired
	private PlacesService placesService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	@RequestMapping(value = "/myplaces", method=RequestMethod.GET)
	public String placesPage(ModelMap model) {
		model.addAttribute("activePage", "myplaces");
		String currentUser = getUserService.getLoggedInUserName();
		model.addAttribute("name", currentUser);
		model.addAttribute("places", placesService.getItemsForUser(currentUser));
		return "myPlaces";
	}
	
	@RequestMapping(value = "/newplace", method=RequestMethod.GET)
	public String addPlaceGet(ModelMap model) {
		model.addAttribute("actionWord", "Add");
		model.addAttribute("placeToPopulate", new Place());
		return "place";
	}
	
	@RequestMapping(value = "/newplace", method=RequestMethod.POST)
	public String addPlacePost(@Valid Place place, ModelMap model, BindingResult result) {
		if (result.hasErrors())	return "newPlace";		
		place.setUser(getUserService.getLoggedInUserName());
		placesService.addPlace(place);
		model.clear();
		return "redirect:/myplaces";
	}
	
	@RequestMapping(value = "/updateplace", method=RequestMethod.GET)
	public String updatePlaceGet(ModelMap model, @RequestParam int id) {
		model.addAttribute("actionWord", "Update");
		model.addAttribute("placeToPopulate", placesService.getPlace(id));
		return "place";
	}
	
	@RequestMapping(value = "/updateplace", method=RequestMethod.POST)
	public String updatePlacePost(@Valid Place place, ModelMap model, BindingResult result) {
		if (result.hasErrors())	return "newPlace";		
		place.setUser(getUserService.getLoggedInUserName());
		placesService.updatePlace(place);
		model.clear();
		return "redirect:/myplaces";
	}
	
	@RequestMapping(value = "/deleteplace", method=RequestMethod.GET)
	public String deletePlace(@RequestParam int id) {
		placesService.removePlace(id);
		return "redirect:/myplaces";
	}
}
