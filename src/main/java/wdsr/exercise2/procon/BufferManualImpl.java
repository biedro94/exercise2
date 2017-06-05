package wdsr.exercise2.procon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Task: implement Buffer interface without using any *Queue classes from java.util.concurrent package.
 * Any combination of "synchronized", *Lock, *Semaphore, *Condition, *Barrier, *Latch is allowed.
 */
public class BufferManualImpl implements Buffer {
	
	private Queue<Order> queueOrders = new LinkedList<Order>();
	private Lock lock = new ReentrantLock();
	private Condition isNotFull = lock.newCondition();
	private Condition isNotEmpty = lock.newCondition();
	private boolean access =true;
	
	public void submitOrder(Order order) throws InterruptedException {
		lock.lock();
		
		while(!access){
			isNotEmpty.await();
		}
		
		queueOrders.add(order);
		isNotFull.signal();
		access = false;
		lock.unlock();
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		Order order;
		
		lock.lock();
		
		while(access){
			isNotFull.await();
		}
		
		order = queueOrders.remove();
		isNotEmpty.signal();
		access=true;
		lock.unlock();
		
		
		
		return order;
	}
}
