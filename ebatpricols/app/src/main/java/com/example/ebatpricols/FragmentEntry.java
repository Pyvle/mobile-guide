package com.example.ebatpricols;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FragmentEntry extends Fragment {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonEntry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry, container, false);

        editTextUsername = view.findViewById(R.id.login);
        editTextPassword = view.findViewById(R.id.password);

        buttonEntry = view.findViewById(R.id.entryHome);

        buttonEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
                    Toast.makeText(getActivity(), "Введите данные корректно", Toast.LENGTH_SHORT).show();
                else {
                     if(checkCredentials(username, password)) {
                         Intent intent = new Intent(getActivity(), HomeActivity.class);
                         startActivity(intent);
                         getActivity().finish();
                     }
                }

            }
        });

        return view;
    }

    private boolean checkCredentials(String username, String password) {
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
                    String storedPassword = storedCredentials[1];

                    if (username.equals(storedUsername) && password.equals(storedPassword))
                        return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ошибка при чтении файла", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}