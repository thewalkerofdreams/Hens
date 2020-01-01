package es.iesnervion.yeray.gallinas.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import es.iesnervion.yeray.gallinas.DDBB.ManejadorBaseDeDatos;
import es.iesnervion.yeray.gallinas.Entities.Gallina;

public class HenListActivityVM extends AndroidViewModel {

    String nickUsuario;
    MutableLiveData<ArrayList<Gallina>> listadoGallinas;
    MutableLiveData<Gallina> gallinaSeleccionada;

    public HenListActivityVM(Application application) {
        super(application);
        nickUsuario = "";
        listadoGallinas = new MutableLiveData<>();
        gallinaSeleccionada = new MutableLiveData<>();
    }

    public String getNickUsuario(){
        return nickUsuario;
    }

    public void setNickUsuario(String nickUsuario){
        this.nickUsuario = nickUsuario;
        cargarListadoGallinas();//Cargamos la lista según el nick de usuario
    }

    public LiveData<ArrayList<Gallina>> getListadoGallinas() {
        return listadoGallinas;
    }

    public void setListadoGallinas(ArrayList<Gallina> listadoGallinas) {
        this.listadoGallinas.setValue(listadoGallinas);
    }

    public LiveData<Gallina> getGallinaSeleccionada() {
        return gallinaSeleccionada;
    }

    public void setGallinaSeleccionada(Gallina gallinaSeleccionada) {
        this.gallinaSeleccionada.setValue(gallinaSeleccionada);
    }

    /*
    * Interfaz
    * Nombre: cargarListadoGallinas
    * Comentario: Este método nos permite cargar un listado de gallinas
    * de la base de datos, según el nick de usuario de este viewmodel.
    * Cabecera: private void cargarListadoGallinas()
    * Postcondiciones: El método carga la lista de gallinas.
    * */
    private void cargarListadoGallinas(){
        ManejadorBaseDeDatos manejador = new ManejadorBaseDeDatos(getApplication());
        List<Gallina> listGallinas = manejador.getAllHens(nickUsuario);
        ArrayList<Gallina> henList = new ArrayList<Gallina>();

        for(int i = 0; i < listGallinas.size(); i++){
            henList.add(listGallinas.get(i));
        }

        this.listadoGallinas.setValue(henList);
    }
}
