package com.example.ebatpricols;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity implements AddItem.OnOnItemAddedListener{
    private ListView list;
    private String fileName = "environment_data.txt";
    private ArrayList<String[]> array;
    private MyAdapter adapter;
    private String userRole;
    private FrameLayout addItemFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addItemFragment = findViewById(R.id.addItemFragment);

        //Показ списка
        list = findViewById(R.id.listView);
        FileManager fileManager = new FileManager(this);

        if(!fileManager.isFileValid(fileName)) {
            fileManager.writeDataToFile(fileName);
        }

        array = fileManager.readDataFromFile(fileName);

        adapter = new MyAdapter(this, array);
        list.setAdapter(adapter);

        userRole = getIntent().getStringExtra("user_role");

        //показ верхней понели
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.home);
        setSupportActionBar(toolbar);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, Text_Content_Activity.class);
                intent.putExtra("object_info", array.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if("редактор".equals(userRole))
            getMenuInflater().inflate(R.menu.toolbar_menu_for_redactor, menu);
        else
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
        else if(id == R.id.addItem) {
            Toast.makeText(this, "Добавление элемента", Toast.LENGTH_SHORT).show();
            // Показываем фрагмент
            AddItem addItem = new AddItem();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.addItemFragment, addItem);
            ft.addToBackStack(null);
            ft.commit();
            addItemFragment.setVisibility(View.VISIBLE);

        }
        return true;
    }

    @Override
    public void onItemAdded(String name, String objectType, String coordinates, String controlParameters, String measurementMethod) {
        String[] newItem = {name, objectType, coordinates, controlParameters, measurementMethod};
        array.add(newItem);
        adapter.notifyDataSetChanged();

        FileManager fileManager = new FileManager(this);
        fileManager.addRecordToFile(fileName, newItem);

        addItemFragment.setVisibility(View.GONE);
        closeKeyboard();
    }

    private void closeKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}