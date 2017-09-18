package ch.teko.michael.wgapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import ch.teko.michael.wgapp.api.RequestHelper;
import ch.teko.michael.wgapp.model.Settle;

/**
 * Created by Michael on 10.09.2017.
 */

public class StatementofcostsActivity extends AppCompatActivity {

    private Context context;
    private List<Settle> settelsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statementofcosts);

        context = getApplicationContext();


    }

    @Override
    protected void onResume() {
        this.loadSettles();

        super.onResume();
    }

    private void loadSettles() {
        RequestHelper.getAll(context, "/settles", (jsonArray -> {
            Log.i("settles", jsonArray.toString());

            settelsList = Settle.fromArray(jsonArray);
        }));
    }
}
