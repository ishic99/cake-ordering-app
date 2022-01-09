package com.mad.toffels.ShoppingCart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad.toffels.OrderConfiremation;
import com.mad.toffels.R;
import com.mad.toffels.ShoppingCart.DataBASE.SHHandler;

import java.util.List;

public class EditPurchase extends AppCompatActivity {

    EditText qty,address,total;
    Button search,buy,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_purchase);

        qty = findViewById(R.id.SSD1);
        address = findViewById(R.id.Nimal2);
        total = findViewById(R.id.Nimal4);

        search = findViewById(R.id.SSD);
        buy = findViewById(R.id.Nima9);
        delete = findViewById(R.id.Nimal10);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SHHandler shHandler = new SHHandler(getApplicationContext());
                List qtys = shHandler.read(qty.getText().toString());

                if(qtys.isEmpty()){
                    Toast.makeText(EditPurchase.this, "No Purchase", Toast.LENGTH_SHORT).show(); //toast message for empty details
                    qty.setText(null);
                }
                else{
                    Toast.makeText(EditPurchase.this, "Order Details Found !    User Found: "+qtys.get(0).toString(),
                            Toast.LENGTH_SHORT).show(); //toast message for successfully found details
                    qty.setText(qtys.get(0).toString());
                    address.setText(qtys.get(1).toString());
                    total.setText(qtys.get(2).toString());

                }
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SHHandler shHandler = new SHHandler(getApplicationContext());
                Boolean stat = shHandler.update(qty.getText().toString(),address.getText().toString(),total.getText().toString());
                if(stat){
                    Toast.makeText(EditPurchase.this, "PURCHASE UPDATED !", Toast.LENGTH_SHORT).show();
                    qty.setText(null);
                    address.setText(null);
                    total.setText(null);

                }
                else {
                    Toast.makeText(EditPurchase.this, "PURCHASE NOT UPDATED", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SHHandler shHandler = new SHHandler(getApplicationContext());
                shHandler.deleteINFO(qty.getText().toString());
                Toast.makeText(EditPurchase.this, "Purchase Deleted !", Toast.LENGTH_SHORT).show();
                qty.setText(null);
                address.setText(null);
                total.setText(null);

            }
        });
    }
}