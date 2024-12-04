package com.example.appsemestre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class perfilActivity extends AppCompatActivity {
    private Button btnchat, btnpedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        btnchat = findViewById(R.id.btnChat);
        btnpedidos = findViewById(R.id.btnPedidos);

        btnpedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), FirebaseActivity.class);
                startActivity(intent);
            }
        });

        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),MqttActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Items> itemList = new ArrayList<>();
        itemList.add(new Items("Esponjoso", R.drawable.esponjoso));
        itemList.add(new Items("Mantequilla", R.drawable.mantequilla));
        itemList.add(new Items("Merengue", R.drawable.merengue));
        itemList.add(new Items("Natilla", R.drawable.natilla));

        AdapterItems adapter = new AdapterItems(itemList);
        recyclerView.setAdapter(adapter);
    }
}