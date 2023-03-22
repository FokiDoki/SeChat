package com.seChat.artifacts;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection = "messages")
public class Message {
    private @MongoId
    ObjectId id;
    private String message;
    private String fromUsername;
    private String toUsername;
    private long timestamp;

    public ObjectId getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Message(String message, String fromUsername, String toUsername, long timestamp) {
        this.message = message;
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
        this.timestamp = timestamp;
    }
}
