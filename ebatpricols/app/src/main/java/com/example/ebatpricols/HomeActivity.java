package com.example.ebatpricols;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {
    private ListView list;
    private String[] array;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        //Показ списка
        list = findViewById(R.id.listView);
        array = getResources().getStringArray(R.array.cities);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(Arrays.asList(array)));
        list.setAdapter(adapter);

        //показ верхней понели
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.home);
        setSupportActionBar(toolbar);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, Text_Content_Activity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.home) {
            Toast.makeText(this, "Список", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.instructions) {
            Toast.makeText(this, "Инструкция", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, InstructionActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.program) {
            Toast.makeText(this, "О программе", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ProgramActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.author) {
            Toast.makeText(this, "Об авторе", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, AuthorActivity.class);
            startActivity(intent);
        }
        return true;
    }
}