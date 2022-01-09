package com.mad.toffels.AddItems;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad.toffels.AddItems.Database.ITMHandler;
import com.mad.toffels.OrderConfiremation;
import com.mad.toffels.OrderDetails;
import com.mad.toffels.R;

public class addItem extends AppCompatActivity {

    Button Check , AddSave;
    EditText itemcode,cakename,weight,sheap,flavour,price;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Check = findViewById(R.id.Check);
        AddSave = findViewById(R.id.AddSave);
        itemcode = findViewById(R.id.AddX1);
        cakename = findViewById(R.id.AddX2);
        weight  = findViewById(R.id.AddX3);
        sheap = findViewById(R.id.AddX4);
        flavour = findViewById(R.id.AddX5);
        price = findViewById(R.id.AddX6);

        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent xx = new Intent(getApplicationContext(),EditItem.class);
                startActivity(xx);
            }
        });

        AddSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(cakename.getText())){
                    cakename.setError("Enter Cake Name!"); //check whether pickup date is entered
                    cakename.requestFocus();}
                else if(TextUtils.isEmpty(weight.getText())){
                    weight.setError("Enter weight!"); //check whether weight is entered
                    weight.requestFocus(); }
                else if(TextUtils.isEmpty(price.getText())){
                    price.setError("Enter price!"); //check whether price is entered
                    price.requestFocus(); }

                else{

                    ITMHandler itmHandler = new ITMHandler(getApplicationContext());
                    long newID = itmHandler.addInfo(itemcode.getText().toString(),cakename.getText().toString(),
                            weight.getText().toString(),sheap.getText().toString(),flavour.getText().toString(),price.getText().toString());
                    Toast.makeText(addItem.this, "Item Added Successfully !    Item ID: " + newID,
                            Toast.LENGTH_SHORT).show(); //success toast message with reservation ID

                    Intent i123 = new Intent(getApplicationContext(),EditItem.class);
                    startActivity(i123);

                    itemcode.setText(null);
                    cakename.setText(null);
                    weight.setText(null);
                    sheap.setText(null);
                    flavour.setText(null);
                    price.setText(null);
                }

            }
        });


    }
}