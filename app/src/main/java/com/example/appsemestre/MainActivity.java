package com.example.appsemestre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnIniciar;

    private EditText txtNombre, txtContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        btnIniciar = findViewById(R.id.btnIniciar);
        txtNombre = findViewById(R.id.txtNombre);
        txtContraseña = findViewById(R.id.txtContraseña);

        btnIniciar.setOnClickListener(v -> validateAndLogin());

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), perfilActivity.class);
                startActivity(intent);
            }
        });
    }
    public void validateAndLogin(){
        String nombre = txtNombre.getText().toString().trim();
        String contraseña = txtContraseña.getText().toString().trim();

        if (nombre.isEmpty()){
            txtNombre.setError("Falta datos!!");
            txtNombre.requestFocus();
            return;
        }
        if (contraseña.isEmpty()){
            txtNombre.setError("Falta datos!!");
            txtContraseña.requestFocus();
            return;
        }
        //INICIAMOS SECION CON ESTOS DATOS
        if (nombre.equals("") && contraseña.equals("")) {

            Intent intent = new Intent(MainActivity.this, perfilActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Nombre y contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }



}
