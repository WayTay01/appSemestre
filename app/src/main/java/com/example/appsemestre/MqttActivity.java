package com.example.appsemestre;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MqttActivity extends AppCompatActivity {

    private static final String BROKER = "tcp://broker.emqx.io:1883";
    private static final String CLIENT_ID = "mqtt_ABD";
    private static final String TOPIC_SUB = "lab/redes/android";
    private MqttHolder mqttHolder;

    private EditText mensaje;
    private TextView MostrarMensaje, MensajeRecibido;
    private Button BtnPublicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mqtt);

        mensaje = findViewById(R.id.editTextMessage);
        MostrarMensaje = findViewById(R.id.textViewStatus);
        MensajeRecibido = findViewById(R.id.textViewReceived);
        BtnPublicar = findViewById(R.id.buttonPublish);

        mqttHolder = new MqttHolder();

        mqttHolder.setMessageListener(new MqttHolder.MessageListener() {
            @Override
            public void onMessageReceived(String topic, String message) {
                runOnUiThread(()->MensajeRecibido.setText("Topico: ["+ topic + "] : " + message));
            }
        });

        mqttHolder.connect(BROKER, CLIENT_ID);
        mqttHolder.subscribe(TOPIC_SUB);

        BtnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dato = mensaje.getText().toString();
                if (!dato.isEmpty()){
                    mqttHolder.publish(TOPIC_SUB, dato);
                    MostrarMensaje.setText("Mensaje publicado: " + dato);
                }
            }
        });

    }
    @Override
    protected void onDestroy(){
        mqttHolder.disconnect();
        super.onDestroy();
    }
}