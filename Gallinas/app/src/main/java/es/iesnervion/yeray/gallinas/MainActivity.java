package es.iesnervion.yeray.gallinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import es.iesnervion.yeray.gallinas.DDBB.ManejadorBaseDeDatos;

public class MainActivity extends AppCompatActivity {

    ManejadorBaseDeDatos manejador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    /*
     * Intefaz
     * Nombre: throwCreateCountActivity
     * Comentario: Este método nos permite lanzar la actividad CreateCountActivity.
     * Cabecera: public void throwCreateCountActivity(View v)
     * Entrada:
     *   -View v
     * Postcondiciones: El método lanza la actividad CreateCountActivity.
     * */
    public void throwCreateCountActivity(View v){
        Intent i = new Intent(this, CreateCountActivity.class);
        startActivity(i);
    }
}
