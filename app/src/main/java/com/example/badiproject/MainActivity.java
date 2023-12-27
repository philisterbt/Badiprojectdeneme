package com.example.badiproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.badiproject.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();

        if (user!=null){
            Intent intent=new Intent(MainActivity.this,FragmentPage.class);
            startActivity(intent);
            finish();
        }

    }


    public void Login(View view){

        String mail=binding.email.getText().toString();
        String passaword=binding.password.getText().toString();
        if (mail.equals("")||passaword.equals("")){
            Toast.makeText(this, "Write Email or Passaword", Toast.LENGTH_SHORT).show();
        }else {
            auth.signInWithEmailAndPassword(mail,passaword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,FragmentPage.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    public void alreadyhave(View view){
        Intent intent=new Intent(MainActivity.this,Register.class);
        startActivity(intent);
        finish();



    }
}