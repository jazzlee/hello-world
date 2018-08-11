package com.lee;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.lmax.disruptor.*;
import com.lmax.disruptor.RingBuffer;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LongEventMain {
	public static void main(String[] args) throws Exception {
		// Executor that will be used to construct new threads for consumers
		Executor executor = Executors.newCachedThreadPool();

		// The factory for the event
		LongEventFactory factory = new LongEventFactory();

		// Specify the size of the ring buffer, must be power of 2.
		int bufferSize = 1024 * 1024;

		// Construct the Disruptor
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, DaemonThreadFactory.INSTANCE,
				ProducerType.SINGLE, new YieldingWaitStrategy());

		// Connect the handler
		disruptor.handleEventsWith(new LongEventHandler(), new LongEventHandler2()).then(new LongEventHandler3(),
				new LongEventHandler4()); //

		// Get the ring buffer from the Disruptor to be used for publishing.
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

		// LongEventProducer producer = new LongEventProducer(ringBuffer);

		LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);

		ByteBuffer bb = ByteBuffer.allocate(8);
		for (long l = 0; l < bufferSize; l++) {
			bb.putLong(0, l);
			producer.onData(bb);
//			Thread.sleep(1);
		}
		Thread.sleep(4000);
		long start = System.currentTimeMillis();
		System.out.println(start);
		// Start the Disruptor, starts all threads running
		disruptor.start();

		for (long l = bufferSize; true; l++) {
			bb.putLong(0, l);
			producer.onData(bb);
			Thread.sleep(1);
		}

	}
}
