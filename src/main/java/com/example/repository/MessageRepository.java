package com.example.repository;

import com.example.entity.PlayerMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MessageRepository {
    private final EntityManagerFactory entityManagerFactory;

    public MessageRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("message-pu");
    }

    public void saveMessage(PlayerMessage playerMessage) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(playerMessage);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
