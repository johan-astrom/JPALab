package com.johanastrom.main;

import com.johanastrom.integration.UserDAOImpl;
import com.johanastrom.users.User;

import java.util.List;

public class Main {

    public static void main(String[] args){

        UserDAOImpl userDAO = new UserDAOImpl();

        boolean persist = userDAO.create(new User("890607-1234", "johast", "myPwd123", "Johan", "Åström", "johan@johansmail.com", "098765432"));
        System.out.println(persist);

        User u = userDAO.getOne("890607-1234");
        System.out.println(u);

        List<User> userList = userDAO.get("firstName", "an");
        for (User user : userList){
            System.out.println(user);
        }

        userList = userDAO.getAll();
        for (User user : userList){
            System.out.println(user);
        }

        String valueBeforeUpdate = userDAO.update("890607-1234", "firstName", "JohansNyaNamn");
        System.out.println("Value before update: " + valueBeforeUpdate);
        System.out.println("Values after update: " + userDAO.getOne("890607-1234"));

        boolean deleted = (userDAO.delete("890607-1234"));
        System.out.println(deleted);
    }
}
