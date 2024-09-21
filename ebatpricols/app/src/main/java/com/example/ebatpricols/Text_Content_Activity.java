package com.example.ebatpricols;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Text_Content_Activity extends AppCompatActivity {

    private TextView text_content1;
    private TextView text_content2;
    private TextView text_content3;
    private TextView text_content4;
    private TextView text_content5;
    private ImageView iContent;
    private Toolbar toolbar;
    private int position = 0;

    private String[] objectDetails;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_content);


        text_content1 = findViewById(R.id.main_text_12);
        text_content2 = findViewById(R.id.main_text_22);
        text_content3 = findViewById(R.id.main_text_32);
        text_content4 = findViewById(R.id.main_text_42);
        text_content5 = findViewById(R.id.main_text_52);
        iContent = findViewById(R.id.main_image);
        reciveIntent();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(objectDetails[0]);
        setSupportActionBar(toolbar);
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
        finish();
        return true;
    }

    private void reciveIntent() {
        Intent i = getIntent();
        if(i != null)
            objectDetails = i.getStringArrayExtra("object_info");

        String objectType = objectDetails[1];
        iContent.setImageResource(getImageResource(objectType));

        text_content1.setText(objectDetails[0]);
        text_content2.setText(objectDetails[1]);
        text_content3.setText(objectDetails[2]);
        text_content4.setText(objectDetails[3]);
        text_content5.setText(objectDetails[4]);

    }

    // Метод для получения изображения по типу
    private int getImageResource(String type) {
        switch (type) {
            case "Промышленный объект":
                return R.drawable.ic_factory;
            case "Природный объект":
                return R.drawable.ic_tree;
            case "Транспортная инфраструктура":
                return R.drawable.ic_road;
            case "Природный водный объект":
                return R.drawable.ic_water;
            default:
                return R.drawable.ic_default;
        }
    }
}
