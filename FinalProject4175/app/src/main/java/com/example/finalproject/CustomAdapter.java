package com.example.finalproject;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>  {
    List<Event> adapterList;
    String[] name = new String[100];
    String[] gname = new String[100];
    String[] desc = new String[100];
    String[] location = new String[100];
    String[] date = new String[100];
    String[] time = new String[100];
    int img;



    public CustomAdapter(List<Event> adapterList, int image) {
        this.adapterList = adapterList;
        int i = 0;
        for (Event e : adapterList) {
            name[i] = e.getEventName();
            gname[i] = e.getGroupName();
            desc[i] = e.getEventDesc();
            location[i] = e.getEventLocation();
            date[i] = e.getDateOfEvent();
            time[i] = e.getTimeOfEvent();
            i++;
        }

        img = image;


    }
    public void changeData(List<Event> adapterList, int image) {
        this.adapterList = adapterList;
        int i = 0;
        for (Event e : adapterList) {
            name[i] = e.getEventName();
            desc[i] = e.getEventDesc();
            location[i] = e.getEventLocation();
            date[i] = e.getDateOfEvent();
            gname[i] = e.getGroupName();
            time[i] = e.getTimeOfEvent();
            i++;
        }

        img = image;

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int I) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.layoutdetail, viewGroup, false);
        CustomViewHolder itemViewHolder = new CustomViewHolder(itemView);


        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int i) {

        holder.eventName.setText(name[i]);
        holder.eventDesc.setText(desc[i]);
        holder.eventLocation.setText(location[i]);
        holder.eventDates.setText(date[i]);
        holder.groupName.setText(gname[i]);
        holder.logoImage.setImageResource(img);
        holder.eventTime.setText(time[i]);
    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView eventName;
        TextView eventDesc;
        TextView eventLocation;
        TextView eventDates;
        ImageView logoImage;
        TextView groupName;
        TextView eventTime;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            eventName = itemView.findViewById(R.id.eventName);
            eventDesc = itemView.findViewById(R.id.eventDesc);
            eventLocation = itemView.findViewById(R.id.eventLocation);
            eventDates = itemView.findViewById(R.id.eventDate);
            logoImage = itemView.findViewById(R.id.imageView);
            groupName = itemView.findViewById(R.id.groupName);
            eventTime = itemView.findViewById(R.id.time);
           // customTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);

           eventName.setOnClickListener(this);
            eventDesc.setOnClickListener(this);
            eventLocation.setOnClickListener(this);
            eventDates.setOnClickListener(this);
            groupName.setOnClickListener(this);
            logoImage.setOnClickListener(this);
            eventTime.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {

        }
    }




}