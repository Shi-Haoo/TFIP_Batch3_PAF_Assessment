package ibf2022.assessment.paf.batch3.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Order;
import ibf2022.assessment.paf.batch3.models.OrderDetail;

@Repository
public class OrderRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	// TODO: Task 5

	public String insertOrder(OrderDetail orderd){

		Document doc = new Document();
		doc = Document.parse(orderd.toJson().toString());
		mongoTemplate.insert(doc, "orders");
		return orderd.getOrderId();
	}
}
