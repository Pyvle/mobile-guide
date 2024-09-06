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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class FragmentReg extends Fragment {

    private EditText editTextUsername;
    private EditText editTextPassword1;
    private EditText editTextPassword2;
    private Button buttonRegistry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reg, container, false);

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

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password1) || !password1.equals(password2))
                    Toast.makeText(getActivity(), "Введите данные корректно", Toast.LENGTH_SHORT).show();
                else {
                    addUserToFile(username, password1);
                }

            }
        });

        return view;
    }

    private void addUserToFile(String username, String password) {
        String filename = "credentials.txt";

        if (checkUsername(username)) {
            closeKeyboard();
            Toast.makeText(getActivity(), "Пользователь с таким именем уже существует", Toast.LENGTH_SHORT).show();
        } else {
            try (FileOutputStream fos = getActivity().openFileOutput(filename, Context.MODE_APPEND)) {
                String credentials = username + ":" + password + "\n";
                fos.write(credentials.getBytes());
                closeKeyboard();
                Toast.makeText(getActivity(), "Пользователь добавлен", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Ошибка при добавлении учетных данных", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkUsername(String username) {
        String filename = "credentials.txt";
        StringBuilder text = new StringBuilder();

        try (FileInputStream fis = getActivity().openFileInput(filename);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader bufferedReader = new BufferedReader(isr)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] storedCredentials = line.split(":");
                if (storedCredentials.length == 2) {
                    String storedUsername = storedCredentials[0];

                    if (username.equals(storedUsername))
                        return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ошибка при чтении файла", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}