package ch.teko.michael.wgapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Michael on 10.09.2017.
 */

public class EditUserActivity extends AppCompatActivity {


    Button buttonAddEditButton;
    EditText editTextMail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edituser);
        String activityMode = getIntent().getStringExtra("activityModeAddEditUser");
        String editUserMail = getIntent().getStringExtra("userMail");
        String editUserID = getIntent().getStringExtra("userID");


        buttonAddEditButton = (Button) findViewById(R.id.buttonAddEditUser);
        editTextMail = (EditText) findViewById(R.id.editTextMail);

        if (activityMode.equals("add")) {

            buttonAddEditButton.setText("Create User");

        }


        if (activityMode.equals("edit")) {


            buttonAddEditButton.setText("Edit User");
            editTextMail.setText(editUserMail);
            editTextMail.setEnabled(false);
        }


        buttonAddEditButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {


                    }
                }
        );
    }
}






