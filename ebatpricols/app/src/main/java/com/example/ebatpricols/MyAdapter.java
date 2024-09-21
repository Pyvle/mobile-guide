package com.example.ebatpricols;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String[]> values;

    public MyAdapter(@NonNull Context context, ArrayList<String[]> values) {
        super(context, R.layout.list_item);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    // Метод для получения изображения в зависимости от типа объекта
    private int getImageResource(String type) {
        switch (type) {
            case "Промышленный объект":
                return R.drawable.ic_factory; // Изображение завода
            case "Природный объект":
                return R.drawable.ic_tree; // Изображение дерева
            case "Транспортная инфраструктура":
                return R.drawable.ic_road; // Изображение дороги
            case "Природный водный объект":
                return R.drawable.ic_water; // Изображение воды
            default:
                return R.drawable.ic_default; // Изображение по умолчанию
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView textView = rowView.findViewById(R.id.item_name);
        ImageView imageView = rowView.findViewById(R.id.item_image);

        // Устанавливаем текст и изображение для каждого элемента
        textView.setText(values.get(position)[0]);

        String type = values.get(position)[1];
        imageView.setImageResource(getImageResource(type));

        return rowView;
    }
}
