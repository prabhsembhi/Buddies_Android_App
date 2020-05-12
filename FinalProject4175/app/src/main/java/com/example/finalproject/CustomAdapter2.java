package com.example.finalproject;

import android.content.Intent;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.CustomViewHolder> {
    List<Groups> adapterList;
    String[] groupname = new String[100];
    String[] groupdesc = new String[100];
    String[] grouplocation = new String[100];
    String[] ginterest = new String[100];

    int img;


    public CustomAdapter2(List<Groups> list, int image) {
        adapterList = list;
        int i = 0;
        for (Groups g : adapterList) {
            groupname[i] = g.getGroupName();
            groupdesc[i] = g.getGroupDesc();
            grouplocation[i] = g.getGroupLocation();
            ginterest[i] = g.getInterest();

            i++;
        }

        img = image;

    }
    public void changeData(List<Groups> list, int image) {
        adapterList = list;
        int i = 0;
        for (Groups g : adapterList) {
            groupname[i] = g.getGroupName();
            groupdesc[i] = g.getGroupDesc();
            grouplocation[i] = g.getGroupLocation();
            ginterest[i] = g.getInterest();

            i++;
        }

        img = image;

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CustomAdapter2.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int I) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.grouplayout, viewGroup, false);
        CustomAdapter2.CustomViewHolder itemViewHolder = new CustomAdapter2.CustomViewHolder(itemView);


        return itemViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull CustomAdapter2.CustomViewHolder holder, int i) {
        final int i2 = i;
        holder.g_name.setText(groupname[i]);
        holder.g_desc.setText(groupdesc[i]);

        holder.g_location.setText(grouplocation[i]);
        holder.g_interest.setText(ginterest[i]);
        holder.img2.setImageResource(img);

        holder.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), chatPage.class);
                intent.putExtra("groupname", groupname[i2]);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView g_name;
        TextView g_desc;
        TextView g_location;
        TextView g_interest;
        Button chat;

        ImageView img2;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            g_name = itemView.findViewById(R.id.g_name);
            g_desc = itemView.findViewById(R.id.g_desc);
            g_location = itemView.findViewById(R.id.g_location);
            g_interest = itemView.findViewById(R.id.g_interest);

            img2 = itemView.findViewById(R.id.imageView2);
            chat = (Button) itemView.findViewById(R.id.chat);
            // customTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            g_name.setOnClickListener(this);
            g_desc.setOnClickListener(this);
            g_location.setOnClickListener(this);
            g_interest.setOnClickListener(this);

            img2.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            int position = getLayoutPosition();
            String SL = groupname[position];

            Intent intent = new Intent(v.getContext(), groupDetail.class);
            intent.putExtra("SL", SL);
            v.getContext().startActivity(intent);

        }
    }

}
