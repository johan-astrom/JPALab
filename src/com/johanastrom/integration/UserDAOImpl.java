package com.johanastrom.integration;

import com.johanastrom.users.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPALab");

    @Override
    public boolean create(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if (this.getOne(user.getSsn())!=null){
            System.out.println("A user with the specified SSN already exists.");
            return false;
        }
        em.persist(user);
        em.getTransaction().commit();
        return this.getOne(user.getSsn()) != null;
    }

    @Override
    public User getOne(String ssn) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, ssn);
        em.getTransaction().commit();
        return user;
    }

    @Override
    public List<User> get(String field, String searchValue) {
        String queryString = "";
        switch (field) {

            case "userName":
                queryString = "select u from User u where u.userName like :searchValue";
                break;

            case "password":
                queryString = "select u from User u where u.password like :searchValue";

                break;

            case "firstName":
                queryString = "select u from User u where u.firstName like :searchValue";
                break;

            case "lastName":
                queryString = "select u from User u where u.lastName like :searchValue";
                break;

            case "email":
                queryString = "select u from User u where u.email like :searchValue";
                break;

            case "phone":
                queryString = "select u from User u where u.phone like :searchValue";
                break;

        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<User> userList = em.createQuery(queryString, User.class)
                .setParameter("searchValue", "%" + searchValue + "%")
                .getResultList();
        em.getTransaction().commit();
        if (userList.isEmpty()){
            System.out.println("No users found.");
        }
        return userList;
    }

    @Override
    public List<User> getAll() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<User> userList = em.createQuery("select u from User u", User.class)
                .getResultList();
        em.getTransaction().commit();
        if (userList.isEmpty()){
            System.out.println("No users stored.");
        }
        return userList;
    }

    @Override
    public String update(String ssn, String field, String newValue) {
        String oldValue = "";
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User u = em.find(User.class, ssn);
        if (u==null){
            return null;
        }

        switch (field) {

            case "userName":
                oldValue = u.getUserName();
                u.setUserName(newValue);
                break;

            case "password":
                oldValue = u.getPassword();
                u.setPassword(newValue);
                break;

            case "firstName":
                oldValue = u.getFirstName();
                u.setFirstName(newValue);
                break;

            case "lastName":
                oldValue = u.getLastName();
                u.setLastName(newValue);
                break;

            case "email":
                oldValue = u.getEmail();
                u.setEmail(newValue);
                break;

            case "phone":
                oldValue = u.getPhone();
                u.setPhone(newValue);
                break;

        }
        em.getTransaction().commit();
        return oldValue;
    }

    @Override
    public boolean delete(String ssn) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User u = em.find(User.class, ssn);
        if (u==null){
            return false;
        }
        em.remove(u);
        em.getTransaction().commit();

        return this.getOne(u.getSsn())==null;
    }
}
