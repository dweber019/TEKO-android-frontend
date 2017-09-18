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

import ch.teko.michael.wgapp.api.RequestHelper;
import ch.teko.michael.wgapp.model.PurchaseItem;

public class PurchaseDetailAdapter extends RecyclerView.Adapter<PurchaseDetailAdapter.MyViewHolder> {


    private ImageView imageViewPurchaseDelete;
    private List<PurchaseItem> purchaseItemList;
    private Context context;
    private Integer purchaseId;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewPurchaseItem;
        public ImageView imageViewDeletePurchaseItem;
        public TextView textViewPurchaseItemDescription;

        public MyViewHolder(View view) {
            super(view);
            textViewPurchaseItem = (TextView) view.findViewById(R.id.textviewPurchaseDetailSingleRow);
            imageViewDeletePurchaseItem = (ImageView) view.findViewById(R.id.imageViewPurchaseDetailSingleRowDelete);
            textViewPurchaseItemDescription = (TextView) view.findViewById(R.id.textviewPurchaseDetailDescriptionSingleRow);
        }
    }

    public PurchaseDetailAdapter(List<PurchaseItem> purchaseItemList, Context context, Integer purchaseId) {
        this.purchaseItemList = purchaseItemList;
        this.context = context;
        this.purchaseId = purchaseId;
    }

    public void reloadData() {
        // Get all slips
        RequestHelper.getAll(context, "/slips/" + purchaseId + "/items", (jsonArray -> {
            Log.i("slips", jsonArray.toString());

            this.swapData(PurchaseItem.fromArray(jsonArray));
        }));
    }

    public void swapData(List<PurchaseItem> purchaseList) {
        this.purchaseItemList = purchaseList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.purchasedetail_single_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PurchaseItem items = purchaseItemList.get(position);

        holder.textViewPurchaseItem.setText(items.name);
        holder.textViewPurchaseItemDescription.setText(items.description);

        holder.imageViewDeletePurchaseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Test","Delete Button "+ items.name);

                RequestHelper.delete(context, "/slips/" + purchaseId + "/items/" + items.id, (jsonArray -> {
                    Log.i("slips-items", jsonArray.toString());

                    reloadData();
                }));

            }
        });
    }

    @Override
    public int getItemCount() {
        return purchaseItemList.size();
    }
}