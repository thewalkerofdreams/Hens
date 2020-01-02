package es.iesnervion.yeray.gallinas.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import es.iesnervion.yeray.gallinas.DDBB.ManejadorBaseDeDatos;
import es.iesnervion.yeray.gallinas.Entities.Gallina;

public class CreateHenActivityVM extends AndroidViewModel {

    private MutableLiveData<String> name, pedigree, weight, height;
    String nickUsuario;

    public CreateHenActivityVM(Application application) {
        super(application);
        name = new MutableLiveData<String>();
        pedigree = new MutableLiveData<String>();
        weight = new MutableLiveData<String>();
        height = new MutableLiveData<String>();
        nickUsuario = "";
    }

    //Get y Sets
    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public MutableLiveData<String> getPedigree() {
        return pedigree;
    }

    public void setPedigree(String pedigree) {
        this.pedigree.setValue(pedigree);
    }

    public MutableLiveData<String> getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight.setValue(weight);
    }

    public MutableLiveData<String> getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height.setValue(height);
    }

    public String getNickUsuario() {
        return nickUsuario;
    }

    public void setNickUsuario(String nickUsuario) {
        this.nickUsuario = nickUsuario;
    }

    /*
     * Interfaz
     * Nombre: insertarNuevaGallina
     * Comentario: Este método nos permite insertar una nueva gallina en la base de
     * datos de la aplicación Gallinas con los datos del ViewModel "CreateHenActivityVM".
     * Cabecera: public void insertarNuevaGallina()
     * Precondiciones:
     *   -Los datos del ViewModel "CreateHenActivityVM" deben ser válidos.
     * Postcondiciones: El método inserta una nueva gallina en la base de datos de la aplicación.
     * */
    public void insertarNuevaGallina() {
        ManejadorBaseDeDatos manejadorBaseDeDatos = new ManejadorBaseDeDatos(getApplication());
        Gallina gallina = new Gallina(0, this.name.getValue(), this.pedigree.getValue(), Double.valueOf(this.weight.getValue()),
                Double.valueOf(this.height.getValue()), this.nickUsuario);
        manejadorBaseDeDatos.insertHen(gallina);
    }
}