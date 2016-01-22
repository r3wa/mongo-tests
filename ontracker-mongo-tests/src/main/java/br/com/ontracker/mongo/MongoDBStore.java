package br.com.ontracker.mongo;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

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

































	private static MongoClient mongoClient() {
		return new MongoClient("54.94.207.191");
	}











































	/*public static void main(String[] args) {
		new MongoStore().save(new HistoryTracker(1));
	}*/


}
