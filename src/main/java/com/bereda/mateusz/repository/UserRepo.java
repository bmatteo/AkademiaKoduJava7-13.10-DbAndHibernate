package com.bereda.mateusz.repository;

import com.bereda.mateusz.model.User;

import java.util.ArrayList;

public class UserRepo {
    private static ArrayList<User> userDB = new ArrayList<User>();

    public static void addUser(User user) {
        UserRepo.userDB.add(user);
    }

    public static boolean authenticate(User user) {

        for (User userFormDB: UserRepo.userDB) {
            if(userFormDB.getLogin().equals(user.getLogin()) &&
                    userFormDB.getPass().equals(user.getPass())) {
                return true;
            }
        }

        return false;
    }

    public static void initFakeData() {
        User u1 = new User();
        u1.setLogin("admin");
        u1.setPass("admin");

        User u2 = new User();
        u2.setLogin("user");
        u2.setPass("user");

        UserRepo.userDB.add(u1);
        UserRepo.userDB.add(u2);
    }
}
