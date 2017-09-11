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

public class PurchaseDetailActivity extends AppCompatActivity{

    private Button buttonAddItem;
    private Button buttonSavePurchase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchasedetail);

    buttonAddItem = (Button) findViewById(R.id.buttonAddItem);
        buttonSavePurchase = (Button) findViewById(R.id.buttonSavePurchase);

        buttonAddItem.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {

                        Intent i = new Intent(getBaseContext(), PurchaseAddItem.class);
                        startActivity(i);

                    }
                }

        );

        buttonSavePurchase.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {



                    }
                }

        );


    }
}
