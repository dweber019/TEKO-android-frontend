package ch.teko.michael.wgapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import ch.teko.michael.wgapp.api.RequestHelper;

/**
 * Created by Michael on 10.09.2017.
 */

public class EditUserActivity extends AppCompatActivity {


    Button buttonAddEditButton;
    EditText editTextMail;
    EditText editTextName;
    EditText editTextPassword;
    EditText editTextPasswordConfirm;
    Context context;
    

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edituser);
        String activityMode = getIntent().getStringExtra("activityModeAddEditUser");
        String editUserMail = getIntent().getStringExtra("userMail");
        String editUserID = getIntent().getStringExtra("userID");

        this.context = getApplicationContext();

        editTextName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPasswordConfirm = (EditText) findViewById(R.id.editTextPasswordConfirm); 
        buttonAddEditButton = (Button) findViewById(R.id.buttonAddEditUser);
        editTextMail = (EditText) findViewById(R.id.editTextMail);

        if (activityMode.equals("add")) {

            buttonAddEditButton.setText("Create User");

            buttonAddEditButton.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {

                            if (! editTextName.getText().toString().equals("")) {
                                if (editTextMail.getText().toString().contains("@") && editTextMail.getText().toString().contains(".")) {
                                    if (editTextPassword.getText().toString().equals(editTextPasswordConfirm.getText().toString())) {
                                        if (editTextPassword.getText().toString().length() >= 8) {

                                            try {
                                                JSONObject jsonBody = new JSONObject();
                                                jsonBody.put("name", editTextName.getText().toString());
                                                jsonBody.put("email", editTextMail.getText().toString());
                                                jsonBody.put("password", editTextPassword.getText().toString());

                                                RequestHelper.post(context,"/users", jsonBody, (JSONObject jsonObject) ->  {
                                                    Log.i("json", jsonObject.toString());
                                                    finish();
                                                });
                                            } catch (JSONException e) {
                                                e.getStackTrace();

                                            }

                                        } else {
                                            Toast.makeText(context, "Password is too short. Minimum lentgh 8 characters", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
                                        Toast.makeText(context, "Password not equal", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(context, "No valid E-Mail adress", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(context, "No valid Name", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }

        if (activityMode.equals("edit")) {


            buttonAddEditButton.setText("Edit User");
            editTextMail.setText(editUserMail);
            editTextMail.setEnabled(false);

            buttonAddEditButton.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {

                            





                        }
                    }
            );
        }


    }}







