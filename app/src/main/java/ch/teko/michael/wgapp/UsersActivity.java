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
import ch.teko.michael.wgapp.model.Purchase;
import ch.teko.michael.wgapp.model.User;

/**
 * Created by Michael on 10.09.2017.
 */

public class UsersActivity extends AppCompatActivity{


    private List<User> userList;
    private Button addUserButton;
    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;
    private String activityModeUser;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        getSupportActionBar().show();
        setTitle(R.string.titleUsers);

        addUserButton = (Button) findViewById(R.id.buttonAddUser);

        final Context context = getApplicationContext();

        userList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewUsers);
        usersAdapter = new UsersAdapter(userList);
        RecyclerView.LayoutManager purchaseLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(purchaseLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(usersAdapter);

        //Get users
        RequestHelper.getAll(context, "/users", (jsonArray -> {
            Log.i("users", jsonArray.toString());

            usersAdapter.swapData(User.fromArray(jsonArray));
        }));



        addUserButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {

                        activityModeUser = "add";

                        Intent i = new Intent(getBaseContext(), EditUserActivity.class);
                        i.putExtra("activityModeAddEditUser", activityModeUser);
                        startActivity(i);

                    }
                }
        );

    }
}
