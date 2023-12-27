package com.example.badiproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.badiproject.databinding.ActivityCreateEventBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class createEvent extends AppCompatActivity {

    private ActivityCreateEventBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreateEventBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();




    }

    public void save(View view){


        String eventname=binding.eventname.getText().toString();
        String eventdescription=binding.eventdescription.getText().toString();
        String eventlocation=binding.eventlocation.getText().toString();
        String eventdate=binding.eventdate.getText().toString();

        if (eventname.equals("")||eventdescription.equals("")||eventlocation.equals("")){
            Toast.makeText(this, "You must fill every space.", Toast.LENGTH_SHORT).show();
        }else {

            Intent intent=getIntent();
            String kategori=intent.getStringExtra("kategori");


            CollectionReference events= db.collection(kategori);
            Map<String,Object> event=new HashMap<>();
            event.put("title",eventname);
            event.put("description",eventdescription);
            event.put("location",eventlocation);
            event.put("date",eventdate);


            events.add(event)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(createEvent.this, "Event Added Successfully", Toast.LENGTH_SHORT).show();


                            Intent intent=new Intent(createEvent.this,Event.class);
                            intent.putExtra("kategori",kategori);
                            startActivity(intent);
                            finish();



                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(createEvent.this, "Eror adding event :"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }



    }


}