package ch.teko.michael.wgapp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Michael on 11.09.2017.
 */

public class Purchase {

    public Double price;
    public Boolean payed;
    public Boolean settled;
    public Integer id;
    public String date;
    public Integer userId;


    public Purchase(Double price, Boolean payed, Boolean settled, Integer id, String date, Integer userId) {
        this.price = price;
        this.payed = payed;
        this.settled = settled;
        this.id = id;
        this.date = date;
        this.userId = userId;
    }

    public static List<Purchase> fromArray(JSONArray jsonArray) {
        List<Purchase> purchaseList = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                purchaseList.add(Purchase.fromObject(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.getStackTrace();
        }

        return purchaseList;
    }

    public static Purchase fromObject(JSONObject jsonObject) {
        try {
            return new Purchase(
                    jsonObject.isNull("price") ? null : jsonObject.getDouble("price"),
                    jsonObject.getInt("payed") == 0 ? false : true,
                    jsonObject.getInt("settled") == 0 ? false : true,
                    jsonObject.getInt("id"),
                    jsonObject.getString("date"),
                    jsonObject.isNull("userId") ? null : jsonObject.getInt("userId")
            );
        } catch (JSONException e) {
            e.getStackTrace();
        }
        return null;
    }
}
