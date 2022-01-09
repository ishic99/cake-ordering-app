package com.mad.toffels.ShoppingCart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mad.toffels.R;

public class ViewItems extends AppCompatActivity {

    Button vtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        vtn1 = findViewById(R.id.vtn1);
        vtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent xxxxx = new Intent(getApplicationContext(),MyCart.class);
                startActivity(xxxxx);
            }
        });
    }
}