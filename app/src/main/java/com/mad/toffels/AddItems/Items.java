
package com.mad.toffels.AddItems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mad.toffels.R;

public class Items extends AppCompatActivity {

    Button AddPro1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        AddPro1 = findViewById(R.id.AddPro1);

        AddPro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nm = new Intent(getApplicationContext(),addItem.class);
                startActivity(nm);
            }
        });
    }
}