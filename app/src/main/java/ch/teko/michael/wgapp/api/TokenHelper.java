package ch.teko.michael.wgapp.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.auth0.android.jwt.JWT;

/**
 * Created by tzhweda9 on 17.09.17.
 */

public class TokenHelper {

    private static String token = "TOKEN";

    public static void saveToken(Context context, String token) {
        SharedPreferences sharedPref = context.getSharedPreferences(TokenHelper.token, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(TokenHelper.token, token);
        editor.commit();
    }

    public static String getToken(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(TokenHelper.token, Context.MODE_PRIVATE);
        return sharedPref.getString(TokenHelper.token, "");
    }

    public static boolean isTokenValid(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(TokenHelper.token, Context.MODE_PRIVATE);
        String token = sharedPref.getString(TokenHelper.token, "");
        if (token == "") {
            return false;
        }

        JWT jwt = new JWT(token);
        return !jwt.isExpired(10);
    }

    public static Integer getSub(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(TokenHelper.token, Context.MODE_PRIVATE);
        String token = sharedPref.getString(TokenHelper.token, "");
        if (token == "") {
            return 0;
        }

        JWT jwt = new JWT(token);
        return Integer.parseInt(jwt.getSubject());
    }

}
