package org.example.consumer;

import org.example.constants.MessageConstants;
import org.example.dto.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Consumer class consumes messages from a message queue and processes them.
 * It increments the success or error counters based on the outcome of the processing.
 */
public class Consumer implements Runnable {

    /**
     * The message queue from which the consumer retrieves messages.
     */
    private final BlockingQueue<Message> queue;

    /**
     * The counter for the total number of messages processed successfully.
     */
    private final AtomicInteger successCount;

    /**
     * The counter for the total number of error messages encountered during processing.
     */
    private final AtomicInteger errorCount;

    /**
     * Constructs a new Consumer with the specified queue and counters for success and error messages.
     *
     * @param queue the message queue
     * @param successCount the counter for successfully processed messages
     * @param errorCount the counter for error messages
     */
    public Consumer(BlockingQueue<Message> queue, AtomicInteger successCount, AtomicInteger errorCount) {
        this.queue = queue;
        this.successCount = successCount;
        this.errorCount = errorCount;
    }

    /**
     * Runs the consumer thread, taking messages from the queue and processing them.
     */
    @Override
    public void run() {
        try {
            while (true) {
                Message message = queue.take();
                // Exit loop if end message is received
                if (MessageConstants.STOP.equals(message.getContent())) {
                    break;
                }
                processMessage(message);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Processes a message, incrementing the success or error count based on the result.
     *
     * @param message the message to process
     */
    private void processMessage(Message message) {
        try {
            // Simulate message processing
            System.out.println("Consumed: " + message.getContent());
            successCount.incrementAndGet();
            Thread.sleep(100); // Simulate time delay
        } catch (Exception e) {
            errorCount.incrementAndGet();
            System.err.println("Error processing message: " + message.getContent());
        }
    }
}

