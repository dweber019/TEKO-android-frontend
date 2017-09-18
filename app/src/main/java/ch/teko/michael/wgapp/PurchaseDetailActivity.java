package ch.teko.michael.wgapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import ch.teko.michael.wgapp.model.PurchaseItem;

/**
 * Created by Michael on 10.09.2017.
 */

public class PurchaseDetailActivity extends AppCompatActivity{

    private Button buttonAddItem;
    private Button buttonClosePurchase;

    private RecyclerView recyclerView;
    private PurchaseDetailAdapter purchaseDetailAdapter;
    private List<PurchaseItem> purchaseItemList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchasedetail);

    buttonAddItem = (Button) findViewById(R.id.buttonAddItem);
    buttonClosePurchase = (Button) findViewById(R.id.buttonClosePurchase);








        purchaseItemList = new ArrayList<>();

        purchaseItemList.add(new PurchaseItem("Zucker"));
        purchaseItemList.add(new PurchaseItem("Fleisch"));
        purchaseItemList.add(new PurchaseItem("Zucker"));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewPurchaseDetails);

        purchaseDetailAdapter = new PurchaseDetailAdapter(purchaseItemList);
        RecyclerView.LayoutManager purchaseLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(purchaseLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(purchaseDetailAdapter);



        buttonAddItem.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {

                        Intent i = new Intent(getBaseContext(), PurchaseAddItem.class);
                        startActivity(i);

                    }
                }

        );

        buttonClosePurchase.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {

                        Intent i = new Intent(getBaseContext(), PurchaseCloseActivity.class);
                        startActivity(i);


                    }
                }

        );


    }
}
