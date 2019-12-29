package es.iesnervion.yeray.gallinas.Validations;

import android.content.Context;
import android.util.Patterns;

import java.util.regex.Pattern;

import es.iesnervion.yeray.gallinas.DDBB.ManejadorBaseDeDatos;
import es.iesnervion.yeray.gallinas.Utilities.FuncionesCadenas;

public class UserValidations {
    /*
    * Interfaz
    * Nombre: correoValido
    * Comentario: Este método nos permite verificar si una cadena es un correo válido.
    * Cabecera: public boolean correoValido(String correo)
    * Entrada:
    *   -String correo
    * Salida:
    *   -boolean correct
    * Postcondiciones: El método devuelve un valor booleano asociado al nombre, true
    * si el correo es válido y falso en caso contrario.
    * */
    public boolean correoValido(String correo){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(correo).matches();
    }

    /*
    * Interfaz
    * Nombre: correctPassword
    * Comentario: Este método nos permite verificar si una cadena es
    * una contraseña válida para la aplicación Gallinas. Para que la
    * contraseña se válida debe cumplir lo siguiente:
    *   -Debe tener un tamaño entre 5 y 20 caracteres.
    *   -Debe contener como mínimo un carácter en mayúscula.
    *   -Debe contener como mínimo un carácter en minúscula.
    *   -Debe contener como mínimo un número.
    *   -Debe contener como mínimo uno de los siguientes caracteres (! " # $ % & ' ( ) * + , - . /).
    *   -La contraseña no debe contener espacios.
    * Cabecera: public boolean correctPassword(String password)
    * Entrada:
    *   -String password
    * Salida:
    *   -boolean correct
    * Postcondiciones: El método devuelve un valor booleano asociado al nombre,
    * true si la contraseña es válida o false en caso contrario.
    * */
    public boolean correctPassword(String password){
        boolean correct = false;

        if((password.length() >= 5 && password.length() <= 20) &&
                (FuncionesCadenas.numeroMayusculas(password) >= 1) &&
                (FuncionesCadenas.numeroMinusculas(password) >= 1) &&
                (FuncionesCadenas.numeroDeNumeros(password) >= 1) &&
                (FuncionesCadenas.numeroCaracteresEspeciales(password) >= 1) &&
                (FuncionesCadenas.numeroEspaciosEnBlanco(password) == 0)){
            correct = true;
        }

        return correct;
    }

    /*
    * Interfaz
    * Nombre: existNickName
    * Comentario: Este método nos permite verificar si existe un nick específico
    * en la base de datos de la aplicación Gallinas.
    * Cabecera: public boolean existNickName(String nick)
    * Entrada:
    *   -String nick
    *   -Context context
    * Salida:
    *   -boolean exist
    * Postcondiciones: El método devuelve un valor booleano asociado al nombre, true
    * si el nick aún no existe en la base de datos o false en caso contrario.
    * */
    public boolean existNickName(String nick, Context context){
        boolean exist = false;
        ManejadorBaseDeDatos manejador = new ManejadorBaseDeDatos(context);

        if(manejador.getUser(nick) != null){//Si existe el nick en la base de datos
            exist = true;
        }

        return exist;
    }
}
