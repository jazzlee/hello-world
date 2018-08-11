package com.lee;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent>
{
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception
    {
        System.out.println("Event <--1:" + event);
    	long l = event.get() + 1L;
    	Thread.sleep(100);
    	
    }
}
