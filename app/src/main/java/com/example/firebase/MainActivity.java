package com.example.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    FirebaseAuth auth;
    TextInputLayout email, pass;
    Button signup, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        signup=findViewById(R.id.signup);
        login=findViewById(R.id.login);
        auth=FirebaseAuth.getInstance();

        signup.setOnClickListener(this);
        login.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        String Email=email.getEditText().getText().toString();
        String Pass=pass.getEditText().getText().toString();
        if(view==login)
        {
            auth.signInWithEmailAndPassword(Email,Pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(MainActivity.this,authResult.getUser().getDisplayName(), Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull final Exception e) {
                    Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();

                }
            });

        }
        else if(view==signup)
        {
           Intent intent=new Intent(MainActivity.this,Signup.class);
           startActivity(intent);
        }
    }
}
