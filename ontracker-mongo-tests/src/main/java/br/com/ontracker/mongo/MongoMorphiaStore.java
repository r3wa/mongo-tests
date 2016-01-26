package br.com.ontracker.mongo;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;

import br.com.ontracker.domain.Localization;

public class MongoMorphiaStore {



	private Datastore datastore;




	public MongoMorphiaStore() {
		Morphia morphia = new Morphia();
		morphia.mapPackage("br.com.ontracker.domain");
		datastore = morphia.createDatastore(mongoClient(), "ontracker");
	}





	public void save(Object object) {
		datastore.save(object);
	}






	public List<Localization> localizations(long id){
		return datastore.createQuery(Localization.class)
				.disableValidation()
				.filter("vechile.mysqlID =", id)
				.asList();
	}





	public <T> List<T> find(Class<T> c){
		return datastore.find(c).asList();
	}






	public <T> T find(Class<T> c, long id) {
		Query<T> query = datastore.createQuery(c).field("mysqlID").equal(id);
		return query.get();
	}































	private static MongoClient mongoClient() {
		//return new MongoClient("54.207.116.140");
		return new MongoClient("localhost");
	}
















































	/*public static void main(String[] args) {
		new MongoStore().save(new HistoryTracker(1));
	}*/


}
