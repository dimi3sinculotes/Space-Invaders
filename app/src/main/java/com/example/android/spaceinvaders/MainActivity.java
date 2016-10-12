package com.example.android.spaceinvaders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ImageButton opcionBoton;
    private PopupWindow popup;
    private RelativeLayout layoutPrincipal;
    String[] resultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultados = new String[2];
        opcionBoton = (ImageButton) findViewById(R.id.opcion_boton);
        layoutPrincipal = (RelativeLayout) findViewById(R.id.principal_screen);
        opcionBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View vistaPopup = inflater.inflate(R.layout.popup_activity, null);

                popup = new PopupWindow(
                        vistaPopup,
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );

                Button cerrarPop = (Button) vistaPopup.findViewById(R.id.volver_boton);
                cerrarPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popup.dismiss();
                    }
                });
                popup.showAtLocation(layoutPrincipal, Gravity.BOTTOM, 0, 0);

            }
        });
    }

    public void iniciaJuego(View view){
        Intent juego = new Intent(view.getContext(), GameActivity.class);
        juego.putExtra("arg", resultados[0]+" "+resultados[1]);
        startActivity(juego);
    }

    public void actualizaFondo(View vista) {
        switch (vista.getId()) {
            case R.id.fondo_1:
                resultados[0] = "fondo";
                break;
            case R.id.fondo_2:
                resultados[0] = "fondo1";
                break;
            case R.id.fondo_3:
                resultados[0] = "fondo3";
                break;
        }
    }

    public void actualizaNave(View vista) {
        switch (vista.getId()) {
            case R.id.nave_1:
                resultados[1] = "diseno11";
                break;
            case R.id.nave_2:
                resultados[1] = "diseno21";
                break;
            case R.id.nave_3:
                resultados[1] = "diseno31";
                break;
        }
    }

}
