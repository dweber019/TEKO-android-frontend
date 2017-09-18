package ch.teko.michael.wgapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.teko.michael.wgapp.model.Settle;

/**
 * Created by Michael on 10.09.2017.
 */

public class StatementofcostsActivity extends AppCompatActivity {

    private TextView textViewPersonOwesYou;
    private TextView textViewYouOwesPerson;
    private RecyclerView recyclerViewPersonOwesYou;
    private RecyclerView recyclerViewYouOwesPerson;
    private List<Settle>settleList;
    private StatementofcostsAdapter statementofcostsPersonOwesYouAdapter;
    private StatementofcostsAdapter statementofcostsYouOwesPersonAdapter;
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statementofcosts);


        settleList = new ArrayList<>();
        settleList.add(new Settle(0,"Michael","David", "10.10.2017" , false, 12.19));

        //List Person Owes You

        recyclerViewPersonOwesYou = (RecyclerView) findViewById(R.id.recyclerviewStatementofcostPersonOwesYou);
        statementofcostsPersonOwesYouAdapter = new StatementofcostsAdapter(settleList, context);
        RecyclerView.LayoutManager statementofcostsPersonOwesYouManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPersonOwesYou.setLayoutManager(statementofcostsPersonOwesYouManager);
        recyclerViewPersonOwesYou.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPersonOwesYou.setAdapter(statementofcostsPersonOwesYouAdapter);




        //List You Owes Person

        recyclerViewYouOwesPerson = (RecyclerView) findViewById(R.id.recyclerviewStatementofcostYouOwesPerson);
        statementofcostsYouOwesPersonAdapter = new StatementofcostsAdapter(settleList, context);
        RecyclerView.LayoutManager statementofcostsYouOwesPersonManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewYouOwesPerson.setLayoutManager(statementofcostsYouOwesPersonManager);
        recyclerViewYouOwesPerson.setItemAnimator(new DefaultItemAnimator());
        recyclerViewYouOwesPerson.setAdapter(statementofcostsYouOwesPersonAdapter);




    }





}
