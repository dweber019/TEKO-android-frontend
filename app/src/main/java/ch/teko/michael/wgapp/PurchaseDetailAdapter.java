package ch.teko.michael.wgapp;

/**
 * Created by Michael on 11.09.2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.teko.michael.wgapp.model.PurchaseItem;

public class PurchaseDetailAdapter extends RecyclerView.Adapter<PurchaseDetailAdapter.MyViewHolder> {


    private ImageView imageViewPurchaseDelete;
    private List<PurchaseItem> purchaseItemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewPurchaseItem;
        public ImageView imageViewDeletePurchaseItem;

        public MyViewHolder(View view) {
            super(view);
            textViewPurchaseItem = (TextView) view.findViewById(R.id.textviewPurchaseDetailSingleRow);
            imageViewDeletePurchaseItem = (ImageView) view.findViewById(R.id.imageViewPurchaseDetailSingleRowDelete);




        }
    }


    public PurchaseDetailAdapter(List<PurchaseItem> purchaseItemList) {
        this.purchaseItemList = purchaseItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.purchasedetail_single_row, parent, false);

        return new MyViewHolder(itemView);
    }

    Context context;

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PurchaseItem items = purchaseItemList.get(position);
        holder.textViewPurchaseItem.setText(items.getItemName());
        holder.imageViewDeletePurchaseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Test","Delete Button "+ items.getItemName());

            }
        });

        holder.textViewPurchaseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Test","Item " + items.getItemName());
            }
        });


    }

    @Override
    public int getItemCount() {
        return purchaseItemList.size();
    }
}