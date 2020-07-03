package com.example.priya.finalprojectsdp;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.priya.finalprojectsdp.Common.Food;
import com.example.priya.finalprojectsdp.Common.Order;
import com.example.priya.finalprojectsdp.database.database;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductDetail extends AppCompatActivity {
    TextView product_name,product_price,product_description;
    ImageView product_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;
    String productId ="";

    FirebaseDatabase database;
    DatabaseReference products;

    Food currentProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        database = FirebaseDatabase.getInstance();
        products = database.getReference("Products");

        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart = (FloatingActionButton)findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new database(getBaseContext()).addToCart(new Order(
                    productId,
                    currentProduct.getName(),
                    numberButton.getNumber(),
                    currentProduct.getPrice(),
                    currentProduct.getDiscount()


                ));
                Toast.makeText(ProductDetail.this,"Added to Cart",Toast.LENGTH_SHORT).show();
            }
        });
        product_description = (TextView) findViewById(R.id.food_description);
        product_name = (TextView)findViewById(R.id.food_name);
        product_price = (TextView) findViewById(R.id.food_price);
        product_image = (ImageView) findViewById(R.id.img_food);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);


        if(getIntent() != null)
            productId = getIntent().getStringExtra("ProductId");

        if(!productId.isEmpty())
        {
            getDetailFood(productId);
        }

    }

    private void getDetailFood(String productId) {
        products.child(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentProduct= dataSnapshot.getValue(Food.class);

                Picasso.with(getBaseContext()).load(currentProduct.getImage())
                            .into(product_image);
                collapsingToolbarLayout.setTitle(currentProduct.getName());
                product_price.setText(currentProduct.getPrice());
                product_name.setText(currentProduct.getName());
                product_description.setText(currentProduct.getDescription());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
