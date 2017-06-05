package wdsr.exercise2.startthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BusinessServiceWithExecutor {
	private NumericHelper helper;
	private ExecutorService service;
	
	public BusinessServiceWithExecutor(NumericHelper helper) {
		this.helper = helper;
		service = Executors.newSingleThreadExecutor();
	}

	/**
	 * Calculates Fibonacci number asynchronously and invokes the callback when result is available.
	 * This method returns immediately. 
	 * @param n Which Fibonacci number should be computed.
	 * @param callback Callback to be invoked when Fibonacci number is found.
	 */
	public void computeFibonacci(int n, FibonacciCallback callback) {	
		service.execute(()->{
			long value = helper.findFibonacciValue(n);
			callback.fibonacciComputed(value);
		});
	}
}
