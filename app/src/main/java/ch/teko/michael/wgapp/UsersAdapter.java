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

import ch.teko.michael.wgapp.model.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {


    private List<User> userList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewUsername;
        public ImageView imageViewDeleteUser;

        public MyViewHolder(View view) {
            super(view);
            textViewUsername = (TextView) view.findViewById(R.id.textviewUsernameSingleRow);
            imageViewDeleteUser = (ImageView) view.findViewById(R.id.imageViewUserSingleRowDelete);


        }
    }

    public UsersAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_single_row, parent, false);

        return new MyViewHolder(itemView);
    }

    Context context;

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final User users = userList.get(position);
        holder.textViewUsername.setText(users.getUsername());
        holder.imageViewDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Test",users.getUsername());

            }
        });

        holder.textViewUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Test",users.getUsername());
            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}