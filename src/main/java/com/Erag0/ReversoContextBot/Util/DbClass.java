package com.Erag0.ReversoContextBot.Util;

import com.Erag0.ReversoContextBot.Entity.UsersEntity;
import com.Erag0.ReversoContextBot.Factories.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DbClass {
    private static DbClass dbClass = new DbClass();
    private static SessionFactory sessionFactory = new HibernateSessionFactory().getSessionFactory();

    private DbClass() { }

    public static DbClass getInstance() {
        return dbClass;
    }

    public void AddUser(long chat_id, String username, String command, String language) {
        UsersEntity user = new UsersEntity();
        user.setChatId(Long.toString(chat_id));
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
}
