package ibf2022.assessment.paf.batch3.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.OrderDetail;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.repositories.BeerRepository;
import ibf2022.assessment.paf.batch3.repositories.OrderRepository;

@Service
public class BeerService {

	@Autowired
	BeerRepository beerRepo;

	@Autowired
	OrderRepository orderRepo;

	public List<Style> getStyles() {
		return beerRepo.getStyles();
	}

	public List<Beer> getBreweriesByBeer(String styleId){
		return beerRepo.getBreweriesByBeer(styleId);
	}
	
	public Optional<Brewery> getBeersFromBrewery(String breweryId){
		return beerRepo.getBeersFromBrewery(breweryId);
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public String placeOrder(OrderDetail od) {
		// TODO: Task 5 
		UUID uuid = UUID.randomUUID();
        String hexString = uuid.toString().replace("-", "").substring(0, 8);
		
		od.setOrderId(hexString);
		
		orderRepo.insertOrder(od);
		
		return hexString;
	}

}
