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
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.teko.michael.wgapp.api.RequestHelper;
import ch.teko.michael.wgapp.api.TokenHelper;
import ch.teko.michael.wgapp.model.Settle;

public class StatementofcostsAdapter extends RecyclerView.Adapter<StatementofcostsAdapter.MyViewHolder> {

    private List<Settle> settleList;
    private Context context;
    private Boolean ownsYou;

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


    public StatementofcostsAdapter(List<Settle> settleList, Context context, Boolean ownsYou) {
        this.settleList = settleList;
        this.context = context;
        this.ownsYou = ownsYou;
        this.reloadData();
    }

    public void reloadData() {
        // Get all slips
        RequestHelper.getAll(context, "/settles", (jsonArray -> {
            Log.i("settles", jsonArray.toString());

            this.swapData(Settle.fromArray(jsonArray));
        }));
    }

    public void swapData(List<Settle> settleList) {

        ArrayList<Settle> settleListNew = new ArrayList<>();
        Integer currentUserId = TokenHelper.getSub(context);

        if (this.ownsYou) {
            for (Settle settle : settleList) {
                if (settle.userLent.id == currentUserId) {
                    settleListNew.add(settle);
                }
            }
        } else {
            for (Settle settle : settleList) {
                if (settle.userOwns.id == currentUserId) {
                    settleListNew.add(settle);
                }
            }
        }

        this.settleList = settleListNew;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View settleView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.statementofcosts_single_row, parent, false);

        return new MyViewHolder(settleView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Settle settles = settleList.get(position);
        holder.textviewStatementofCostsPersonName.setText(ownsYou ? settles.userOwns.name : settles.userLent.name);
        holder.textviewStatementofCostsAmount.setText(Double.toString(settles.amount));
        holder.imageViewStatementofCostsSolved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("click", settles.id.toString());

                try {
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("payed", true);

                    RequestHelper.put(context, "/settles/" + settles.id, jsonBody, (JSONObject jsonObject) -> {
                        Log.i("json", jsonObject.toString());

                        reloadData();
                    });
                } catch (JSONException e) {
                    e.getStackTrace();
                }
            }
        });
    };

    @Override
    public int getItemCount() {
        return settleList.size();
    }
}