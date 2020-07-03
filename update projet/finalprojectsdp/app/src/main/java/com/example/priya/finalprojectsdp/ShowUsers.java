package com.example.priya.finalprojectsdp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
/**
 * Created by coderzpassion on 31/07/16.
 */
public class ShowUsers extends AppCompatActivity {
    ListView allusers;
    ProgressDialog mProgressDialog;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    ListingAdapter adapter;
    ArrayList<Registration_Class> users=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        allusers=(ListView)findViewById(R.id.allusers);
        adapter=new ListingAdapter(ShowUsers.this,users);
        allusers.setAdapter(adapter);
        getDataFromServer();
        //String name = findViewById(R.id.user_fullname);
    }
    // getting the data from UserNode at Firebase and then adding the users in Arraylist and setting it to Listview
    public void getDataFromServer()
    {
        showProgressDialog();
        databaseReference.child("Registration").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                    {
                        Registration_Class user=postSnapShot.getValue(Registration_Class.class);
                        users.add(user);
                        adapter.notifyDataSetChanged();
                    }
                }
                hideProgressDialog();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                hideProgressDialog();
            }
        });
    }
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(ShowUsers.this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }
    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    private class ListingAdapter extends BaseAdapter {
        Context context;
        LayoutInflater layoutInflater;
        ArrayList<Registration_Class> users;
        public ListingAdapter(Context con, ArrayList<Registration_Class> users)
        {
            context=con;
            layoutInflater = LayoutInflater.from(context);
            this.users=users;
        }
        @Override
        public int getCount() {
            return users.size();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.activity_listing_adapter, null, false);
                holder = new ViewHolder();
                holder.fullname = (TextView) convertView.findViewById(R.id.user_fullname);

                holder.phone = (TextView) convertView.findViewById(R.id.user_phone);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Registration_Class user=users.get(position);
            holder.fullname.setText(user.getName());




            //holder.email.setText(user.getEmail());
            return convertView;
        }
        public class ViewHolder {
            TextView fullname, phone;
        }
        @Override
        public Object getItem(int position) {
            return users.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
    }
}
