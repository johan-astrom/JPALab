package com.johanastrom.main;

import com.johanastrom.users.User;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPALab");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        User u = em.find(User.class, "890508-1234");
        System.out.println(u);
        u.setFirstName("Johan");
        System.out.println(u);
        em.getTransaction().commit();

    }
}
