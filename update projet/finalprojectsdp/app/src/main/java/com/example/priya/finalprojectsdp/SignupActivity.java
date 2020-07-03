package com.example.priya.finalprojectsdp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignupActivity extends AppCompatActivity {


    private EditText inputEmail, inputPassword, inputName, inputAddress, inputRepass, inputMobile,acnt_no;
    private Button btnSignIn, btnSignUp, btnResetPassword;

    private Button buttonForRegister;
    private EditText  acnt_id;
    private ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
//       FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("Registration");



        buttonForRegister = findViewById(R.id.sign_up_button);
        btnSignIn = findViewById(R.id.sign_in_button);
        inputName = findViewById(R.id.name);
        inputAddress = findViewById(R.id.input_address);
        inputMobile = findViewById(R.id.input_mobile);
        inputPassword = findViewById(R.id.input_password);
        inputRepass = findViewById(R.id.input_reEnterPassword);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonForRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {



                        String address = inputAddress.getText().toString();
                        String name = inputName.getText().toString();
                        String mobile = inputMobile.getText().toString();
                        String pass = inputPassword.getText().toString();
                        String repass = inputRepass.getText().toString();

                        if (name.equals("") | address.equals("") |  mobile.equals("") | pass.equals("") | repass.equals("")) {
                            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                        }

                        else {

                            if (dataSnapshot.child(inputMobile.getText().toString()).exists()) {
                                Toast.makeText(SignupActivity.this, "Exist", Toast.LENGTH_SHORT).show();
                            } else {
                                Registration_Class reg = new Registration_Class(inputName.getText().toString(), inputAddress.getText().toString(), inputPassword.getText().toString(),
                                        inputRepass.getText().toString());
                                ref.child(inputMobile.getText().toString()).setValue(reg);
                                Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                            }
                        }





                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

        });
    }
    public static String EncodeString(String string) {
        return string.replace(".", ",");
    }



/*
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
*/

}


