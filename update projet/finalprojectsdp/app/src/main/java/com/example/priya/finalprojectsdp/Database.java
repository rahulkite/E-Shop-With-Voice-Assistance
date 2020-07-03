package com.example.priya.finalprojectsdp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


        import android.content.Intent;
        import android.support.constraint.solver.widgets.Snapshot;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.ListAdapter;
        import android.widget.Toast;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;
        import java.util.List;

public class Database extends AppCompatActivity {

    public static final String Name = "Name";
    public static final String Email = "Email";


    ListView listView;
    FirebaseDatabase database;
    DatabaseReference reference;
    List<Registration_Class> registration_classlist;
    ArrayList<String> list;
    ArrayAdapter<Registration_Class> adapter;


    Registration_Class registration_class;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Registration");

        // registration_class=new Registration_Class();
        listView = findViewById(R.id.list_view_id);
        registration_classlist = new ArrayList<>();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Registration_Class registration_class = registration_classlist.get(position);


                String no = reference.getKey();

                Intent intent = new Intent(getApplicationContext(),ShowUsers.class);
                intent.putExtra(Name, registration_class.getName());



                startActivity(intent);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                registration_classlist.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    registration_class=ds.getValue(Registration_Class.class);
                    registration_classlist.add(registration_class);
                   // list.add(registration_class.getId()+ "   " + registration_class.getName()+ "     " + registration_class.getEmail());
                }
                RegisterListClass adapter = new RegisterListClass(Database.this,registration_classlist);
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
