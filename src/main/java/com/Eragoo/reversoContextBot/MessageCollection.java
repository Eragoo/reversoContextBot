package com.Eragoo.reversoContextBot;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.Optional;

public class MessageCollection {
    private final MongoClient mongoClient = new MongoClient();
    private DBCollection collection = mongoClient.getDB("bot").getCollection("messages");

    public MessageCollection(String database, String collection) throws UnknownHostException {
        this.collection = mongoClient.getDB(database).getCollection(collection);
    }

    public MessageCollection() throws UnknownHostException {
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

    public Optional<Message> findById(ObjectId id) {
        DBObject entity = new BasicDBObject().append("_id", id);
        DBCursor objects = collection.find(entity);
        if (objects.size() <= 0) {
            return Optional.empty();
        }
        return Optional.of(Message.valueOf(objects.one()));
    }

    public void deleteAll() {
        collection.drop();
    }
}
