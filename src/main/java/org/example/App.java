package org.example;

import org.example.consumer.Consumer;
import org.example.dto.Message;
import org.example.producer.Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The main application class that sets up and starts the producer and consumer threads.
 */
public class App {

    /**
     * The entry point of the application.
     * Sets up the producer and consumer threads, starts them, and waits for their completion.
     *
     * @param args command-line arguments (not used)
     */
    public static void main( String[] args ) {
        // Create a LinkedBlockingQueue to hold the messages
        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

        // Counters for tracking message production and processing
        AtomicInteger messageCount = new AtomicInteger(0);
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger errorCount = new AtomicInteger(0);

        // Create producer and consumer instances
        Producer producer = new Producer(queue, messageCount);
        Consumer consumer = new Consumer(queue, successCount, errorCount);

        // Create threads for producer and consumer
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        // Start the threads
        producerThread.start();
        consumerThread.start();

        // Wait for the threads to finish
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Print the final counts
        System.out.println("Total messages produced: " + messageCount.get());
        System.out.println("Total messages processed successfully: " + successCount.get());
        System.out.println("Total errors encountered: " + errorCount.get());
    }
}
