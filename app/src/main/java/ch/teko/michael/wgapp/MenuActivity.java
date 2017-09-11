package ch.teko.michael.wgapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Michael on 04.09.2017.
 */

public class MenuActivity extends AppCompatActivity {


    private Button buttonPurchase;
    private Button buttonStatementOfCost;
    private Button buttonUsers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().show();

        setTitle(R.string.titleMenueBar);


        buttonPurchase = (Button) findViewById(R.id.buttonPurchase);
        buttonStatementOfCost = (Button) findViewById(R.id.buttonStatementOfCosts);
        buttonUsers = (Button) findViewById(R.id.buttonUsers);



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


    }
}
