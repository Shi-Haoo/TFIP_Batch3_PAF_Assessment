package ibf2022.assessment.paf.batch3.repositories;

public class DBQueries {
    public static final String GET_STYLES = """
    SELECT s.id, s.style_name, COUNT(b.id) AS beer_count
    FROM styles AS s
    LEFT JOIN beers AS b ON b.style_id = s.id
    GROUP BY s.id
    ORDER BY beer_count DESC, s.style_name ASC; 
    """;

    public static final String GET_BREWERIES_BY_BEER = """
            SELECT b.id AS beer_id, b.name AS beer_name, b.descript AS description, br.id AS brewery_id, br.name AS brewery_name
            FROM styles AS s
            LEFT JOIN beers AS b ON b.style_id = s.id
            LEFT JOIN breweries AS br ON br.id = b.brewery_id
            WHERE s.id=?
            ORDER BY beer_name ASC;
            """;

    public static final String GET_BEERS_FROM_BREWERIES = """
            SELECT b.id AS beer_id, b.name AS beer_name, b.descript AS description, br.id AS brewery_id, br.name AS brewery_name, br.descript AS brew_description, br.address1 AS address1, br.address2 AS address2, br.city AS city, br.phone AS phone, br.website AS website
            FROM beers AS b
            LEFT JOIN breweries AS br ON br.id = b.brewery_id
            WHERE brewery_id=?
            ORDER BY beer_name ASC;
            """;
            
            

}
