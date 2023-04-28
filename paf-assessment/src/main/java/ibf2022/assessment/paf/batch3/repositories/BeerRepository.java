package ibf2022.assessment.paf.batch3.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;

import static ibf2022.assessment.paf.batch3.repositories.DBQueries.*;

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
		SqlRowSet rs = sqlTemplate.queryForRowSet(GET_STYLES, Integer.parseInt(styleId));

		while(rs.next()){
			
			Beer b = new Beer();
			b.setBeerId(rs.getInt("beer_id"));
			b.setBeerName(rs.getString("beer_name"));
			b.setBeerDescription(rs.getString("description"));
			b.setBreweryId(rs.getInt("brewery_id"));
			b.setBreweryName("brewery_name");
			beers.add(b);
		}

		return beers;
	}

	// public Style getStyleNameById(String styleId){
	// 	SqlRowSet rs = sqlTemplate.queryForRowSet(GET_STYLE_NAME_BY_ID, Integer.parseInt(styleId));
		
	// 	Style s = null;

	// 	if(rs.first()){
	// 		s = new Style();
	// 		s.setStyleId(rs.getInt("id"));
	// 		s.setName(rs.getString("style_name"));
	// 	}

	// 	return s;
	// }

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(/* You can add any number of parameters here */) {
		// TODO: Task 4

		return Optional.empty();
	}
}
