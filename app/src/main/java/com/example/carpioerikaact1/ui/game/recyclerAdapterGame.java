package com.example.carpioerikaact1.ui.game;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpioerikaact1.R;

import java.util.ArrayList;
import java.util.List;

public class recyclerAdapterGame extends RecyclerView.Adapter<recyclerAdapterGame.MyViewHolder>{
    private ArrayList<GuessTile> contents = new ArrayList<>();
    public recyclerAdapterGame(ArrayList<GuessTile> contents){
        this.contents = contents;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView status;
        public TextView info;
        public MyViewHolder(final View view) {
            super(view);
            status = view.findViewById(R.id.ivStat);
            info = view.findViewById(R.id.txtGuess);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.control_guesses, parent, false);
        Log.d("Adapter", "onCreateViewHolder called");
        return  new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String c = contents.get(position).getInfo();
        int num = contents.get(position).getNumber();
        String data = "";
        holder.info.setText(data);
        if(c.equals("Lower")){
            holder.status.setImageResource(R.drawable.baseline_arrow_circle_down_24);
            data = "Your Guess of " + Integer.toString(num) + " is " + c + " than the number";
        }
        else if(c.equals("Higher")){
            holder.status.setImageResource(R.drawable.baseline_arrow_circle_up_24);
            data = "Your Guess of " + Integer.toString(num) + " is " + c + " than the number";
        }
        else {
            holder.status.setImageResource(R.drawable.baseline_check_circle_outline_24);
            data = "Your Guess of " + Integer.toString(num) + " is " + c + "!";
        }
        holder.info.setText(data);
    }
    public void clear() {
        int size = contents.size();
        contents.clear();
        notifyItemRangeRemoved(0, size);
    }
    @Override
    public int getItemCount() {
        return contents.size();
    }
}
