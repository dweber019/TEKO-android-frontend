package ch.teko.michael.wgapp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 17.09.2017.
 */

public class PurchaseItem {

    public Integer id;
    public String name;
    public String description;

    public PurchaseItem(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static List<PurchaseItem> fromArray(JSONArray jsonArray) {
        List<PurchaseItem> purchaseList = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                purchaseList.add(PurchaseItem.fromObject(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.getStackTrace();
        }

        return purchaseList;
    }

    public static PurchaseItem fromObject(JSONObject jsonObject) {
        try {
            return new PurchaseItem(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    jsonObject.isNull("description") ? null : jsonObject.getString("description")
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
