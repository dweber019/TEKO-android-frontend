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

import java.util.List;

import ch.teko.michael.wgapp.api.RequestHelper;
import ch.teko.michael.wgapp.model.Purchase;
import ch.teko.michael.wgapp.model.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {


    private List<User> userList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewUsername;
        public ImageView imageViewDeleteUser;

        public MyViewHolder(View view) {
            super(view);
            textViewUsername = (TextView) view.findViewById(R.id.textviewUsernameSingleRow);
            imageViewDeleteUser = (ImageView) view.findViewById(R.id.imageViewUserSingleRowDelete);
        }
    }

    public UsersAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_single_row, parent, false);

        return new MyViewHolder(itemView);
    }


    public void reloadData() {
        // Get all slips
        RequestHelper.getAll(context, "/users", (jsonArray -> {
            Log.i("users", jsonArray.toString());

            this.swapData(User.fromArray(jsonArray));
        }));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final User users = userList.get(position);
        holder.textViewUsername.setText(users.email);
        holder.imageViewDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestHelper.delete(context, "/users/" + users.id, (jsonArray -> {
                    Log.i("users", jsonArray.toString());

                }));



            }
        });

        holder.textViewUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("user", users.toString());
                Intent i = new Intent(context, EditUserActivity.class);
                i.putExtra("activityModeAddEditUser","edit");
                i.putExtra("userMail", users.email );
                i.putExtra("userID", users.id);
                context.startActivity(i);
            }
        });

    }

    public void swapData(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}