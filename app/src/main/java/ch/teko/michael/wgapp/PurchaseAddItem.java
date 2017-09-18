package ch.teko.michael.wgapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ch.teko.michael.wgapp.api.RequestHelper;
import ch.teko.michael.wgapp.model.Purchase;
import ch.teko.michael.wgapp.model.PurchaseItem;

/**
 * Created by Michael on 10.09.2017.
 */

public class PurchaseAddItem extends AppCompatActivity {


    private Button buttonSaveItems;
    private EditText editTextItemDescription;
    private Spinner spinnerItemSelection;

    private String itemSelection;
    private String itemDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchaseadditem);


        buttonSaveItems = (Button) findViewById(R.id.buttonAddItemSave);
        editTextItemDescription = (EditText) findViewById(R.id.editTextItemDescription);
        spinnerItemSelection = (Spinner)findViewById(R.id.spinnerItemSelection);



        //create a list of items for the spinner.

        List<PurchaseItem> itemList = new ArrayList<>();
        itemList.add(new PurchaseItem(0,"Test","Test Description"));
        itemList.add(new PurchaseItem(0,"Test2","Test Description"));

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<PurchaseItem> adapter = new ArrayAdapter<PurchaseItem>(this, android.R.layout.simple_spinner_dropdown_item, itemList );
        //set the spinners adapter to the previously created one.
        spinnerItemSelection.setAdapter(adapter);

        // Add Item On Click Listener
        buttonSaveItems.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {


                    itemSelection = spinnerItemSelection.getSelectedItem().toString();
                        itemDescription = editTextItemDescription.getText().toString();




                    }
                }
        );



    }
}
