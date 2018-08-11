package com.lee;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class LongEventProducer
{
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer)
    {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb)
    {
        long sequence = ringBuffer.next();  // Grab the next sequence
        try
        {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
                                                        // for the sequence
            event.set(bb.getLong(0));  // Fill with data
            
            System.out.println("Event Produced: " + event);
        }
        catch(Throwable t ) {
        	System.out.println("Exception :" + t);
        }
        finally
        {
            ringBuffer.publish(sequence);
        }
    }
}
