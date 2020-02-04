package com.softech.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.softech.login.Modelo.Cliente;

public class ActivityBienvenido extends AppCompatActivity {

    TextView txtUsuario;
    TextView txtToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        inicializarOyentes();
    }

    private void inicializarOyentes() {
        txtUsuario = findViewById(R.id.txtUsuario);
        txtToken = findViewById(R.id.txtToken);

        String cliente = getIntent().getStringExtra("cliente");
        Gson gson = new Gson();

        Cliente c = gson.fromJson(cliente, Cliente.class);

        txtToken.setText(c.getUsuario().getToken().toString());
        txtUsuario.setText(c.getUsuario().getNombreUsuario().toString());
    }
}
