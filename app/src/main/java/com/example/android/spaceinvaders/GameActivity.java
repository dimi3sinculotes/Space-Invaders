package com.example.android.spaceinvaders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    ImageView municion;
    ImageView nave;
    ImageView fondoJuego;
    Button botonDisparo;
    Handler manejaDisparo = new Handler();
    final int movimiento = 30;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        municion = (ImageView) findViewById(R.id.municion);
        nave = (ImageView) findViewById(R.id.nave);
        fondoJuego = (ImageView) findViewById(R.id.fondo_juego);
        botonDisparo = (Button) findViewById(R.id.disparo);
        Intent i = getIntent();
        if (i!=null) {
            String data = i.getStringExtra("arg");
            introduceCambios(data);
        }
    }

    private void introduceCambios(String data){
        String[] info = data.split(" ");
        int idFondo = getResources().getIdentifier(info[0], "drawable", getPackageName());
        fondoJuego.setImageResource(idFondo);
        int idNave = getResources().getIdentifier(info[1], "drawable", getPackageName());
        nave.setImageResource(idNave);
    }

    public void actualizaPosicion(View v) {
        switch (v.getId()) {
            case (R.id.control_derecha):
                if (!seSale("der")) {
                    nave.setImageResource(R.drawable.diseno12);
                    nave.setX(nave.getX() - movimiento);
                }
                break;
            case R.id.control_izquierda:
                if (!seSale("izq")) {
                    nave.setImageResource(R.drawable.diseno13);
                    nave.setX(nave.getX() + movimiento);
                }
                break;
        }
    }

    public void dispara(View v) {
        nave.setImageResource(R.drawable.diseno11);
        municion.setX(nave.getX() + (((nave.getWidth()) / 2) - 5));
        municion.setY(nave.getY());
        municion.setVisibility(View.VISIBLE);
        botonDisparo.setEnabled(false);
        manejaDisparo.postDelayed(accionDisparo, 0);
    }

    Runnable accionDisparo = new Runnable() {
        @Override
        public void run() {
            municion.setY(municion.getY() - 50);
            if (llegaAlFinal()) {
                municion.setVisibility(View.INVISIBLE);
                manejaDisparo.removeCallbacks(accionDisparo);
                botonDisparo.setEnabled(true);
            }
            manejaDisparo.postDelayed(this, 80);
        }
    };

    private boolean llegaAlFinal() {
        return municion.getY() <= 20;
    }

    private boolean seSale(String direccion) {
        switch (direccion) {
            case "izq":
                return (nave.getX() + movimiento + nave.getWidth()) > findViewById(R.id.activity_main).getWidth();
            case "der":
                return (nave.getX() - movimiento) < 0;
        }
        return true;
    }
}
