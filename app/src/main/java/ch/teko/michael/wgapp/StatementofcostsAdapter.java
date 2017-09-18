package ch.teko.michael.wgapp;

/**
 * Created by Michael on 11.09.2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.teko.michael.wgapp.model.Settle;

public class StatementofcostsAdapter extends RecyclerView.Adapter<StatementofcostsAdapter.MyViewHolder> {



    private List<Settle> settleList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textviewStatementofCostsPersonName;
        public TextView textviewStatementofCostsAmount;
        private ImageView imageViewStatementofCostsSolved;

        public MyViewHolder(View view) {
            super(view);
            textviewStatementofCostsPersonName = (TextView) view.findViewById(R.id.textviewStatementofCostsPersonName);
            textviewStatementofCostsAmount = (TextView) view.findViewById(R.id.textviewStatementofCostsAmount);
            imageViewStatementofCostsSolved = (ImageView) view.findViewById(R.id.imageViewStatementofCostSolved);
        }
    }


    public StatementofcostsAdapter(List<Settle> settleList, Context context) {
        this.settleList = settleList;
        this.context = context;
    }

    /*
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
    */

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View settleView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.statementofcosts_single_row, parent, false);

        return new MyViewHolder(settleView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Settle settles = settleList.get(position);
        holder.textviewStatementofCostsPersonName.setText(settles.userLent);
        holder.textviewStatementofCostsAmount.setText(Double.toString(settles.price));
        holder.imageViewStatementofCostsSolved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    };

    /*
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Test","Item " + items.id);

                Intent i = new Intent(context, PurchaseDetailActivity.class);
                i.putExtra("ID", items.id);
                context.startActivity(i);
            }
        });

*/



    @Override
    public int getItemCount() {
        return settleList.size();
    }
}