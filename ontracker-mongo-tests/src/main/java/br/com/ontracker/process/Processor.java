package br.com.ontracker.process;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

import br.com.ontracker.domain.Localization;
import br.com.ontracker.domain.Vehicle;
import br.com.ontracker.mongo.MongoMorphiaStore;
import br.com.ontracker.mysql.MySQLStore;


public class Processor {


	private static final ExecutorService EXECUTOR_SERVICE = new ForkJoinPool();




	public void process() throws InterruptedException, ExecutionException {

		List<Callable<String>> tasks = new ArrayList<>();


		MongoMorphiaStore mongoStore = new MongoMorphiaStore();


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

			MongoMorphiaStore mongoStore = new MongoMorphiaStore();


			MySQLStore mySQLStore = new MySQLStore();

			System.out.println("Starting create the localizations to vehicle ->  " + id  +  " in thread -> " + Thread.currentThread().getName());

			List<Localization> localizations = mySQLStore.localizations(id);

			System.out.println("Number of localizations for this vehicle is -> " + localizations.size());

			localizations.forEach(localization -> {
				localization.vechile = mongoStore.find(Vehicle.class, id);
				mongoStore.save(localization);
			});

			System.out.println("Finished create the localizations to vehicle ->  " + id  +  " in thread -> " + Thread.currentThread().getName());


			return null;
		};
	}

















}
