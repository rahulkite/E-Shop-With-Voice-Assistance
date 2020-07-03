package com.example.priya.finalprojectsdp;


import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.priya.finalprojectsdp.Common.Common;
import com.example.priya.finalprojectsdp.Common.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText account_no, pass;
    private Button loginButton, SignUp;
    private TextView attemptText;

    long l_login;
    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference("Registration");

        account_no = findViewById(R.id.student_account_no_id);
        pass = findViewById(R.id.password);
        loginButton = findViewById(R.id.btn_login);

        SignUp = findViewById(R.id.btn_signup);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if((account_no.getText().toString().equals("01679357283"))&&(pass.getText().toString().equals("tahmina123")))
                        {
                            Toast.makeText(getApplicationContext(), "Successful",Toast.LENGTH_SHORT).show();
                            //Intent intent= new Intent(LoginActivity.this,Database.class);
                            Intent intent= new Intent(LoginActivity.this,AdminPage.class);
                            startActivity(intent);

                        }

                        if(dataSnapshot.child(account_no.getText().toString()).exists())
                        {
                            Registration_Class log = dataSnapshot.child(account_no.getText().toString()).getValue(Registration_Class.class);
                            log.setMobile(account_no.getText().toString());

                            if(log.getPassword().equals(pass.getText().toString()))
                            {
                                Toast.makeText(getApplicationContext(), "Successful",Toast.LENGTH_SHORT).show();
                                //Intent intent= new Intent(LoginActivity.this,Database.class);
                                Intent intent= new Intent(LoginActivity.this,Home.class);
                                Common.current_user= log;

                                startActivity(intent);
                            }

                            else
                            {
                                Toast.makeText(getApplicationContext(), "Wrong",Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Doesn't Exist!",Toast.LENGTH_SHORT).show();

                        }





                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });





    }

}

