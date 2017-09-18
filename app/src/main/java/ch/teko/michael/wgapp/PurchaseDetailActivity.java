package ch.teko.michael.wgapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import ch.teko.michael.wgapp.api.RequestHelper;
import ch.teko.michael.wgapp.model.PurchaseItem;

/**
 * Created by Michael on 10.09.2017.
 */

public class PurchaseDetailActivity extends AppCompatActivity {

    private Button buttonAddItem;
    private Button buttonClosePurchase;

    private RecyclerView recyclerView;
    private PurchaseDetailAdapter purchaseDetailAdapter;
    private List<PurchaseItem> purchaseItemList;

    private Context context;
    private Integer purchaseId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchasedetail);

        Intent intent = getIntent();
        purchaseId = intent.getIntExtra("ID", 0);

        context = getApplicationContext();


        buttonAddItem = (Button) findViewById(R.id.buttonAddItem);
        buttonClosePurchase = (Button) findViewById(R.id.buttonClosePurchase);

        purchaseItemList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewPurchaseDetails);

        purchaseDetailAdapter = new PurchaseDetailAdapter(purchaseItemList, context, purchaseId);
        RecyclerView.LayoutManager purchaseLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(purchaseLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(purchaseDetailAdapter);

        this.loadSlipItems();

        buttonAddItem.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {

                        Intent i = new Intent(getBaseContext(), PurchaseAddItem.class);
                        i.putExtra("ID", purchaseId);
                        startActivity(i);

                    }
                }

        );

        buttonClosePurchase.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {

                        Intent i = new Intent(getBaseContext(), PurchaseCloseActivity.class);
                        startActivity(i);


                    }
                }

        );
    }

    private void loadSlipItems() {
        // Get all slips
        RequestHelper.getAll(context, "/slips/" + purchaseId + "/items", (jsonArray -> {
            Log.i("slip-items", jsonArray.toString());

            purchaseDetailAdapter.swapData(PurchaseItem.fromArray(jsonArray));
        }));
    }
}
