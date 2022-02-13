package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.zip.Inflater;

public class LoginActivity extends AppCompatActivity {
    private TextView btn;
    private EditText edtEmail, edtPassword;
    private Button btnMasuk;
    private SharedPreferences sharedPreferences;
    //private String Email, Password, Periksa;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = findViewById(R.id.textViewSignUp);
        btn.setOnClickListener((v)->{
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        });
        DatabaseHandler databaseHandler;
        databaseHandler= new DatabaseHandler(this);
        edtEmail = findViewById(R.id.logEmail);
        edtPassword = findViewById(R.id.logPassword);
        btnMasuk = findViewById(R.id.btnlogin);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String sp_email = sharedPreferences.getString(KEY_EMAIL,null);

        if (sp_email != null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //buttonLogin_onClick(v);
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                Boolean res = databaseHandler.checkUser(email, password);

                if (res == true)
                {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_EMAIL,email);
                    editor.putString(KEY_PASSWORD,password);
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Log in Berhasil ", Toast.LENGTH_SHORT).show();


                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Log in Gagal ", Toast.LENGTH_SHORT).show();

                }
                /*
                if (v.getId() == R.id.btnlogin){
                    Email = edtEmail.getText().toString();
                    Password = edtPassword.getText().toString();
                    DatabaseHandler databaseHandler = new DatabaseHandler((getApplicationContext()));
                    Periksa = databaseHandler.searchPass(Email);
                    if (Password.equals(Periksa)){
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("pesan",Email);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Username dan Password Tidak Cocok", Toast.LENGTH_SHORT).show();
                    }
                }*/
            }
        });
    }


}