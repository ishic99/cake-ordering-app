package com.mad.toffels.ShoppingCart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.toffels.OrderDetails;
import com.mad.toffels.R;
import com.mad.toffels.ShoppingCart.DataBASE.SHHandler;

public class MyCart extends AppCompatActivity {

    EditText qty,address;
    TextView price,total;
    Button cal,purchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        qty = findViewById(R.id.OC2);
        price = findViewById(R.id.OC1);
        total = findViewById(R.id.OC4);
        cal = findViewById(R.id.bbnn);
        address = findViewById(R.id.OC);
        purchase = findViewById(R.id.purchas);


        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number1 = Integer.parseInt(price.getText().toString());
                int number2 = Integer.parseInt(qty.getText().toString());
                int sum = number1 * number2;
                total.setText("Total is :"+ String.valueOf(sum));
            }
        });

        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(qty.getText())){
                    qty.setError("Enter Qty!"); //check whether pickup date is entered
                    qty.requestFocus();}
                else if(TextUtils.isEmpty(address.getText())){
                    address.setError("Enter Address!"); //check whether return date is entered
                    address.requestFocus(); }
                else{

                    SHHandler shHandler = new SHHandler(getApplicationContext());
                    long newID = shHandler.add(qty.getText().toString(),address.getText().toString(),total.getText().toString());
                    Toast.makeText(MyCart.this, "Purchase Successfully......!    Purchase ID: " + newID, Toast.LENGTH_SHORT).show(); //success toast message with reservation ID

                    Intent asf = new Intent(getApplicationContext(),EditPurchase.class);
                    startActivity(asf);

                    qty.setText(null);
                    address.setText(null);
                    total.setText(null);
                }

            }
        });
    }
}