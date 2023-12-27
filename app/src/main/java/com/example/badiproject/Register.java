package com.example.badiproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.badiproject.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private FirebaseAuth auth;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        db=FirebaseFirestore.getInstance();

    }

    public void register(View view) {
        String firstname=binding.firstname.getText().toString();
        String surname=binding.surname.getText().toString();
        String Email=binding.Email.getText().toString();
        String password=binding.password.getText().toString();
        String username=binding.username.getText().toString();

        if (firstname.equals("")||surname.equals("")||Email.equals("")||password.equals("")||username.equals("")){
            Toast.makeText(this, "You must fill every space.", Toast.LENGTH_SHORT).show();
        }else {

            auth.createUserWithEmailAndPassword(Email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    Map<String, Object> user= new HashMap<>();
                    user.put("First Name",firstname);
                    user.put("Surname",surname);
                    user.put("Email",Email);
                    user.put("Username",username);
                    user.put("password",password);

                    db.collection("user")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(Register.this, "Succesful", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                    Intent intenttomain=new Intent(Register.this,MainActivity.class);
                    startActivity(intenttomain);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Register.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }

    public void alreadyhave(View view){
        Intent intent=new Intent(Register.this,MainActivity.class);
        startActivity(intent);
        finish();




    }

}