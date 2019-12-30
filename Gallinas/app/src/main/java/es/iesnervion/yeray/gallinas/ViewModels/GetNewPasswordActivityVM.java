package es.iesnervion.yeray.gallinas.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class GetNewPasswordActivityVM extends ViewModel {
    private MutableLiveData<String> mail;

    public GetNewPasswordActivityVM(){
        mail = new MutableLiveData<String>();
    }

    //Get Y Set
    public MutableLiveData<String> getMail(){
        return mail;
    }

    public void setMail(String mail){
        this.mail.setValue(mail);
    }

    /*
    * Interfaz
    * Nombre: claveAutogenerada
    * Comentario: Este método nos permite obtener una clave autogenerada.
    * La clave tendrá en cuenta lo siguiente:
    *   -Tendrá un tamaño entre 5 y 20 caracteres.
    *   -Contendrá como mínimo un carácter en mayúscula.
    *   -Contendrá como mínimo un carácter en minúscula.
    *   -Contendrá como mínimo un número.
    *   -Contendrá como mínimo uno de los siguientes caracteres (! " # $ % & ' ( ) * + , - . /).
    *   -La clave no contendrá espacios.
    * Cabecera: public String claveAutogenerada()
    * Salida:
    *   -String clave
    * Postcondiciones: El método devuelve una cadena asociada al nombre,
    * que es la nueva clave generada.
    * */
    public String claveAutogenerada(){
        String clave = "";
        Random random = new Random();
        int numCaracteres = random.nextInt(20)+5;

        for(int i = 0; i < numCaracteres / 4; i++){//Insertamos las letras en mayúscula
            clave += random.nextInt(25)+66;
        }

        for(int i = 0; i < numCaracteres / 4; i++){//Insertamos las letras en minúscula
            clave += random.nextInt(25)+98;
        }

        for(int i = 0; i < numCaracteres / 4; i++){//Insertamos los números
            clave += random.nextInt(10)+49;
        }

        for(int i = 0; i < numCaracteres / 4; i++){//Insertamos los caracteres espaciales
            clave += random.nextInt(14)+34;
        }

        for(int i = clave.length(); i < numCaracteres; i++){//Si aún quedan caracteres
            clave += random.nextInt(25)+66;
        }

        return clave;
    }
}
