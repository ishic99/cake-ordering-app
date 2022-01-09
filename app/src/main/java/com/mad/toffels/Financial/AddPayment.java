package com.mad.toffels.Financial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
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

import com.mad.toffels.Financial.DataBase.PDBHandler;
import com.mad.toffels.R;

import java.util.Calendar;

public class AddPayment extends AppCompatActivity {

    private static final String TAG = "OrderDetails";

    EditText holdername,cardnum,expiredate,cvvnum;
    Button add,update;
    private DatePickerDialog.OnDateSetListener DataSetListner8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        holdername = findViewById(R.id.payText);
        cardnum = findViewById(R.id.payText2);
        expiredate= findViewById(R.id.payText3);
        cvvnum = findViewById(R.id.payText4);
        update = findViewById(R.id.PaymentBtn1);

        add = findViewById(R.id.PaymentBtn);
//        update = findViewById(R.id.PaymentBtn1);


        expiredate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddPayment.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DataSetListner8,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        DataSetListner8 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                Log.d(TAG, "OnDateSet:mm/dd/yyyy:" + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                expiredate.setText(date);
            }
        };

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(cardnum.getText())){
                    cardnum.setError("Enter Card Number!"); //check whether pickup date is entered
                    cardnum.requestFocus();}
                else if(TextUtils.isEmpty(expiredate.getText())){
                    expiredate.setError("Enter Expiredate Date!"); //check whether return date is entered
                    expiredate.requestFocus(); }
                else if(TextUtils.isEmpty(cvvnum.getText())){
                    cvvnum.setError("Enter cvv Number!"); //check whether cvv is entered
                    cvvnum.requestFocus(); }
                else{

                    PDBHandler pdbHandler = new PDBHandler(getApplicationContext());
                    long newID = pdbHandler.addInfo(holdername.getText().toString(),cardnum.getText().toString(),
                            expiredate.getText().toString(),cvvnum.getText().toString());
                    Toast.makeText(AddPayment.this, "Payment Successfully......!    Payment ID: " + newID,
                            Toast.LENGTH_SHORT).show(); //success toast message with reservation ID

                    Intent xzc = new Intent(getApplicationContext(), EditPayment.class);
                    startActivity(xzc);

                    holdername.setText(null);
                    cardnum.setText(null);
                    expiredate.setText(null);
                    cvvnum.setText(null);
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kkj = new Intent(getApplicationContext(),EditPayment.class);
                startActivity(kkj);
            }
        });
    }
}