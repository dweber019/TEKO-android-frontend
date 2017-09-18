package ch.teko.michael.wgapp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 11.09.2017.
 */

public class User {


    public String password;
    public String email;
    public Integer id;
    public String name;

    public User(Integer id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }


    public User(Integer id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }


    public static List<User> fromArray(JSONArray jsonArray) {
        List<User> userList = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                userList.add(User.fromObject(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.getStackTrace();
        }

        return userList;
    }

    public static User fromObject(JSONObject jsonObject) {
        try {
            return new User(
                    jsonObject.getInt("id"),
                    jsonObject.getString("email"),
                    jsonObject.getString("name")
            );
        } catch (JSONException e) {
            e.getStackTrace();
        }
        return null;


    }
}

