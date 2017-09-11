package ch.teko.michael.wgapp;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


    buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);


buttonLogin.setOnClickListener(
        new View.OnClickListener(){
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), MenuActivity.class);
                startActivity(i);

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







