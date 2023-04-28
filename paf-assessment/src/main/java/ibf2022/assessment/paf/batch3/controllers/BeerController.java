package ibf2022.assessment.paf.batch3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.assessment.paf.batch3.models.Beer;
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

	
	//TODO Task 5 - view 2, place order

}
