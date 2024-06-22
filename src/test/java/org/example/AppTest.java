package org.example;

import org.example.consumer.Consumer;
import org.example.dto.Message;
import org.example.producer.Producer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * The message queue used for communication between producer and consumer.
     */
    private BlockingQueue<Message> queue;

    /**
     * The counter for the total number of messages produced.
     */
    private AtomicInteger messageCount;

    /**
     * The counter for the total number of messages processed successfully.
     */
    private AtomicInteger successCount;

    /**
     * The counter for the total number of error messages encountered during processing.
     */
    private AtomicInteger errorCount;

    /**
     * Sets up the test environment before each test.
     * Initializes the message queue and counters.
     */
    @BeforeEach
    public void setUp() {
        queue = new LinkedBlockingQueue<>();
        messageCount = new AtomicInteger(0);
        successCount = new AtomicInteger(0);
        errorCount = new AtomicInteger(0);
    }

    /**
     * Tests the producer-consumer scenario for a successful message processing case.
     *
     * @throws InterruptedException if the test thread is interrupted while waiting for threads to finish
     */
    @Test
    public void testProducerConsumerSuccessScenario() throws InterruptedException {
        // Create producer and consumer instances
        Producer producer = new Producer(queue, messageCount);
        Consumer consumer = new Consumer(queue, successCount, errorCount);

        // Create and start threads
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        // Wait for the threads to finish
        producerThread.join();
        consumerThread.join();

        // Test message production
        assertEquals(100, messageCount.get(), "Total messages produced should be 100");

        // Test message processing
        int totalProcessedMessages = successCount.get() + errorCount.get();
        assertEquals(100, totalProcessedMessages, "Total messages processed should be 100");

        // Test successful message processing count
        assertEquals(100, successCount.get(), "Total messages processed successfully should be 100");

        // Test error message processing count
        assertEquals(0, errorCount.get(), "Total errors encountered should be 0");
    }

    /**
     * Tests the producer-consumer scenario for a failure case where both threads are interrupted.
     *
     * @throws InterruptedException if the test thread is interrupted while waiting for threads to finish
     */
    @Test
    public void testProducerConsumerFailureScenario() throws InterruptedException {
        // Create producer and consumer instances
        Producer producer = new Producer(queue, messageCount);
        Consumer consumer = new Consumer(queue, successCount, errorCount);

        // Create and start threads
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        // Interrupt the threads
        producerThread.interrupt();
        consumerThread.interrupt();

        // Wait for the threads to finish
        producerThread.join();
        consumerThread.join();

        // Test message production
        assertEquals(0, messageCount.get(), "Total messages produced should be 100");

        // Test message processing
        int totalProcessedMessages = successCount.get() + errorCount.get();
        assertEquals(0, totalProcessedMessages, "Total messages processed should be 100");

        // Test successful message processing count
        assertEquals(0, successCount.get(), "Total messages processed successfully should be 100");

        // Test error message processing count
        assertEquals(0, errorCount.get(), "Total errors encountered should be 0");
    }
}
