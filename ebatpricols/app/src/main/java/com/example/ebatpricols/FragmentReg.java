package com.example.ebatpricols;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class FragmentReg extends Fragment {

    private EditText editTextUsername;
    private EditText editTextPassword1;
    private EditText editTextPassword2;
    private Spinner spinnerRole;
    private Button buttonRegistry;
    private DataBaseClass dbClass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbClass = new DataBaseClass(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reg, container, false);

        spinnerRole = view.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.roles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(adapter);

        editTextUsername = view.findViewById(R.id.reg_login);
        editTextPassword1 = view.findViewById(R.id.reg_password1);
        editTextPassword2 = view.findViewById(R.id.reg_password2);

        buttonRegistry = view.findViewById(R.id.reg);

        buttonRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password1 = editTextPassword1.getText().toString();
                String password2 = editTextPassword2.getText().toString();
                String role = spinnerRole.getSelectedItem().toString();

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password1) || !password1.equals(password2))
                    Toast.makeText(getActivity(), "Введите данные корректно", Toast.LENGTH_SHORT).show();
                else {
                    if(dbClass.checkUsername(username)) {
                        closeKeyboard();
                        Toast.makeText(getActivity(), "Пользователь с таким именем уже существует", Toast.LENGTH_SHORT).show();
                    } else {
                        if (dbClass.addUser(username, password1, role)) {
                            closeKeyboard();
                            Toast.makeText(getActivity(), "Пользователь добавлен", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Ошибка при добавлении учетных данных", Toast.LENGTH_SHORT).show();
                            closeKeyboard();
                        }
                    }
                }

            }
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