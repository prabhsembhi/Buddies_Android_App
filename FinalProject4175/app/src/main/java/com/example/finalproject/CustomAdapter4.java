package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter4 extends RecyclerView.Adapter<CustomAdapter4.CustomViewHolder> {


    List<User> adapterList;
    String[] username = new String[100];
    String[] useremail = new String[100];


    int img;


    public CustomAdapter4(List<User> list) {
        adapterList = list;
        int i = 0;
        for (User u : adapterList) {
            username[i] = u.getFullName();
            useremail[i] = u.getUserEmail();
            i++;
        }


    }
    public void changeData(List<User> list) {
        adapterList = list;
        int i = 0;
        for (User u : adapterList) {
            username[i] = u.getFullName();
            useremail[i] = u.getUserEmail();
            i++;
        }

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CustomAdapter4.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int I) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.layoutforuser, viewGroup, false);
        CustomAdapter4.CustomViewHolder itemViewHolder = new CustomAdapter4.CustomViewHolder(itemView);


        return itemViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull CustomAdapter4.CustomViewHolder holder, int i) {
        final int i2 = i;
        holder.username1.setText(username[i]);
        holder.useremail1.setText(useremail[i]);



    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       TextView username1;
       TextView useremail1;

        ImageView img2;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            useremail1 = itemView.findViewById(R.id.tvAdmin1);
            username1 = itemView.findViewById(R.id.tvAdmin2);



        }

        @Override
        public void onClick(View v) {

        }

    }
}
