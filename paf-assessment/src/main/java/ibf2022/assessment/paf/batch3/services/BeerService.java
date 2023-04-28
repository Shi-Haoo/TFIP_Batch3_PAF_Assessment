package ibf2022.assessment.paf.batch3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.repositories.BeerRepository;

@Service
public class BeerService {

	@Autowired
	BeerRepository beerRepo;

	public List<Style> getStyles() {
		return beerRepo.getStyles();
	}

	public List<Beer> getBreweriesByBeer(String styleId){
		return beerRepo.getBreweriesByBeer(styleId);
	}
	
	// public Style getStyleNameById(String styleId){
		
	// 	return beerRepo.getStyleNameById(styleId);
	// }
	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public String placeOrder(/* You can add any number parameters here */) {
		// TODO: Task 5 
		
		return "";
	}

}
