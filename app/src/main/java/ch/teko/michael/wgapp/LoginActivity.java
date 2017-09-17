package ch.teko.michael.wgapp;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Build;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private Button buttonCancel;
    private EditText edittextUsername;
    private EditText edittextPassword;

    private String username;
    private String password;

    public static final String LOGIN_URL = "https://wg-app.scapp.io/api";

    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


    buttonLogin = (Button) findViewById(R.id.buttonLogin);
    buttonCancel = (Button) findViewById(R.id.buttonCancel);

        edittextUsername = (EditText) findViewById(R.id.editTextUsername);
        edittextPassword = (EditText) findViewById(R.id.editTextPassword);




buttonLogin.setOnClickListener(
        new View.OnClickListener(){
            public void onClick(View v) {



                username = edittextUsername.getText().toString().trim();
                password = edittextPassword.getText().toString().trim();



                Map<String, String> params = new HashMap<>();
                params.put(KEY_USERNAME,username);//put your parameters here
                params.put(KEY_PASSWORD,password);

                Custom_Volly_Request jsObjRequest = new Custom_Volly_Request(
                        Request.Method.POST, LOGIN_URL, params,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("Response: ", response.toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError response)
                            {
                                Log.d("Response: Error", response.toString());
                            }
                        }
                );




            }
        }
);
        buttonCancel.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {

                        finish();

                    }


                    });
                }

}







