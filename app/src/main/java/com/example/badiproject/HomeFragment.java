package com.example.badiproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView ders = view.findViewById(R.id.ders);
        TextView eglence=view.findViewById(R.id.eglence);
        TextView spor=view.findViewById(R.id.spor);
        TextView sanat=view.findViewById(R.id.sanat);
        TextView seyahat=view.findViewById(R.id.seyahat);
        ders.setOnClickListener(this::ders);
        spor.setOnClickListener(this::spor);
        eglence.setOnClickListener(this::eglence);
        sanat.setOnClickListener(this::sanat);
        seyahat.setOnClickListener(this::seyahat);

        return view;
    }




    public void ders(View view){
        Intent intent=new Intent(getActivity(),Event.class);
        intent.putExtra("kategori","ders");
        startActivity(intent);



    }

    public void  spor(View view){
        Intent intent=new Intent(getActivity(),Event.class);
        intent.putExtra("kategori","spor");
        startActivity(intent);



    }

    public void  seyahat(View view){
        Intent intent=new Intent(getActivity(),Event.class);
        intent.putExtra("kategori","seyehat");
        startActivity(intent);



    }
    public void  sanat(View view){
        Intent intent=new Intent(getActivity(),Event.class);
        intent.putExtra("kategori","sanat");
        startActivity(intent);



    }
    public void  eglence(View view){
        Intent intent=new Intent(getActivity(),Event.class);
        intent.putExtra("kategori","eglence");
        startActivity(intent);



    }





}