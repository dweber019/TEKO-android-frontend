package ch.teko.michael.wgapp.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tzhweda9 on 17.09.17.
 */

public class RequestHelper {

    private static String baseURL = "https://wg-app.scapp.io/api";

    public static void get(Context context, String uri, JsonObjectOperator operator) {
        RequestHelper.request(context, uri, null, operator, Request.Method.GET, true);
    }

    public static void getAll(Context context, String uri, JsonArrayOperator operator) {
        RequestHelper.requestAll(context, uri, null, operator, Request.Method.GET, true);
    }

    public static void post(Context context, String uri, JSONObject json, JsonObjectOperator operator) {
        RequestHelper.request(context, uri, json, operator, Request.Method.POST, true);
    }

    public static void put(Context context, String uri, JSONObject json, JsonObjectOperator operator) {
        RequestHelper.request(context, uri, json, operator, Request.Method.PUT, true);
    }

    public static void delete(Context context, String uri, JsonObjectOperator operator) {
        RequestHelper.request(context, uri, null, operator, Request.Method.DELETE, true);
    }

    public static void login(Context context, String uri, JSONObject json, JsonObjectOperator operator) {
        RequestHelper.request(context, uri, json, operator, Request.Method.POST, false);
    }

    private static void request(Context context, String uri, JSONObject json, JsonObjectOperator operator, Integer method, Boolean withToken) {

        final RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, RequestHelper.baseURL + uri, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("VOLLEY", response.toString());
                operator.op(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("accept", "application/json");
                headers.put("Content-Type", "application/json");
                if (withToken) {
                    headers.put("Authorization", "Bearer " + TokenHelper.getToken(context));
                }
                return headers;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }

    private static void requestAll(Context context, String uri, JSONArray json, JsonArrayOperator operator, Integer method, Boolean withToken) {

        final RequestQueue queue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(method, RequestHelper.baseURL + uri, json, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("VOLLEY", response.toString());
                operator.op(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("accept", "application/json");
                headers.put("Content-Type", "application/json");
                if (withToken) {
                    headers.put("Authorization", "Bearer " + TokenHelper.getToken(context));
                }
                return headers;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);

    }

}
