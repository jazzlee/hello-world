package com.lee;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler4 implements EventHandler<LongEvent> {
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch)  throws Exception{
	    System.out.println("Event <--4:" + event);
		Long l = event.get() + 4L;
		Thread.sleep(1);
	}
}