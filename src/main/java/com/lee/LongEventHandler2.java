package com.lee;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler2 implements EventHandler<LongEvent> {
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch)  throws Exception{
	    System.out.println("Event <--2:" + event);
		Long l = event.get() + 2L;
		Thread.sleep(10);
	}
}
