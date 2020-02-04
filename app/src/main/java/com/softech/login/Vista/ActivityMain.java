package com.softech.login.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import com.softech.login.ActivityBienvenido;
import com.softech.login.Controlador.Controlador;

import com.softech.login.Modelo.Cliente;
import com.softech.login.Modelo.Usuario;
import com.softech.login.R;


import java.net.URLEncoder;

public class ActivityMain extends AppCompatActivity {

    TextView txtUsuario;
    TextView txtContrasenia;
    private Button btnIniciarSesion;
    private Controlador ctrl = new Controlador();
    private String API = "http://192.168.0.9:8080/Login/api/ingresar/cliente?";
    private Gson gson = new Gson();

    private Dialog dlgConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Si la version del api de android es mayor o igual a 23 (Android 6.0)
         * hay que pedir permiso de esta froma, además de agregarlo al Manifest
         */
        //si la version del api de android es mayor o igual a 23 (Android 6.0)
        //Hay que pedir permiso de esta forma, además de agregarlo al Manifest
        if (Build.VERSION.SDK_INT >= 23) {
            //Pedimos permiso para que la app pueda usar internet
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
        } else {
            inicializarOyentes();

        }

    }

    /**
     * Este método se invoca al momento que el usuario da permiso a la app para hacer uso de internet
     *
     * @param
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.i("info", "Permiso concedido");

            /**
             * Una vez que el usuario de los permisos necesarios, inicializamos los componentes
             */
            inicializarOyentes();
        } else {
            System.exit(0);
        }
    }


    private void inicializarOyentes() {
        txtUsuario = findViewById(R.id.txtUsuario);
        txtContrasenia = findViewById(R.id.txtContrasenia);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        Drawable d = new ColorDrawable(Color.BLACK);
        dlgConsultar = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        d.setAlpha(130);
        dlgConsultar.getWindow().setBackgroundDrawable(d);
    }

    public void iniciarSesion(View v) {
        String usuario = txtUsuario.getText().toString().trim();
        String contrasenia = txtContrasenia.getText().toString(); //Como es contraseña no quitamos espacios

        Usuario u = new Usuario(usuario, contrasenia);

        if (!verificarDatos(u)) {

            Snackbar.make(v, "NEL", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }

        conectarServicio(u, v);

    }

    private boolean verificarDatos(Usuario u) {

        return !(u.getNombreUsuario().isEmpty() || u.getContrasenia().isEmpty());

    }

    public void conectarServicio(Usuario u, final View v) {

        RequestQueue rq = Volley.newRequestQueue(this);

        String url = API
                + "&usuario=" + URLEncoder.encode(u.getNombreUsuario())
                + "&contrasenia=" + URLEncoder.encode(u.getContrasenia());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.toString() != null && !response.toString().trim().isEmpty() && !response.toString().equals("null")) {

                    cambiarActivity(response);
                }
                dlgConsultar.hide();

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dlgConsultar.hide();
                Snackbar.make(v, error.getMessage(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        };

        StringRequest sr = new StringRequest(
                Request.Method.POST,
                url,
                responseListener,
                errorListener);
        dlgConsultar.show();
        rq.add(sr);

    }

    public void cambiarActivity(String response) {
        Intent intent = new Intent(this, ActivityBienvenido.class);
        intent.putExtra("cliente", response);
        startActivity(intent);
    }

}
