package ch.teko.michael.wgapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ch.teko.michael.wgapp.api.TokenHelper;

/**
 * Created by Michael on 04.09.2017.
 */

public class MenuActivity extends AppCompatActivity {


    private Button buttonPurchase;
    private Button buttonStatementOfCost;
    private Button buttonUsers;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().show();

        setTitle(R.string.titleMenueBar);


        buttonPurchase = (Button) findViewById(R.id.buttonPurchase);
        buttonStatementOfCost = (Button) findViewById(R.id.buttonStatementOfCosts);
        buttonUsers = (Button) findViewById(R.id.buttonUsers);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);



        buttonPurchase.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {

                        Intent i = new Intent(getBaseContext(), PurchaseActivity.class);
                        startActivity(i);

                    }
                }
        );

        buttonStatementOfCost.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {

                        Intent i = new Intent(getBaseContext(), StatementofcostsActivity.class);
                        startActivity(i);

                    }
                }
        );

        buttonUsers.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {

                        Intent i = new Intent(getBaseContext(), UsersActivity.class);
                        startActivity(i);

                    }
                }
        );

        buttonLogout.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {

                        TokenHelper.removeToken(getBaseContext());

                        Intent i = new Intent(getBaseContext(), LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                    }
                }
        );


    }
}
