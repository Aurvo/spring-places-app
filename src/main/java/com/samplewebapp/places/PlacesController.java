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
import org.springframework.web.multipart.MultipartFile;

import com.samplewebapp.GetUserService;
import com.samplewebapp.ImageService;
import com.samplewebapp.places.PlacesService;


@Controller
@SessionAttributes("username")
public class PlacesController {
	
	@Autowired
	GetUserService getUserService;
	@Autowired
	private PlacesService placesService;
	@Autowired
	private ImageService imageService;
	
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
		model.addAttribute("username", currentUser);
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
	public String addPlacePost(@Valid Place place, ModelMap model, BindingResult result,
			@RequestParam(required=false) MultipartFile imageFile) {
		if (result.hasErrors())	return "place";
		place.setUser(getUserService.getLoggedInUserName());
		handleNewUpdatedImage(imageFile, place);
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
	public String updatePlacePost(@Valid Place place, ModelMap model, BindingResult result,
			@RequestParam(required=false) MultipartFile imageFile) {
		if (result.hasErrors())	return "place";		
		place.setUser(getUserService.getLoggedInUserName());
		handleNewUpdatedImage(imageFile, place);
		placesService.updatePlace(place);
		model.clear();
		return "redirect:/myplaces";
	}
	
	@RequestMapping(value = "/deleteplace", method=RequestMethod.GET)
	public String deletePlace(@RequestParam int id) {
		String imageName = placesService.getPlace(id).getImageName();
		placesService.removePlace(id);
		imageService.deleteImage(imageName);
		return "redirect:/myplaces";
	}
	
	private void handleNewUpdatedImage(MultipartFile imageFile, Place place) {
		imageService.deleteImage(place.getImageName());
		if (imageFile == null || imageFile.isEmpty()) {			
			place.setImageName(null);
			return;
		}
		//generate name for image
		String imageName = imageService.generateFileNameWithSuffix(imageFile);
		place.setImageName(imageName);
		imageService.writeFile(imageFile, imageName);
	}
}
