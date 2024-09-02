package com.example.ebatpricols;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText idName = findViewById(R.id.login);
        EditText idPas = findViewById(R.id.password);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = idName.getText().toString();
                String password = idPas.getText().toString();

                if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password))
                    Toast.makeText(MainActivity.this, "Введите данные", Toast.LENGTH_SHORT).show();
                else {
                    if (username.equals("user") && password.equals("123")) {
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }   
            }
        });
    }
}
