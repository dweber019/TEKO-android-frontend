package ch.teko.michael.wgapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private Button buttonCancel;
    private EditText edittextEmail;
    private EditText edittextPassword;

    private String email;
    private String password;

    public static final String LOGIN_URL = "https://wg-app.scapp.io/api";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);

        edittextEmail = (EditText) findViewById(R.id.editTextEmail);
        edittextPassword = (EditText) findViewById(R.id.editTextPassword);

        final Context context = getApplicationContext();

        if (TokenHelper.isTokenValid(context)) {
            Intent intent = new Intent(context, MenuActivity.class);
            startActivity(intent);
        }

        buttonLogin.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {


                        email = edittextEmail.getText().toString().trim();
                        password = edittextPassword.getText().toString().trim();

                        Log.i("Login:", "Email: " + email + ", Password: " + password);

                        try {
                            JSONObject jsonBody = new JSONObject();
                            jsonBody.put("email", email);
                            jsonBody.put("password", password);

                            RequestHelper.login(context, "/login", jsonBody, (JSONObject jsonObject) -> {
                                try {
                                    Log.i("json", jsonObject.toString());
                                    TokenHelper.saveToken(context, jsonObject.getString("token"));
                                    Intent intent = new Intent(context, MenuActivity.class);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.getStackTrace();
                                }
                            });
                        } catch (JSONException e) {
                            e.getStackTrace();
                        }

                    }
                }
        );
        buttonCancel.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

}







