package ch.teko.michael.wgapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import ch.teko.michael.wgapp.api.RequestHelper;
import ch.teko.michael.wgapp.api.TokenHelper;

/**
 * Created by Michael on 10.09.2017.
 */

public class PurchaseCloseActivity extends AppCompatActivity {

    private Button buttonClose;
    private EditText editTextItemAmount;

    private Context context;
    private Integer purchaseId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchaseclose);

        context = getApplicationContext();

        Intent intent = getIntent();
        purchaseId = intent.getIntExtra("ID", 0);

        buttonClose = (Button) findViewById(R.id.buttonClosePurchase);
        editTextItemAmount = (EditText) findViewById(R.id.editTextPurchaseCost);

        // Add Item On Click Listener
        buttonClose.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Integer userId = TokenHelper.getSub(context);
                        Double amount = Double.parseDouble(editTextItemAmount.getText().toString());

                        try {
                            JSONObject jsonBody = new JSONObject();
                            jsonBody.put("price", amount);
                            jsonBody.put("payed", true);
                            jsonBody.put("userId", userId);

                            RequestHelper.put(context, "/slips/" + purchaseId, jsonBody, (JSONObject jsonObject) -> {
                                Log.i("json", jsonObject.toString());

                                Intent gotoScreenVar = new Intent(context, PurchaseActivity.class);
                                gotoScreenVar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(gotoScreenVar);
                            });
                        } catch (JSONException e) {
                            e.getStackTrace();
                        }
                    }
                }
        );
    }
}
