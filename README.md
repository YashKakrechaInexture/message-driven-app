# Message-Driven Producer-Consumer Application

This is a simple message-driven application in core Java that simulates a producer-consumer scenario using a message queue. The application tracks and logs the total number of messages processed successfully and the number of errors encountered.

## Project Structure

The project consists of the following classes:

- `App`: The main application class that sets up and starts the producer and consumer threads.
- `Message`: Represents a message with content.
- `MessageConstants`: Defines constants used in the message-driven application.
- `Producer`: Produces messages and puts them into a message queue.
- `Consumer`: Consumes messages from the message queue and processes them.
- `AppTest`: Contains unit tests for the application.

## Classes and Their Responsibilities

### App

The `App` class is the entry point of the application. It sets up the producer and consumer threads, starts them, and waits for their completion.

### Message

The `Message` class represents a message with content. It has a constructor to initialize the content and a getter method to retrieve the content.

### MessageConstants

The `MessageConstants` class defines constants used in the application. It contains a single constant, `STOP`, which is used to signal the consumer to stop processing messages.

### Producer

The `Producer` class implements the `Runnable` interface and produces messages. It increments the message count for each produced message and puts the messages into the message queue.

### Consumer

The `Consumer` class implements the `Runnable` interface and consumes messages from the queue. It processes the messages, incrementing the success or error counters based on the result.

## How to Run

### Prerequisites

- Maven installed on your system.
- JDK installed on your system.

### Compile the Project

First, navigate to the root directory of the project where the `pom.xml` file is located.

Run the following command to compile the project:

```sh
mvn compile
```

### Running the Application

To run the application, use:

```sh
mvn exec:java -Dexec.mainClass="org.example.App"
```

### Running the Tests

To run the unit tests, execute:

```sh
mvn test
```
