package com.wesmart.stark.adapter.applicationservices.creditcardreqimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.wesmart.stark.adapter.application.out.creditcardreq.CreditCardRequester;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Defines the structure for an uts requester
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Component("utsRequesterImpl")
public class CreditCardRequesterImpl implements CreditCardRequester {

	private final MongoClient mongoClient;
	@Value("${spring.data.mongodb.database}")
	private String databaseName;
	@Value("${spring.data.mongodb.collection}")
	private String collectionName;
	@Value("${spring.data.mongodb.token-field}")
	private String tokenField;

	@Autowired
	public CreditCardRequesterImpl(@Value("${spring.data.mongodb.uri}") String mongoUri) {

		MongoClientURI mongoClientURI = new MongoClientURI(mongoUri);
		mongoClient = new MongoClient(mongoClientURI);
	}

	/**
	 * Make a petition to the uts app and retrieve the information of a credit card
	 *
	 * @param token - The token of the credit card in order to search it in the records
	 * @return - JsonNode with the information obtained in the uts app
	 * @throws JsonProcessingException - JsonProcessingException
	 */
	@Override public JsonNode getCreditCardInfo(String token) throws JsonProcessingException {

		MongoDatabase database = mongoClient.getDatabase(databaseName);
		MongoCollection<Document> collections = database.getCollection(collectionName);
		BasicDBObject query = new BasicDBObject();
		query.put(tokenField, token);
		Document creditCard = collections.find(query).first();
		ObjectMapper objectMapper = new ObjectMapper();
		if (creditCard != null) {
			return objectMapper.readTree(creditCard.toJson());
		} else {
			return null;
		}
	}
}
