package com.example.ebatpricols;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button entry = findViewById(R.id.entry);
        Button registry = findViewById(R.id.registry);

        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentEntry fragmentEntry = new FragmentEntry();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragmentEntry);
                ft.commit();
            }
        });

        registry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentReg fragmentReg = new FragmentReg();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragmentReg);
                ft.commit();
            }
        });
    }
}
