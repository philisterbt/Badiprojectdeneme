package com.example.badiproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.badiproject.databinding.ActivityEventBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class Event extends AppCompatActivity {

    private ActivityEventBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;
    ArrayList<infoEvent> infoEventArrayList;
    infoEventAdapter infoEventAdapter;
    String kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        infoEventArrayList = new ArrayList<>();
        Intent intent = getIntent();
        kategori = intent.getStringExtra("kategori");

        getData();
        binding.recylerView.setLayoutManager(new LinearLayoutManager(this));
        infoEventAdapter = new infoEventAdapter(infoEventArrayList);
        binding.recylerView.setAdapter(infoEventAdapter);







    }

    public void goevent(View view){
        Intent intent=new Intent(Event.this,detaileventt.class);
        startActivity(intent);



    }



        public void addevent (View view){

            Intent intent = new Intent(Event.this, createEvent.class);
            intent.putExtra("kategori", kategori);

            startActivity(intent);
            finish();

        }

        public  void getData(){
            firebaseFirestore.collection(kategori)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if (error != null) {
                                Toast.makeText(Event.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                            if (value != null) {
                                for (DocumentSnapshot snapshot : value.getDocuments()) {
                                    Map<String, Object> data = snapshot.getData();
                                    String eventTitle = (String) data.get("title");
                                    String eventdate=(String) data.get("date");

                                    infoEvent infoEvent = new infoEvent(eventTitle,eventdate);
                                    infoEventArrayList.add(infoEvent);
                                }
                                infoEventAdapter.notifyDataSetChanged();
                            }
                        }
                    });




        }



}
