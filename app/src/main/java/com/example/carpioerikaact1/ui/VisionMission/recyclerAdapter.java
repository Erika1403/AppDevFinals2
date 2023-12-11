package com.example.carpioerikaact1.ui.VisionMission;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import  androidx.recyclerview.widget.RecyclerView;

import com.example.carpioerikaact1.R;

import java.util.ArrayList;
public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<VMTile> contents = new ArrayList<>();
    public recyclerAdapter(ArrayList<VMTile> contents){
        this.contents = contents;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTxt;
        public TextView conText;
        public MyViewHolder(final View view) {
            super(view);
            titleTxt = view.findViewById(R.id.txtTitle);
            conText = view.findViewById(R.id.txtContent);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_ctrl_vm, parent, false);
        return  new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String c = contents.get(position).getContent();
        String t = contents.get(position).getTitle();
        holder.titleTxt.setText(t);
        holder.conText.setText(c);
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
}
