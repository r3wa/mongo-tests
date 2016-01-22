package br.com.ontracker.mongo;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;

public class MongoDBStore {



	private Datastore datastore;




	public MongoDBStore() {
		Morphia morphia = new Morphia();
		morphia.mapPackage("br.com.ontracker.domain");
		datastore = morphia.createDatastore(mongoClient(), "ontracker");
	}





	public void save(Object object) {
		datastore.save(object);
	}






	public <T> List<T> find(Class<T> c){
		return datastore.find(c).asList();
	}




	public <T> T find(Class<T> c, long id) {
		Query<T> query = datastore.createQuery(c).field("mysqlID").equal(id);
		return query.get();
	}































	private static MongoClient mongoClient() {
		return new MongoClient("localhost");
	}
















































	/*public static void main(String[] args) {
		new MongoStore().save(new HistoryTracker(1));
	}*/


}
