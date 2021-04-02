package com.Eragoo.reversoContextBot;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;

public class MessageRepository {
    private final MongoClient mongoClient = new MongoClient();
    private final DBCollection collection = mongoClient.getDB("bot").getCollection("messages");

    public MessageRepository() throws UnknownHostException {
    }

    public Message save(Message message) {
        DBObject entity = message.toMongoObject();
        collection.insert(entity);
        return message;
    }

    public void delete(Message message) {
        DBObject entity = message.toMongoObject();
        collection.remove(entity);
    }
}
