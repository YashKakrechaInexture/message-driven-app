package org.example.dto;

/**
 * The Message class represents a message with content.
 */
public class Message {

    /**
     * The content of the message.
     */
    private final String content;

    /**
     * Constructs a new Message with the specified content.
     *
     * @param content the content of the message
     */
    public Message(String content) {
        this.content = content;
    }

    /**
     * Gets the content of the message.
     *
     * @return the content of the message
     */
    public String getContent() {
        return content;
    }
}

