package ch.teko.michael.wgapp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Michael on 17.09.2017.
 */

public class Settle {

    public Integer id;
    public User userOwns;
    public User userLent;
    public String date;
    public Boolean payed;
    public Double amount;

    public Settle(Integer id, User userOwns, User userLent, String date, Boolean payed, Double amount) {
        this.id = id;
        this.userOwns = userOwns;
        this.userLent = userLent;
        this.date = date;
        this.payed = payed;
        this.amount = amount;
    }

    public static List<Settle> fromArray(JSONArray jsonArray) {
        List<Settle> settleList = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                settleList.add(Settle.fromObject(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.getStackTrace();
        }

        return settleList;
    }

    public static Settle fromObject(JSONObject jsonObject) {
        try {
            return new Settle(
                    jsonObject.getInt("id"),
                    User.fromObject(jsonObject.getJSONObject("userOwns")),
                    User.fromObject(jsonObject.getJSONObject("userLent")),
                    jsonObject.getString("date"),
                    jsonObject.getInt("payed") == 0 ? false : true,
                    jsonObject.isNull("amount") ? null : jsonObject.getDouble("amount")
            );
        } catch (JSONException e) {
            e.getStackTrace();
        }
        return null;
    }
}
