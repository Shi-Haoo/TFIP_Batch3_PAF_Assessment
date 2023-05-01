package ibf2022.assessment.paf.batch3.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import ibf2022.assessment.paf.batch3.models.Order;
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

	@PostMapping(path="/brewery/{breweryId}/order")
	public String placeOrder(Model model, @PathVariable String breweryId, @RequestBody MultiValueMap<String,String> form){
		
		
		List<Order> orderList = new ArrayList<>();
		
		//form.keySet() produce the list of keys
		Set<String> keys = form.keySet();
		

		for(String key : keys){

			System.out.println(key);

			//get the index position of the current key
			int i = Integer.parseInt(key.substring(8));
			
			//we need to differentiate the key quantity in the payload. i.e use quantity0,quantity1..
			//If we don't differentiate the name of key and just put it as quantity, then no matter how
			//many times we loop, form.getFirst("quantity") will always just get first key. The database
			//will only store one data in attribute orders regardless of how many different kind of beers you order
			if(!form.getFirst("quantity%d".formatted(i)).isEmpty() && Integer.parseInt(form.getFirst("quantity%d".formatted(i)))>0){
				Optional<Brewery> brewOp = svc.getBeersFromBrewery(breweryId);
				List<Beer> beerList = brewOp.get().getBeers();
				int beerId = beerList.get(i).getBeerId();

				Order order = new Order(beerId, Integer.parseInt(form.getFirst("quantity%d".formatted(i))));
				orderList.add(order);
			}
		}

		if(orderList.isEmpty()){
			model.addAttribute("empty", "Your order cart is empty");
			return "view3";
		}
		
		OrderDetail od = new OrderDetail(LocalDateTime.now(), Integer.parseInt(breweryId), orderList);
		String orderId = svc.placeOrder(od);
		
		model.addAttribute("orderId", orderId);

		return "view3";
	}

}
