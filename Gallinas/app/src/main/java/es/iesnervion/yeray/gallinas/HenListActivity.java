package es.iesnervion.yeray.gallinas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import es.iesnervion.yeray.gallinas.Entities.Gallina;
import es.iesnervion.yeray.gallinas.Lists.AdapterHenList;
import es.iesnervion.yeray.gallinas.ViewModels.CreateCountActivityVM;
import es.iesnervion.yeray.gallinas.ViewModels.HenListActivityVM;

public class HenListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Gallina> listadoGallinas = new ArrayList<>();
    AdapterHenList adapter;
    HenListActivityVM henListActivityVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hen_list);

        henListActivityVM = ViewModelProviders.of(this).get(HenListActivityVM.class);
        henListActivityVM.setNickUsuario(getIntent().getStringExtra("nombreUsuario"));
        listadoGallinas = henListActivityVM.getListadoGallinas().getValue();

        listView = findViewById(R.id.ListViewHens);

        adapter = new AdapterHenList(this, R.layout.item_list, listadoGallinas);
        listView.setAdapter(adapter);

        //listView.setOnItemClickListener(this);
        //listView.setOnItemLongClickListener(this);
    }

    /*
     * Intefaz
     * Nombre: throwCreateHenActivity
     * Comentario: Este método nos permite lanzar la actividad CreateHenActivity.
     * Cabecera: public void throwCreateHenActivity(View v)
     * Entrada:
     *   -View v
     * Postcondiciones: El método lanza la actividad CreateHenActivity.
     * */
    public void throwCreateHenActivity(View v){
        Intent i = new Intent(this, CreateHenActivity.class);
        i.putExtra("nombreUsuario", henListActivityVM.getNickUsuario());
        startActivity(i);
    }
}
