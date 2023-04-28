package ibf2022.assessment.paf.batch3.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.OrderDetail;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.services.BeerService;

@Controller
public class BeerController {

	@Autowired
	BeerService svc;
	//TODO Task 2 - view 0

	@GetMapping(path="/")
	public String getStyles(Model model){
		List<Style> styles = svc.getStyles();
		model.addAttribute("styles", styles);

		return "view0";
	}

	
	
	//TODO Task 3 - view 1
	@GetMapping(path="/beer/style/{id}")
	public String getBreweriesByBeer(Model model, @PathVariable String id, @RequestParam String styleName){
		
		List<Beer> beers = svc.getBreweriesByBeer(id);

		if(beers.isEmpty()){
			model.addAttribute("display", false);
            model.addAttribute("error", true);
			
			return "view1";
		}

		model.addAttribute("display", true);
		model.addAttribute("error", false);
		model.addAttribute("beers", beers);
		model.addAttribute("styleName", styleName);
		return "view1";
	}

	//TODO Task 4 - view 2
	@GetMapping(path="/beer/brewery/{id}")
	public String getBeersFromBrewery(Model model, @PathVariable String id, @RequestParam String breweryName){
		Optional<Brewery> brewOp = svc.getBeersFromBrewery(id);

		if(brewOp.isEmpty()){
			model.addAttribute("display", false);
			model.addAttribute("error", true);
			return "view2";
		}

			model.addAttribute("display", true);
			model.addAttribute("error", false);
			model.addAttribute("brew", brewOp.get());
			model.addAttribute("breweryName", breweryName);
			return "view2";
	}
	
	//TODO Task 5 - view 2, place order

	@PostMapping(path="/brewery/{id}/order")
	public String placeOrder(Model model, @PathVariable String id, @RequestBody MultiValueMap<String,String> form){
		
		OrderDetail od = new OrderDetail();
		
		od.setBreweryId(Integer.parseInt(id));
		od.setDate(LocalDateTime.now());
		

		svc.placeOrder(od);

	}

}
