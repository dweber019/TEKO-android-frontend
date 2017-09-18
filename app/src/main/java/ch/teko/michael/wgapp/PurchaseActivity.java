package ch.teko.michael.wgapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.pm.ActivityInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ch.teko.michael.wgapp.model.Purchase;

/**
 * Created by Michael on 10.09.2017.
 */

public class PurchaseActivity extends AppCompatActivity {

    private Button addPurchase;
    private RecyclerView recyclerView;
    private PurchaseAdapter purchaseAdapter;
    private List<Purchase> purchaseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);

        getSupportActionBar().show();
        setTitle(R.string.titlePurchases);


        purchaseList = new ArrayList<>();

        purchaseList.add(new Purchase("09.09.2017"));



        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewPurchases);

        purchaseAdapter = new PurchaseAdapter(purchaseList);
        RecyclerView.LayoutManager purchaseLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(purchaseLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(purchaseAdapter);



        addPurchase = (Button) findViewById(R.id.buttonAddPurchase);

        addPurchase.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {

                        Intent i = new Intent(getBaseContext(), PurchaseDetailActivity.class);
                        startActivity(i);

                    }
                }
        );



    }


}