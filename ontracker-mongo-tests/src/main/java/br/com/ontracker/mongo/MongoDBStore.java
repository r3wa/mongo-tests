package br.com.ontracker.mongo;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBStore {



	private MongoDatabase mongoDatabase;




	public MongoDBStore() {
		mongoDatabase = new MongoClient("54.232.210.174").getDatabase("ontracker");
	}












	public List<Document> documents(long id){
		MongoCollection<Document> collection = mongoDatabase.getCollection("localization2");


		List<Document> documents = new ArrayList<>();


		Block<Document> printBlock = new Block<Document>() {
		     @Override
		     public void apply(final Document document) {
		         documents.add(document);
		     }
		};


		collection.find(eq("vechile.mysqlID", id)).forEach(printBlock);


	/*	collection.find(eq("vechile.mysqlID", id)).forEach(document -> {
			documents.add(document);
		});
*/

		return documents;
	}

























}
