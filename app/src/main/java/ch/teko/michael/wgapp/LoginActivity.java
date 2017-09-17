package ch.teko.michael.wgapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ch.teko.michael.wgapp.Authentication.Custom_Volly_Request;

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







