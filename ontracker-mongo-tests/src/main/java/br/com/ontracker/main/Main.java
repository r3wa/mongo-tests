package br.com.ontracker.main;

import static java.lang.System.currentTimeMillis;
import static org.apache.commons.lang3.math.NumberUtils.toLong;

import br.com.ontracker.mongo.MongoMorphiaStore;

public class Main {




	public static void main(String[] args) throws Exception{
		/*new Processor().process();*/
		//System.out.println(new MySQLStore().Locacations(1363).size());


		MongoMorphiaStore mongoMorphia = new MongoMorphiaStore();

		long start = currentTimeMillis();

		System.out.println("Number of documents -> " + mongoMorphia.localizations(toLong(args[0])).size() + " to vehicle -> " + args[0]);

		System.out.println("Total time -> " + ( ( currentTimeMillis() - start ) / 1000 ) + " seconds");

		System.out.println("----------------------------------------------------------------------------------------------");


	}





































}
