package br.com.ontracker.main;

import static java.lang.System.currentTimeMillis;

import br.com.ontracker.mongo.MongoDBStore;

public class Main {




	public static void main(String[] args) throws Exception{
	//	new Processor().process();


		/*Vehicle vehicle = new MongoDBStore().find(Vehicle.class, 54);
		System.out.println(vehicle);*/

		long start = currentTimeMillis();

		System.out.println(new MongoDBStore().test(62).size());


		System.out.println("Total time -> " + ( ( currentTimeMillis() - start ) / 1000 ) + " seconds");


	}





































}
