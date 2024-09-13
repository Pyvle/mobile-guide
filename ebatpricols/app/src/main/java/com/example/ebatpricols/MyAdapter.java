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

public class MyAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private final int[] images;

    public MyAdapter(@NonNull Context context, String[] values, int[] images) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.values = values;
        this.images = images;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView textView = rowView.findViewById(R.id.item_name);
        ImageView imageView = rowView.findViewById(R.id.item_image);

        // Устанавливаем текст и изображение для каждого элемента
        textView.setText(values[position]);
        imageView.setImageResource(images[position]);

        return rowView;
    }
}
