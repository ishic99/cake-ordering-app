package com.mad.toffels;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.mad.toffels.Database.DBHandler;

import java.util.Calendar;

public class OrderDetails extends AppCompatActivity {

    private static final String TAG = "OrderDetails";
    EditText O5,O6,phone,username,email,OrderCode;
    Button Edit, save;
    private DatePickerDialog.OnDateSetListener DataSetListner3,DataSetListner4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        O6 = findViewById(R.id.O6);
        O5 = findViewById(R.id.O5);
        phone = findViewById(R.id.O3);
        username = findViewById(R.id.O1);
        email = findViewById(R.id.O4);
        OrderCode = findViewById(R.id.O2);

        Edit = findViewById(R.id.Orderbtn2);
        save = findViewById(R.id.Orderbtn1);

        O5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        OrderDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DataSetListner3,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        DataSetListner3 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                Log.d(TAG, "OnDateSet:mm/dd/yyyy:" + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                O5.setText(date);


            }
        };

        O6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        OrderDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DataSetListner4,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        DataSetListner4 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                Log.d(TAG, "OnDateSet:mm/dd/yyyy:" + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                O6.setText(date);


            }
        };

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(getApplicationContext(),OrderConfiremation.class);
                startActivity(ii);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(O5.getText())){
                    O5.setError("Enter Order Date!"); //check whether pickup date is entered
                    O5.requestFocus();}
                else if(TextUtils.isEmpty(O6.getText())){
                    O6.setError("Enter Time Date!"); //check whether return date is entered
                    O6.requestFocus(); }
                else if(TextUtils.isEmpty(phone.getText())){
                    phone.setError("Enter Your Phone Number!"); //check whether phone number is entered
                    phone.requestFocus(); }

                else {

                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    long newID = dbHandler.addinfo(username.getText().toString(), OrderCode.getText().toString(), phone.getText().toString(),
                            email.getText().toString(), O5.getText().toString(),O6.getText().toString());
                    Toast.makeText(OrderDetails.this, "Order Successfully......!    Order ID: " + newID,
                            Toast.LENGTH_SHORT).show(); //success toast message with reservation ID

                    Intent i = new Intent(getApplicationContext(), OrderConfiremation.class);
                    startActivity(i);
                    username.setText(null);
                    OrderCode.setText(null);
                    phone.setText(null);
                    email.setText(null);
                    O5.setText(null);
                    O6.setText(null);

                }

            }
        });
    }
}