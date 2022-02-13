package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {
    private TextView btn;
    private EditText edtNama, edtPhone, edtEmail, edtPassword, edtKonfirmasi;
    private Button btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn = findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btnDaftar = findViewById(R.id.btnRegister);

        edtNama = findViewById(R.id.inputName);
        edtPhone = findViewById(R.id.inputPhone);
        edtEmail = findViewById(R.id.inputEmail);
        edtPassword = findViewById(R.id.inputPassword);
        edtKonfirmasi = findViewById(R.id.inputConfirmPassword);
        DatabaseHandler databaseHandler = new DatabaseHandler((getApplicationContext()));

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //buttonSave_onClick(v);
                if (validate()){

                    //Check in the database is there any user associated with  this email
                    if (!databaseHandler.isEmailExists(edtEmail.getText().toString())) {
                        Users users = new Users(edtNama.getText().toString(),
                                edtEmail.getText().toString(),
                                edtPhone.getText().toString(),
                                edtPassword.getText().toString());
                        databaseHandler.insertUsers(users);
                        finish();
                        Toast.makeText(RegisterActivity.this, "Akun Berhasil di Buat", Toast.LENGTH_SHORT).show();
                    }else {
                        /*
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle(R.string.error);
                        builder.setMessage(R.string.email_exist);
                        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();*/
                        Toast.makeText(RegisterActivity.this, "Email Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public boolean validate(){
        boolean valid = false;
        if (edtNama.getText().toString().isEmpty()){
            valid = false;
            edtNama.setError("Nama Tidak Boleh Kosong");
        }else{
            valid = true;
            edtNama.setError(null);
        }
        if (edtPhone.getText().toString().isEmpty()){
            valid = false;
            edtPhone.setError("Nomor Handphone Tidak Boleh Kosong");
        }else{
            valid = true;
            edtPhone.setError(null);
        }
        if (edtEmail.getText().toString().isEmpty()){
            valid = false;
            edtEmail.setError("Email Tidak Boleh Kosong");
        }else{
            valid = true;
            edtEmail.setError(null);
        }
        if (edtPassword.getText().toString().isEmpty()){
            valid = false;
            edtPassword.setError("Password Tidak Boleh Kosong");
        }else{
            valid = true;
            edtPassword.setError(null);
        }
        if (edtKonfirmasi.getText().toString().isEmpty()){
            valid = false;
            edtKonfirmasi.setError("Harus Mengisi Konfirmasi Password");
        }else{
            valid = true;
            edtKonfirmasi.setError(null);
        }
        if (!edtPassword.getText().toString().equals(edtKonfirmasi.getText().toString())){
            valid = false;
            Toast.makeText(RegisterActivity.this, "Password Tidak Sama", Toast.LENGTH_SHORT).show();
        }else{
            valid = true;
        }
        return valid;
    }

}