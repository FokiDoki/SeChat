package com.seChat.mongoRepo;

import com.seChat.artifacts.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    @Query("{ $or: [ " +
            "{$and: [ {toUsername:'?0'}, {fromUsername:'?1'}]}," +
            "{$and: [ {toUsername:'?1'}, {fromUsername:'?0'}]}" +
            "]}")
    List<Message> getAllWithUser(String toUsername, String fromUsername);

        @Query("{ $and: [{$or: [{$and: [ {toUsername:'?0'}, {fromUsername:'?1'}]}," +
                "{$and: [ {toUsername:'?1'}, {fromUsername:'?0'}]}]},{timestamp:{ $gte: ?2 }} ]}")
    List<Message> getAllWithUserTimestampSlice(String toUsername, String fromUsername, long fromTimestamp);
}
