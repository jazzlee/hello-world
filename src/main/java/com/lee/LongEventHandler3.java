package com.lee;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler3 implements EventHandler<LongEvent> {
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception{
	    System.out.println("Event <--3:" + event);
		Long l = event.get() + 3L;
		Thread.sleep(1000);
	}
}
