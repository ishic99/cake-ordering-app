package com.mad.toffels.Financial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mad.toffels.MainActivity;
import com.mad.toffels.R;

public class Thankyou extends AppCompatActivity {

    Button thnks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);

        thnks = findViewById(R.id.thanks);
        thnks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thanks = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(thanks);
            }
        });
    }
}