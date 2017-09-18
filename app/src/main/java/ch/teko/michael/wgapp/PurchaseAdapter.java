package ch.teko.michael.wgapp;

/**
 * Created by Michael on 11.09.2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ch.teko.michael.wgapp.api.RequestHelper;
import ch.teko.michael.wgapp.model.Purchase;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.MyViewHolder> {


    private ImageView imageViewPurchaseDelete;
    private List<Purchase> purchaseList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewPurchase;
        public ImageView imageViewDeletePurchase;

        public MyViewHolder(View view) {
            super(view);
            textViewPurchase = (TextView) view.findViewById(R.id.textviewPurchaseSingleRow);
            imageViewDeletePurchase = (ImageView) view.findViewById(R.id.imageViewPurchaseSingleRowDelete);
        }
    }


    public PurchaseAdapter(List<Purchase> purchaseList, Context context) {
        this.purchaseList = purchaseList;
        this.context = context;
    }

    public void reloadData() {
        // Get all slips
        RequestHelper.getAll(context, "/slips", (jsonArray -> {
            Log.i("slips", jsonArray.toString());

            this.swapData(Purchase.fromArray(jsonArray));
        }));
    }

    public void swapData(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.purchase_single_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Purchase items = purchaseList.get(position);
        holder.textViewPurchase.setText(items.date);
        holder.imageViewDeletePurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Test","Delete Button "+ items.date);

                RequestHelper.delete(context, "/slips/" + items.id, (jsonArray -> {
                    Log.i("slips", jsonArray.toString());

                    reloadData();
                }));
            }
        });

        holder.textViewPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Test","Item " + items.id);

                Intent i = new Intent(context, PurchaseDetailActivity.class);
                i.putExtra("ID", items.id);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return purchaseList.size();
    }
}