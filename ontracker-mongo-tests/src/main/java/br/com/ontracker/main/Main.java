package br.com.ontracker.main;

import java.util.List;

import br.com.ontracker.domain.Localization;
import br.com.ontracker.domain.Vehicle;
import br.com.ontracker.mongo.MongoDBStore;
import br.com.ontracker.mysql.MySQLStore;

public class Main {




	public static void main(String[] args) throws Exception{
		MongoDBStore mongoStore = new MongoDBStore();
		MySQLStore mySQLStore = new MySQLStore();






/*		mySQLStore.vehicles()
			.forEach(vehicle -> {
				mongoStore.save(vehicle);
			});*/













		mongoStore.find(Vehicle.class)
			.forEach(vechicle -> {
				try {

					System.out.println("Creatin Localation to vehicle -> " + vechicle.mysqlID);

					List<Localization> localizations = mySQLStore.Locacations(vechicle.mysqlID);

					System.out.println("Number of localization for this vehicle is -> " + localizations.size());

					localizations.forEach(localization -> {
							localization.vechile = vechicle;
							mongoStore.save(localization);
						}
					);



				} catch (Exception e) {
					e.printStackTrace();
				}
			});



	}






























































}
