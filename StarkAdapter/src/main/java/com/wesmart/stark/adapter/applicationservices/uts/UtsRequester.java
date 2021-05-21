package com.wesmart.stark.adapter.applicationservices.uts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class UtsRequester {

	private final MongoClient mongoClient;

	public UtsRequester() {

		var mongoClientURI = new MongoClientURI(
				"mongodb+srv://andres:d6ahdcenn39v@uts.l756p.mongodb.net/UTS?retryWrites=true&w=majority");

		mongoClient = new MongoClient(mongoClientURI);
	}

	public JsonNode getCreditCardInfo(String token) throws JsonProcessingException {

		MongoDatabase database = mongoClient.getDatabase("UTS");
		MongoCollection<Document> collections = database.getCollection("UTS");
		var query = new BasicDBObject();
		query.put("token", token);
		Document creditCard = collections.find(query).first();
		var objectMapper = new ObjectMapper();
		if (creditCard != null) {
			return objectMapper.readTree(creditCard.toJson());
		} else {
			return null;
		}
	}
}
