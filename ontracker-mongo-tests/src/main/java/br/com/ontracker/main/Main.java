package br.com.ontracker.main;

import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.math.NumberUtils.toLong;

import java.util.List;

import br.com.ontracker.mysql.MySQLStore;

public class Main {




	public static void main(String[] args) throws Exception{
		/*new Processor().process();*/
		//System.out.println(new MySQLStore().Locacations(1363).size());



		/*MongoMorphiaStore mongoMorphia = new MongoMorphiaStore();*/

		MySQLStore mySQLStore = new MySQLStore();

		long start = currentTimeMillis();

		System.out.println("----------------------------------------------------------------------------------------------");

		/*System.out.println("Number of documents -> " + mongoMorphia.localizations(ids(args)).size() + " to vehicle -> " + asList(args));*/


		System.out.println("Number of documents -> " + mySQLStore.localizations(toLong(args[0])).size() + " to vehicle -> " + asList(args));

		System.out.println("Total time -> " + ( ( currentTimeMillis() - start ) / 1000 ) + " seconds");

		System.out.println("----------------------------------------------------------------------------------------------");


	}



































	private static List<Long> ids(String[] args) {
		return asList(args)
				.stream()
				.map(s -> toLong(s))
				.collect(toList());
	}





































}
