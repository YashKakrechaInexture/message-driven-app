package org.example.producer;

import org.example.constants.MessageConstants;
import org.example.dto.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Producer class produces messages and puts them into a message queue.
 */
public class Producer implements Runnable {

    /**
     * The message queue into which the producer puts messages.
     */
    private final BlockingQueue<Message> queue;

    /**
     * The counter for the total number of messages produced.
     */
    private final AtomicInteger messageCount;

    /**
     * Constructs a new Producer with the specified queue and message count.
     *
     * @param queue the message queue
     * @param messageCount the counter for produced messages
     */
    public Producer(BlockingQueue<Message> queue, AtomicInteger messageCount) {
        this.queue = queue;
        this.messageCount = messageCount;
    }

    /**
     * Runs the producer thread, generating and putting messages into the queue.
     */
    @Override
    public void run() {
        try {
            // Produce 100 messages
            for (int i = 0; i < 100; i++) {
                Message message = new Message("Message " + (i+1));
                queue.put(message);
                messageCount.incrementAndGet();
                System.out.println("Produced: " + message.getContent());

                // Simulate time delay
                Thread.sleep(50);
            }
            // Indicate end of production with a special message
            queue.put(new Message(MessageConstants.STOP));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
