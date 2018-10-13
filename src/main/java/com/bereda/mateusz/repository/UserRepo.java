package com.bereda.mateusz.repository;

import com.bereda.mateusz.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;

public class UserRepo {

    public static Connection connection = null;

    public static void connect() {
        // Setup the connection with the DB
        try {
            Class.forName("com.mysql.jdbc.Driver");
            UserRepo.connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/logowanie?user=root&password=");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(User user) {
        try {

            String sqlInsert = "INSERT INTO TUser (login, pass) VALUES (?, ?)";

            PreparedStatement preparedStatement = UserRepo.connection.prepareStatement(sqlInsert);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, DigestUtils.md5Hex(user.getPass()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean authenticate(User user) {
        try {
            String sqlSelect = "SELECT * FROM TUser WHERE login = '" + user.getLogin() + "';";
            System.out.println(sqlSelect);
            Statement s = UserRepo.connection.createStatement();
            ResultSet resultSet = s.executeQuery(sqlSelect);

            while (resultSet.next()) {
                User userFromDB = new User();

                userFromDB.setId(resultSet.getInt("id"));
                userFromDB.setLogin(resultSet.getString("login"));
                userFromDB.setPass(resultSet.getString("pass"));

                if(user.getLogin().equals(userFromDB.getLogin()) &&
                        DigestUtils.md5Hex(user.getPass()).equals(userFromDB.getPass())) {
                    user=null;
                    userFromDB=null;
                    return true;
                } else {
                    user=null;
                    userFromDB=null;
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        user=null;
        return false;
    }
}
