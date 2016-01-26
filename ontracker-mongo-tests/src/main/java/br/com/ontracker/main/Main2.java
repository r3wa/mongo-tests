package br.com.ontracker.main;

import static java.lang.System.currentTimeMillis;

import br.com.ontracker.mongo.MongoDBStore;

public class Main2 {




	public static void main(String[] args) throws Exception{
		/*new Processor().process();*/

		long start = currentTimeMillis();

	//	System.out.println(new MongoDBStore().test().size());

//		System.out.println(new MySQLStore().Locacations(1363).size());

		System.out.println(new MongoDBStore().documents(1363).size());

		System.out.println("Total time -> " + ( ( currentTimeMillis() - start ) / 1000 ) + " seconds");

	}





































}
