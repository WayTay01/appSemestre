package com.example.appsemestre;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseActivity extends AppCompatActivity {
    private EditText campo1, campo2;
    private Button btnEnviar;
    private TextView resultado;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_firebase);

        campo1 = findViewById(R.id.editTextField1);
        campo2 = findViewById(R.id.editTextField2);
        btnEnviar = findViewById(R.id.buttonSend);
        resultado = findViewById(R.id.textViewResult);
        databaseReference = FirebaseDatabase.getInstance().getReference("Datos");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder values = new StringBuilder();
                for(DataSnapshot sn : snapshot.getChildren()){
                    Datos dato = sn.getValue(Datos.class);
                    if(dato != null){
                        values.append("Campo 1: ").append(dato.campo1).append(", Campo 2: ").append("\n");
                    }
                }
                resultado.setText(values.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String c1 = campo1.getText().toString();
                String c2 = campo2.getText().toString();
                String id = databaseReference.push().getKey();
                if(id != null){

                    databaseReference.child(id).setValue(new Datos(c1,c2));
                    campo1.setText("");
                    campo2.setText("");
                }
            }
        });

    }
    public static class Datos{
        public String campo1;
        public String campo2;
        public Datos(){}

        public Datos(String campo1, String campo2){
            this.campo1 = campo1;
            this.campo2 = campo2;
        }
    }
}