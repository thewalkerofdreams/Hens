package es.iesnervion.yeray.gallinas.Utilities;

public class FuncionesCadenas {
    /*
    * Interfaz
    * Nombre: numeroEspaciosEnBlanco
    * Comentario: Esta función nos permite saber el número de espacios en
    * blanco que contiene una cadena.
    * Cabecera: public static int numeroEspaciosEnBlanco(String cadena)
    * Entrada:
    *   -String cadena
    * Salida:
    *   -int numEspacios
    * Postcondiciones: La función devuelve un número entero asociado al nombre,
    * que es el número de espacios en blanco que contiene la cadena.
    * */
    public static int numeroEspaciosEnBlanco(String cadena){
        int numEspacios = 0;

        for(int i = 0; i < cadena.length(); i++){
            if(cadena.charAt(i) == 32){
                numEspacios++;
            }
        }

        return numEspacios;
    }

    /*
    * Interfaz
    * Nombre: numeroCaracteresEspeciales
    * Comentario: Esta función nos permite obtener al número de caracteres
    * especiales que contiene una cadena. Los caracteres especiales son los
    * siguientes (! " # $ % & ' ( ) * + , - . /)
    * Cabecera: public static int numeroCaracteresEspeciales(String cadena)
    * Entrada:
    *   -String cadena
    * Salida:
    *   -int numEspeciales
    * Postcondiciones: La función devuelve un número entero asociado al nombre,
    * que es el número de caracteres especiales que contiene la cadena.
    * */
    public static int numeroCaracteresEspeciales(String cadena){
        int numEspeciales = 0;

        for(int i = 0; i < cadena.length(); i++){
            if(cadena.charAt(i) >= 33 && cadena.charAt(i) <= 47){
                numEspeciales++;
            }
        }

        return numEspeciales;
    }

    /*
    * Interfaz
    * Nombre: numeroMinusculas
    * Comentario: Esta función nos permite obtener el número de caracteres
    * en minúscula de una cadena.
    * Cabecera: public static int numeroMinusculas(String cadena)
    * Entrada:
    *   -String cadena
    * Salida:
    *   -int numMinusculas
    * Postcondiciones: La función devuelve un número entero asociado al nombre,
    * que es el número de caracteres en minúscula que contiene la cadena.
    * */
    public static int numeroMinusculas(String cadena){
        int numMinusculas = 0;

        for(int i = 0; i < cadena.length(); i++){
            if((cadena.charAt(i) >= 97 && cadena.charAt(i) <= 122) || cadena.charAt(i) == 164){
                numMinusculas++;
            }
        }

        return numMinusculas;
    }

    /*
     * Interfaz
     * Nombre: numeroMayusculas
     * Comentario: Esta función nos permite obtener el número de caracteres
     * en mayúscula de una cadena.
     * Cabecera: public static int numeroMayusculas(String cadena)
     * Entrada:
     *   -String cadena
     * Salida:
     *   -int numMayusculas
     * Postcondiciones: La función devuelve un número entero asociado al nombre,
     * que es el número de caracteres en mayúscula que contiene la cadena.
     * */
    public static int numeroMayusculas(String cadena){
        int numMayusculas = 0;

        for(int i = 0; i < cadena.length(); i++){
            if((cadena.charAt(i) >= 65 && cadena.charAt(i) <= 90) || cadena.charAt(i) == 165){
                numMayusculas++;
            }
        }

        return numMayusculas;
    }

    /*
    * Interfaz
    * Nombre: numeroDeNumeros
    * Comentario: Esta función nos permite obtener el número de números que
    * contiene una cadena.
    * Cabecera: public static int numeroDeNumeros(String cadena)
    * Entrada:
    *   -String cadena
    * Salida:
    *   -int numNumeros
    * Postcondiciones: La función devuelve un número entero asociado al nombre,
    * que es el número de números que contiene la cadena.
    * */
    public static int numeroDeNumeros(String cadena){
        int numNumeros = 0;

        for(int i = 0; i < cadena.length(); i++){
            if((cadena.charAt(i) >= 48 && cadena.charAt(i) <= 57)){
                numNumeros++;
            }
        }

        return numNumeros;
    }
}
