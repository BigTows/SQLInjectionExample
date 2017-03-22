package ru.sql.injection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.Object;
import java.util.Random;

/**
 * Created by bigtows on 21/03/2017.
 */
public class DataBase {
    private Connection connect;
    private boolean status;

    public DataBase(String host, String nameDB, String user, String password) {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://" +
                    host + "/" + nameDB +
                    "?user=" + user + "&password=" + password+"&allowMultiQueries=true");
            System.out.println("");
            this.status = true;
        } catch (SQLException ex) {
            this.status = false;
        }
    }


    public ResultSet sendQuery(String query) throws SQLException {
        return connect.createStatement().executeQuery(query);
    }

    public void init() throws SQLException {
        sendQuery("DROP DATABASE IF EXISTS SQLInjection");
        sendQuery("CREATE DATABASE SQLInjection");
        sendQuery("CREATE TABLE SQLInjection.users(id INTEGER PRIMARY KEY AUTO_INCREMENT,name VARCHAR(100),password VARCHAR(200))");
        for (int i = 0; i < 100; i++) {
            sendQuery("INSERT INTO SQLInjection.users (name,password) VALUES('" + generateRandomWords() + "','" + generateRandomWords()+"')");
        }
    }

    public boolean getStatus() {
        return status;
    }

    private String generateRandomWords()
    {
        String randomStrings = "sd";
        Random random = new Random();
        for (int i = 0; i < 1; i++) {
            char[] word = new char[random.nextInt(8) + 3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26));
            }
            randomStrings = new String(word);
        }
        return randomStrings;
    }
}
