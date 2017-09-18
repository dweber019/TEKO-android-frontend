package ch.teko.michael.wgapp.model;

/**
 * Created by Michael on 11.09.2017.
 */

public class User {


    public String password;
    public String email;
    public Integer id;
    public String name;
    public String createAt;
    public String updatedAt;

    public User(String email, int id, String name) {


        this.email = email;
        this.id = id;
        this.name = name;
    }



}

