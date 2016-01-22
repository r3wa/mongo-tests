package br.com.ontracker.process;

import static java.lang.System.currentTimeMillis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import br.com.ontracker.domain.HistoryTracker;
import br.com.ontracker.mongo.MongoDBStore;


public class ProcessorHistoryTracker {


	private static final ExecutorService EXECUTOR_SERVICE = new ForkJoinPool();




	public void process() throws InterruptedException, ExecutionException {

		List<Callable<String>> tasks = new ArrayList<>();


		for (int i = 0; i < 800; i++) {
			tasks.add(task());
		}

		excuteAll(tasks);

	}






























	private Callable<String> task() {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {


				long start = currentTimeMillis();

				MongoDBStore mongoStore = new MongoDBStore();

				IntStream.range(0, 100_000)
					.forEach( i -> {
						mongoStore.save(new HistoryTracker());
					});

				System.out.println( "Total Time -> " +  ((( currentTimeMillis() - start ) / 1000) / 60) + " s");

				return Thread.currentThread().getName() +  "finished";
			}
		};
	}










	private void excuteAll(List<Callable<String>> taks)throws InterruptedException, ExecutionException {

		List<Future<String>> future = EXECUTOR_SERVICE.invokeAll(taks);

		for (Future<String> f : future) {
			System.out.println(f.get());
		}
	}











}
