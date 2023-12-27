package com.example.badiproject;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.badiproject.databinding.InfoeventBinding;

import java.util.ArrayList;

public class infoEventAdapter extends RecyclerView.Adapter<infoEventAdapter.PostHolder> {

    private ArrayList<infoEvent> arrayList;

    public infoEventAdapter(ArrayList<infoEvent> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InfoeventBinding infoeventBinding=InfoeventBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PostHolder(infoeventBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        holder.infoeventBinding.eventname.setText(arrayList.get(position).title);
        holder.infoeventBinding.dateevent.setText(arrayList.get(position).date);


    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{

        InfoeventBinding infoeventBinding;
        //ActivityCreateEventBinding activityCreateEventBinding;
        public PostHolder(InfoeventBinding infoeventBinding) {
            super(infoeventBinding.getRoot()) ;
            this.infoeventBinding=infoeventBinding;
        }
    }

}