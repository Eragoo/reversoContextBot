package com.Erag0.ReversoContextBot.Util;

import com.Erag0.ReversoContextBot.Entity.UsersEntity;
import com.Erag0.ReversoContextBot.Factories.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DbClass {
    private static DbClass dbClass = new DbClass();
    private static SessionFactory sessionFactory = new HibernateSessionFactory().getSessionFactory();

    private DbClass() { }

    public static DbClass getInstance() {
        return dbClass;
    }

    public void AddUser(long chat_id, String username, String command, String language) {
        UsersEntity user = new UsersEntity();
        user.setChatId(chat_id);
        user.setCommand(command);
        user.setLanguage(language);
        user.setUsername(username);

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void UpdateUser(long chat_id, String username, String command, String language) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        UsersEntity user = (UsersEntity) session.get(UsersEntity.class, chat_id);
        user.setCommand(command);
        session.update(user);
        transaction.commit();
        session.close();
    }
    public long Count(long chat_id) {
        Session session = sessionFactory.openSession();
        Query query =  session.createQuery("select count(*) from UsersEntity Users where Users.chatId=:chat_id");
        query.setParameter("chat_id", chat_id);
        Long count = (Long)query.uniqueResult();
        session.close();
        return count;
    }

    public String RestoreCommand(long chat_id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        UsersEntity user = (UsersEntity) session.get(UsersEntity.class, chat_id);
        session.update(user);
        transaction.commit();
        session.close();

        return user.getCommand();
    }
}
