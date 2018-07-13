package com.example.firebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class Signup extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout nameS,emailS,passS,cont,gen;
    Button signUpS, cancel;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //getActionBar().setTitle("Create new Account.");


        emailS=findViewById(R.id.signup_textLay_Email);
        nameS=findViewById(R.id.signup_textLay_name);
        passS=findViewById(R.id.signup_textLay_pass);
        cont=findViewById(R.id.signup_textLay_contact);
        gen=findViewById(R.id.signup_textLay_gender);


        signUpS=findViewById(R.id.signup_btn_signup);
        cancel=findViewById(R.id.signup_btn_cancel);
        auth=FirebaseAuth.getInstance();


        signUpS.setOnClickListener(this);





    }

    @Override
    public void onClick(View view) {
        String name=nameS.getEditText().getText().toString();
        String email=emailS.getEditText().getText().toString();
        String pass=passS.getEditText().getText().toString();
        String Cont=cont.getEditText().getText().toString();
        String Gen=gen.getEditText().getText().toString();


        if(view==signUpS){
            if(pass.equals(findViewById(R.id.signup_textLay_cPass).toString())){
                auth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess( AuthResult authResult) {
                       User user=new User(nameS.getEditText().getText().toString(),emailS.getEditText().getText().toString(),passS.getEditText().getText().toString(),cont.getEditText().getText().toString(),gen.getEditText().getText().toString(),authResult.getUser().getUid());
                        FirebaseFirestore firestore =FirebaseFirestore.getInstance();

                        firestore.collection("Users").document(user.getUid()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Signup.this,"User Added SuccessFully.",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Signup.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Signup.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });


            }
            else {
                Toast.makeText(Signup.this,"Pass not same",Toast.LENGTH_LONG).show();
                return;
            }


        }
        if(view==cancel)
        {
            finish();
        }




    }
}
