package br.com.ontracker.process;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

import br.com.ontracker.domain.Localization;
import br.com.ontracker.domain.Vehicle;
import br.com.ontracker.mongo.MongoDBStore;
import br.com.ontracker.mysql.MySQLStore;


public class Processor {


	private static final ExecutorService EXECUTOR_SERVICE = new ForkJoinPool();




	public void process() throws InterruptedException, ExecutionException {

		List<Callable<String>> tasks = new ArrayList<>();


		MongoDBStore mongoStore = new MongoDBStore();


		mongoStore.find(Vehicle.class)
			.forEach(v -> {
				tasks.add(create(v.mysqlID));
			});


		// executing

		System.out.println("Executing  -> " + tasks.size() + " Tasks");

		EXECUTOR_SERVICE.invokeAll(tasks);

	}






























	private Callable<String> create(long id) {

		return () -> {

			MongoDBStore mongoStore = new MongoDBStore();


			MySQLStore mySQLStore = new MySQLStore();

			System.out.println("Creating the localizations to vehicle ->  " + id  +  " in thread -> " + Thread.currentThread().getName());

			List<Localization> localizations = mySQLStore.Locacations(id);

			System.out.println("Number of localizations for this vehicle is -> " + localizations.size());

			localizations.forEach(localization -> {
				localization.vechile = mongoStore.find(Vehicle.class, id);
				mongoStore.save(localization);
			});

			return null;
		};
	}

















}
