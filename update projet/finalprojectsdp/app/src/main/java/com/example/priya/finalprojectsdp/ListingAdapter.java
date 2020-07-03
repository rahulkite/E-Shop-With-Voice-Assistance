package com.example.priya.finalprojectsdp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListingAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Registration_Class> users;
    //Button button;

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
        holder.phone.setText(user.getMobile());

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