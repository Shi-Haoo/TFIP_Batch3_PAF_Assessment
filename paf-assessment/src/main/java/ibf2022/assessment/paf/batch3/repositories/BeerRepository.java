package ibf2022.assessment.paf.batch3.repositories;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;

import static ibf2022.assessment.paf.batch3.repositories.DBQueries.*;

@Repository
public class BeerRepository {

	@Autowired
	JdbcTemplate sqlTemplate;

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	//Group by style, sort by beer count descending then style name ascending
	
	public List<Style> getStyles() {
		// TODO: Task 2

		List<Style> styles = new ArrayList<>();
		
		SqlRowSet rs = sqlTemplate.queryForRowSet(GET_STYLES);

		while(rs.next()){
			Style s = new Style();

			s.setStyleId(rs.getInt("id"));
			s.setName(rs.getString("style_name"));
			s.setBeerCount(rs.getInt("beer_count"));
			styles.add(s);
		}

		return styles;
	}
		
	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(String styleId) {
		// TODO: Task 3
		List<Beer> beers = new ArrayList<>();
		SqlRowSet rs = sqlTemplate.queryForRowSet(GET_BREWERIES_BY_BEER, Integer.parseInt(styleId));

		while(rs.next()){
			
			Beer b = new Beer();
			b.setBeerId(rs.getInt("beer_id"));
			b.setBeerName(rs.getString("beer_name"));
			b.setBeerDescription(rs.getString("description"));
			b.setBreweryId(rs.getInt("brewery_id"));
			b.setBreweryName(rs.getString("brewery_name"));
			beers.add(b);
		}

		return beers;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(String breweryId) {
		// TODO: Task 4

		List<Beer> beers = new LinkedList<>();
		

		SqlRowSet rs = sqlTemplate.queryForRowSet(GET_BEERS_FROM_BREWERIES, Integer.parseInt(breweryId));

		Brewery br = null;
		Beer b = null;

		if(rs.first()){
			br = new Brewery();
			br.setBreweryId(rs.getInt("brewery_id"));
			br.setName(rs.getString("brewery_name"));
			br.setAddress1(rs.getString("address2"));
			br.setAddress2(rs.getString("address1"));
			br.setCity(rs.getString("city"));
			br.setPhone(rs.getString("phone"));
			br.setWebsite(rs.getString("website"));
			br.setDescription(rs.getString("brew_description"));

			b = new Beer();
			b.setBeerId(rs.getInt("beer_id"));
			b.setBeerName(rs.getString("beer_name"));
			b.setBeerDescription(rs.getString("description"));
			b.setBreweryId(rs.getInt("brewery_id"));
			b.setBreweryName("brewery_name");
			beers.add(b);
		}

		while(rs.next()){
			b = new Beer();
			b.setBeerId(rs.getInt("beer_id"));
			b.setBeerName(rs.getString("beer_name"));
			b.setBeerDescription(rs.getString("description"));
			b.setBreweryId(rs.getInt("brewery_id"));
			b.setBreweryName("brewery_name");
			beers.add(b);
		}

		br.setBeers(beers);

		if(beers.isEmpty())

		return Optional.empty();

		return Optional.of(br);
	}
}
