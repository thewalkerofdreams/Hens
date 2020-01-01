package es.iesnervion.yeray.gallinas.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import es.iesnervion.yeray.gallinas.DDBB.ManejadorBaseDeDatos;
import es.iesnervion.yeray.gallinas.Entities.Usuario;

public class CreateCountActivityVM extends AndroidViewModel {

    private MutableLiveData<String> nickName, password01, password02, mail, firstName, lastName;

    public CreateCountActivityVM(Application application){
        super(application);
        this.nickName = new MutableLiveData<>();
        this.password01 = new MutableLiveData<>();
        this.password02 = new MutableLiveData<>();
        this.mail = new MutableLiveData<>();
        this.firstName = new MutableLiveData<>();
        this.lastName = new MutableLiveData<>();
    }

    //Get y Sets
    public LiveData<String> getNickName(){
        return this.nickName;
    }

    public void setNickName(String nickName){
        this.nickName.setValue(nickName);
    }

    public LiveData<String> getPassword01(){
        return this.password01;
    }

    public void setPassword01(String password01){
        this.password01.setValue(password01);
    }

    public LiveData<String> getPassword02(){
        return this.password02;
    }

    public void setPassword02(String password02){
        this.password02.setValue(password02);
    }

    public LiveData<String> getMail(){
        return this.mail;
    }

    public void setMail(String mail){
        this.mail.setValue(mail);
    }

    public LiveData<String> getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName.setValue(firstName);
    }

    public LiveData<String> getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName.setValue(lastName);
    }

    /*
    * Interfaz
    * Nombre: insertarNuevaGallina
    * Comentario: Este método nos permite insertar una nueva cuenta en la base de
    * datos de la aplicación Gallinas con los datos del ViewModel "CreateCountActivityVM".
    * Cabecera: public void insertarNuevaGallina()
    * Precondiciones:
    *   -Los datos del ViewModel "CreateCountActivityVM" deben ser válidos.
    * Postcondiciones: El método inserta una nueva cuenta en la base de datos de la aplicación.
    * */
    public void insertarNuevaCuenta(){
        ManejadorBaseDeDatos manejadorBaseDeDatos = new ManejadorBaseDeDatos(getApplication());
        Usuario usuario = new Usuario(getNickName().getValue(), getPassword01().getValue(), getMail().getValue(),
                getFirstName().getValue(), getLastName().getValue());
        manejadorBaseDeDatos.insertUser(usuario);
    }
}
