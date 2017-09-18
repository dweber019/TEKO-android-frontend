package ch.teko.michael.wgapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.teko.michael.wgapp.api.RequestHelper;
import ch.teko.michael.wgapp.model.Purchase;

/**
 * Created by Michael on 10.09.2017.
 */

public class PurchaseActivity extends AppCompatActivity {

    private Button addPurchase;
    private RecyclerView recyclerView;
    private PurchaseAdapter purchaseAdapter;
    private List<Purchase> purchaseList;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);

        getSupportActionBar().show();
        setTitle(R.string.titlePurchases);

        context = getApplicationContext();

        purchaseList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewPurchases);

        purchaseAdapter = new PurchaseAdapter(purchaseList, context);
        RecyclerView.LayoutManager purchaseLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(purchaseLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(purchaseAdapter);

        addPurchase = (Button) findViewById(R.id.buttonAddPurchase);

        addPurchase.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {

                        RequestHelper.post(context, "/slips", null, jsonObject -> {
                            Log.i("slips", jsonObject.toString());

                            loadSlips();

                            Purchase p = Purchase.fromObject(jsonObject);

                            Intent i = new Intent(getBaseContext(), PurchaseDetailActivity.class);
                            i.putExtra("ID", p.id);
                            startActivity(i);
                        });

                    }
                }
        );
    }

    @Override
    protected void onResume() {
        this.loadSlips();

        super.onResume();
    }

    private void loadSlips() {
        // Get all slips
        RequestHelper.getAll(context, "/slips", (jsonArray -> {
            Log.i("slips", jsonArray.toString());

            purchaseAdapter.swapData(Purchase.fromArray(jsonArray));
        }));
    }


}