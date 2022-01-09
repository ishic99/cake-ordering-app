package com.mad.toffels.AddItems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad.toffels.AddItems.Database.ITMHandler;
import com.mad.toffels.OrderConfiremation;
import com.mad.toffels.R;

import java.util.List;

public class EditItem extends AppCompatActivity {

    Button SearchX1,EditSave,DeleteYY;
    EditText Itemsearch,cakeNAME,WEIGHT,SHEAP,FLAVOUR,PRICE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        SearchX1 = findViewById(R.id.SearchX1);
        EditSave = findViewById(R.id.EditSave);
        DeleteYY = findViewById(R.id.DeleteYY);

        Itemsearch = findViewById(R.id.Itemsearch);
        cakeNAME = findViewById(R.id.AddY2);
        WEIGHT = findViewById(R.id.AddY3);
        SHEAP = findViewById(R.id.AddY4);
        FLAVOUR = findViewById(R.id.AddY5);
        PRICE = findViewById(R.id.AddY6);

        SearchX1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ITMHandler itmHandler = new ITMHandler(getApplicationContext());
                List itemsearch = itmHandler.readAllinfo(Itemsearch.getText().toString());

                if (itemsearch.isEmpty()){ //check whether phone number is Empty
                    Toast.makeText(EditItem.this, "No Item", Toast.LENGTH_SHORT).show(); //toast message for empty details
                    Itemsearch.setText(null);
                }
                else {

                    Toast.makeText(EditItem.this, "Order Details Found !    User Found: "+itemsearch.get(0).toString(),
                            Toast.LENGTH_SHORT).show(); //toast message for successfully found details
                    Itemsearch.setText(itemsearch.get(0).toString());
                    cakeNAME.setText(itemsearch.get(1).toString());
                    WEIGHT.setText(itemsearch.get(2).toString());
                    SHEAP.setText(itemsearch.get(3).toString());
                    FLAVOUR.setText(itemsearch.get(4).toString());
                    PRICE.setText(itemsearch.get(5).toString());




                }
            }
        });

        EditSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ITMHandler itmHandler = new ITMHandler(getApplicationContext());
                Boolean status1 = itmHandler.updateInfo(Itemsearch.getText().toString(),cakeNAME.getText().toString(),
                        WEIGHT.getText().toString(),SHEAP.getText().toString(),FLAVOUR.getText().toString(),PRICE.getText().toString());
                if(status1){
                    Toast.makeText(EditItem.this, "Data UPDATED !", Toast.LENGTH_SHORT).show();
                    Itemsearch.setText(null);
                    cakeNAME.setText(null);
                    WEIGHT.setText(null);
                    SHEAP.setText(null);
                    FLAVOUR.setText(null);
                    PRICE.setText(null);
                }
                else{
                    Toast.makeText(EditItem.this, "Data NOT UPDATED !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        DeleteYY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ITMHandler itmHandler = new ITMHandler(getApplicationContext());
                itmHandler.deleteinfo(Itemsearch.getText().toString());

                Toast.makeText(EditItem.this, "Item Deleted !", Toast.LENGTH_SHORT).show();
                Itemsearch.setText(null);
                cakeNAME.setText(null);
                WEIGHT.setText(null);
                SHEAP.setText(null);
                FLAVOUR.setText(null);
                PRICE.setText(null);

            }
        });
    }
}