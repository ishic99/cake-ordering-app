package com.mad.toffels;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.mad.toffels.Database.DBHandler;
import com.mad.toffels.Financial.AddPayment;

import java.util.Calendar;
import java.util.List;

public class OrderConfiremation extends AppCompatActivity {
    private static final String TAG = "OrderDetails";
    Button search, save,delete,orderPlcace;
    EditText poneNo,userName,Code,email,DDate,TDate;
    private DatePickerDialog.OnDateSetListener DataSetListner5,DataSetListner6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confiremation);


        search = findViewById(R.id.Searchbtn1);
        save = findViewById(R.id.Orderbtn1);
        delete = findViewById(R.id.delete);
        poneNo = findViewById(R.id.Pone1);
        userName = findViewById(R.id.OC1);
        Code = findViewById(R.id.OC2);
        email = findViewById(R.id.OC4);
        DDate = findViewById(R.id.OC5);
        TDate = findViewById(R.id.OC6);

        DDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        OrderConfiremation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DataSetListner5,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        DataSetListner5 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                Log.d(TAG, "OnDateSet:mm/dd/yyyy:" + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                DDate.setText(date);


            }
        };

        TDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        OrderConfiremation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DataSetListner6,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        DataSetListner6 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                Log.d(TAG, "OnDateSet:mm/dd/yyyy:" + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                TDate.setText(date);


            }
        };

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List phonenumber = dbHandler.readAllInfo(poneNo.getText().toString()); //get data from reservation phone number

                if (phonenumber.isEmpty()){ //check whether phone number is Empty
                    Toast.makeText(OrderConfiremation.this, "No Order", Toast.LENGTH_SHORT).show(); //toast message for empty details
                    poneNo.setText(null);
                }
                else {

                    Toast.makeText(OrderConfiremation.this, "Order Details Found !  " +
                            "  User Found: "+phonenumber.get(0).toString(), Toast.LENGTH_SHORT).show(); //toast message for successfully found details
                    userName.setText(phonenumber.get(0).toString());
                    Code.setText(phonenumber.get(1).toString());
                    poneNo.setText(phonenumber.get(2).toString());
                    email.setText(phonenumber.get(3).toString());
                    DDate.setText(phonenumber.get(4).toString());
                    TDate.setText(phonenumber.get(5).toString());




                }

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                Boolean status = dbHandler.updateinfo(userName.getText().toString(),Code.getText().toString(),poneNo.getText().toString(),
                        email.toString().toString(),DDate.getText().toString(),TDate.toString().toString());
                if(status){
                    Toast.makeText(OrderConfiremation.this, "USER UPDATED !", Toast.LENGTH_SHORT).show();
                    Intent go = new Intent(getApplicationContext(), AddPayment.class);
                    startActivity(go);
                    userName.setText(null);
                    Code.setText(null);
                    poneNo.setText(null);
                    email.setText(null);
                    DDate.setText(null);
                    TDate.setText(null);

                }
                else{
                    Toast.makeText(OrderConfiremation.this, "USER NOT UPDATED !", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.deleteInfo(userName.getText().toString());

                Toast.makeText(OrderConfiremation.this, "User Deleted !", Toast.LENGTH_SHORT).show();
                userName.setText(null);
                Code.setText(null);
                poneNo.setText(null);
                email.setText(null);
                DDate.setText(null);
                TDate.setText(null);
            }
        });


    }
}