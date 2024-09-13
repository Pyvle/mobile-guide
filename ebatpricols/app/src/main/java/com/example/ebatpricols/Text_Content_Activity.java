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
    private String[] array_title;
    private String[] array_text;
    private int[] array_image = {R.drawable.i, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_content);

        array_title =  getResources().getStringArray(R.array.cities);
        array_text = new String[]{getString(R.string.text_1), getString(R.string.text_2), getString(R.string.text_3), getString(R.string.text_4), getString(R.string.text_5)};

        text_content1 = findViewById(R.id.main_text_12);
        text_content2 = findViewById(R.id.main_text_22);
        text_content3 = findViewById(R.id.main_text_32);
        text_content4 = findViewById(R.id.main_text_42);
        text_content5 = findViewById(R.id.main_text_52);
        iContent = findViewById(R.id.main_image);
        reciveIntent();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(array_title[position]);
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
            position = i.getIntExtra("position", 0);

        iContent.setImageResource(array_image[position]);

        String[] parts = array_text[position].split(":");
        text_content1.setText(parts[0]);
        text_content2.setText(parts[1]);
        text_content3.setText(parts[2]);
        text_content4.setText(parts[3]);
        text_content5.setText(parts[4]);

    }
}
