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

public class CustomAdapter3 extends RecyclerView.Adapter<CustomAdapter3.CustomViewHolder> {
    List<Groups> adapterList;
    String[] groupname = new String[100];
    String[] groupdesc = new String[100];
    String[] grouplocation = new String[100];
    String[] ginterest = new String[100];

    int img;


    public CustomAdapter3(List<Groups> list, int image) {
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
    public CustomAdapter3.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int I) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.grouplayout2, viewGroup, false);
        CustomAdapter3.CustomViewHolder itemViewHolder = new CustomAdapter3.CustomViewHolder(itemView);


        return itemViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull CustomAdapter3.CustomViewHolder holder, int i) {
        final int i2 = i;
        holder.g_name.setText(groupname[i]);
        holder.g_desc.setText(groupdesc[i]);


        holder.img2.setImageResource(img);


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), editGroup.class);
                intent.putExtra("groupname", groupname[i2]);
                view.getContext().startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), deleteGroup.class);
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
        Button edit;
        Button delete;

        ImageView img2;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            g_name = itemView.findViewById(R.id.groupsname);
            g_desc = itemView.findViewById(R.id.groupsdesc);
            edit = (Button) itemView.findViewById(R.id.groupEdit);
            delete= (Button) itemView.findViewById(R.id.groupDelete);

            img2 = itemView.findViewById(R.id.imageView4);
            // customTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);



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
