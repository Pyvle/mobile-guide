package com.example.ebatpricols;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddItem extends Fragment {

    private EditText editTextName;
    private Spinner spinnerObjectType;
    private EditText editTextCoordinates;
    private EditText editTextControlParameters;
    private EditText editTextMeasurementMethod;
    private Button buttonAdd;
    private Button buttonCancel;

    public interface OnOnItemAddedListener {
        void onItemAdded(String name, String objectType, String coordinates, String controlParameters, String measurementMethod);
    }

    private OnOnItemAddedListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (OnOnItemAddedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnItemAddedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);

        // Инициализация элементов интерфейса
        editTextName = view.findViewById(R.id.edit_main_text_12);
        spinnerObjectType = view.findViewById(R.id.spinner2);
        editTextCoordinates = view.findViewById(R.id.edit_main_text_32);
        editTextControlParameters = view.findViewById(R.id.edit_main_text_42);
        editTextMeasurementMethod = view.findViewById(R.id.edit_main_text_52);
        buttonAdd = view.findViewById(R.id.button_add);
        buttonCancel = view.findViewById(R.id.button_cancel);

        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(getContext(),
                R.array.item_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerObjectType.setAdapter(adapter);

        // Обработчик нажатия кнопки "Добавить"
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String coordinates = editTextCoordinates.getText().toString();
                String controlParameters = editTextControlParameters.getText().toString();
                String measurementMethod = editTextMeasurementMethod.getText().toString();
                String objectType = spinnerObjectType.getSelectedItem().toString();

                if (!name.isEmpty() && !coordinates.isEmpty() && !controlParameters.isEmpty() && !measurementMethod.isEmpty()) {
                    listener.onItemAdded(name,objectType, coordinates, controlParameters, measurementMethod);
                    getParentFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                }
                closeKeyboard();
            }
        });

        buttonCancel.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
            closeKeyboard();
        });

        return view;
    }
    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}