package es.iesnervion.yeray.gallinas;

import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import es.iesnervion.yeray.gallinas.DDBB.ManejadorBaseDeDatos;
import es.iesnervion.yeray.gallinas.Entities.Gallina;
import es.iesnervion.yeray.gallinas.Fragments.EditHenFragment;
import es.iesnervion.yeray.gallinas.Lists.AdapterHenList;
import es.iesnervion.yeray.gallinas.ViewModels.HenListActivityVM;

public class HenListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ListView listView;
    ArrayList<Gallina> listadoGallinas = new ArrayList<>();
    AdapterHenList adapter;
    HenListActivityVM henListActivityVM;
    EditHenFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hen_list);

        henListActivityVM = ViewModelProviders.of(this).get(HenListActivityVM.class);
        henListActivityVM.setNickUsuario(getIntent().getStringExtra("nombreUsuario"));
        listadoGallinas = henListActivityVM.getListadoGallinas().getValue();//Obtenemos el listado de gallinas
        listView = findViewById(R.id.ListViewHens);

        adapter = new AdapterHenList(this, R.layout.item_list, listadoGallinas);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(henListActivityVM.getGallinaSeleccionada() == null || fragment == null){//Si no hay ninguna gallina seleccionada o el fragmento aún no ha sido instanciado
            replaceFragment();
        }
        henListActivityVM.setGallinaSeleccionada((Gallina) listView.getAdapter().getItem(position));//Obtenemos la gallina seleccionada
    }

    @Override
    public boolean onItemLongClick (AdapterView<?> adapterView, View view,int i, long l){
        final Gallina item = (Gallina) adapterView.getItemAtPosition(i);//Obtenemos el item de la posición clicada

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Confirm Delete");// Setting Alert Dialog Title
        alertDialogBuilder.setMessage("Do you really want delete this hen?");// Setting Alert Dialog Message
        alertDialogBuilder.setCancelable(false);//Para que no podamos quitar el dialogo sin contestarlo

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getBaseContext(), "Hen deleted", Toast.LENGTH_SHORT).show();
                ManejadorBaseDeDatos manejador = new ManejadorBaseDeDatos(getApplication());
                manejador.deleteHen(item.getId());
                removeYourFragment();
                recargarLista();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        return true;//Nos permite no realizar la acción de clicado rápido cuando dejamos pulsado un item.
    }

    /*
    * Interfaz
    * Nombre: replaceFragment
    * Comentario: Este método nos permite crear un fragmento y remplazar el contenido de nuestro
    * FrameLayout por ese mismo fragmento.
    * Cabecera: public void replaceFragment()
    * Postcondiciones: El método reemplaza el contenido del FrameLayout por el nuevo fragmento.
    * */
    public void replaceFragment(){
        fragment = new EditHenFragment();
        FragmentTransaction transation = getSupportFragmentManager().beginTransaction();
        transation.replace(R.id.FrameLayout01, fragment);
        transation.commit();
    }

    /*
    * Interfaz
    * Nombre: removeYourFragment
    * Comentario: Este método nos permite eliminar el fragmento de la actividad actual.
    * Cabecera: public void removeYourFragment()
    * Postcondiciones: El método elimina el fragmento de la actividad actual.
    * */
    public void removeYourFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment != null) {
            transaction.remove(fragment);
            transaction.commit();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            fragment = null;
        }
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
        //startActivity(i);
        startActivityForResult(i, 1);
    }

    /*
     * Interfaz
     * Nombre: recargarLista
     * Comentario: Este método nos permite recargar la lista de gallinas.
     * Cabecera: public void recargarLista()
     * Postcondiciones: El método recarga la lista de gallinas.
     * */
    public void recargarLista(){
        ManejadorBaseDeDatos manejador = new ManejadorBaseDeDatos(this);
        ArrayList<Gallina> listaActualizada = new ArrayList<Gallina>();

        listaActualizada.addAll(manejador.getAllHens(henListActivityVM.getNickUsuario()));//Cargamos las gallinas en el arraylist
        henListActivityVM.setListadoGallinas(listaActualizada);//Actualizamos el vm
        listadoGallinas = henListActivityVM.getListadoGallinas().getValue();
        adapter = new AdapterHenList(this, R.layout.item_list, listadoGallinas);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode == RESULT_OK)
        {
            recargarLista();
        }
    }
}
