package ch.teko.michael.wgapp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 11.09.2017.
 */

public class Item {

    public Integer id;
    public String name;

    public Item(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<Item> fromArray(JSONArray jsonArray) {
        List<Item> purchaseList = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                purchaseList.add(Item.fromObject(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.getStackTrace();
        }

        return purchaseList;
    }

    public static Item fromObject(JSONObject jsonObject) {
        try {
            return new Item(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name")
            );
        } catch (JSONException e) {
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
