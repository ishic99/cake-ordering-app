package com.mad.toffels.Financial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mad.toffels.Financial.DataBase.PDBHandler;
import com.mad.toffels.R;

import java.util.List;

public class EditPayment extends AppCompatActivity {

    EditText holdername, cardnum, exdate,cvvnum ;
    Button edit, delete, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_payment);

        holdername = findViewById(R.id.edholder); //cast card holder name
        cardnum = findViewById(R.id.edcnum); //cast card number
        exdate = findViewById(R.id.eddate); //cast expire date
        cvvnum =  findViewById(R.id.edcv); //cast cvv number
        edit = findViewById(R.id.edpbtn); //cast edit button
        delete = findViewById(R.id.pedbtn); //cast delete button
        search = findViewById(R.id.edpsearch); //cast search button

        //validate data from card holder name
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PDBHandler pdbHandler = new PDBHandler(getApplicationContext());
                List hname = pdbHandler.readAllInfo(holdername.getText().toString());

                if (hname.isEmpty()){
                    Toast.makeText(EditPayment.this, "No Details", Toast.LENGTH_SHORT).show();
                    holdername.setText(null);
                }
                else {

                    Toast.makeText(EditPayment.this, "Details Found! Details: "+hname.get(0).toString(), Toast.LENGTH_SHORT).show();
                    holdername.setText(hname.get(0).toString());
                    cardnum.setText(hname.get(1).toString());
                    exdate.setText(hname.get(2).toString());
                    cvvnum.setText(hname.get(3).toString());



                }

            }
        });


        //Update data method
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PDBHandler pdbHandler = new PDBHandler(getApplicationContext());

                Boolean status = pdbHandler.updateInfo(holdername.getText().toString(),cardnum.getText().toString(),exdate.getText().toString(),cvvnum.getText().toString());
                if (status){
                    Toast.makeText(EditPayment.this, "Details Updated", Toast.LENGTH_SHORT).show();
                    Intent gggg = new Intent(getApplicationContext(),Thankyou.class);
                    startActivity(gggg);
                }
                else {
                    Toast.makeText(EditPayment.this, "Update Failed", Toast.LENGTH_SHORT).show();

                }

            }
        });

        //Delete data method
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PDBHandler pdbHandler = new PDBHandler(getApplicationContext());
                pdbHandler.deleteInfo(holdername.getText().toString());

                Toast.makeText(EditPayment.this, "Payment Details Deleted", Toast.LENGTH_SHORT).show();

//                Intent i = new Intent(getApplicationContext(),ResConfirmation.class);
//                startActivity(i);

                holdername.setText(null);
                cardnum.setText(null);
                exdate.setText(null);
                cvvnum.setText(null);
            }
        });
    }
}