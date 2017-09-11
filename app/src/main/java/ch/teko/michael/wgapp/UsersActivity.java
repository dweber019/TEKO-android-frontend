package ch.teko.michael.wgapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Michael on 10.09.2017.
 */

public class UsersActivity extends AppCompatActivity{

    private Button addUserButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        getSupportActionBar().show();
        setTitle(R.string.titleUsers);

        addUserButton = (Button) findViewById(R.id.buttonAddUser);

        addUserButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {

                        Intent i = new Intent(getBaseContext(), EditUserActivity.class);
                        startActivity(i);

                    }
                }
        );

    }
}
