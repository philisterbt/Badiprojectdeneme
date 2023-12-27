package com.example.badiproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.badiproject.databinding.ActivityDetaileventtBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class detaileventt extends AppCompatActivity {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView locationTextView;
    private TextView dateTextView;

    private ActivityDetaileventtBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;
    String kategori;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaileventt);


        }







    }


