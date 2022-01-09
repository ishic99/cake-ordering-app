package com.mad.toffels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad.toffels.AddItems.Items;
import com.mad.toffels.Financial.AddPayment;
import com.mad.toffels.ShoppingCart.ViewItems;

public class MainActivity extends AppCompatActivity {

    Button LGbtn,reg;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LGbtn = findViewById(R.id.LGbtn);
//        username = findViewById(R.id.username);
//        password = findViewById(R.id.password);
        reg = findViewById(R.id.r1);


        LGbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), OrderDetails.class);
//                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
//                    intent.putExtra("isAdmin", true);
//                } else {
//                    intent.putExtra("isAdmin", false);
//                }
                startActivity(intent);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mm = new Intent(getApplicationContext(), Items.class);
                startActivity(mm);

            }
        });


    }

    public void image(View view) {
        Intent vvb = new Intent(getApplicationContext(), ViewItems.class);
        startActivity(vvb);
    }
}