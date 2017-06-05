package wdsr.exercise2.procon;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
 * Task: implement Buffer interface using one of *Queue classes from java.util.concurrent package.
 */
public class BufferQueueImpl implements Buffer {
	
	private BlockingQueue<Order> queue = new LinkedBlockingQueue<Order>();
	
	public void submitOrder(Order order) throws InterruptedException {
		queue.add(order);
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		return queue.take();
	}
}
