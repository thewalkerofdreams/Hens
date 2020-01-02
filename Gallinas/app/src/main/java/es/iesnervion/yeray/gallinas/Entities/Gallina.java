package es.iesnervion.yeray.gallinas.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "gallina")
public class Gallina {
    @PrimaryKey(autoGenerate = true)//Hacemos que la id sea autogenerada
    private int id;
    @ColumnInfo(name = "pedigri")//Con esto indicamos el nombre de la columna, en este caso podriamos quitar este argumento
    private String pedigri;
    private String nombre;
    private double peso;
    private double altura;
    private String nickUsuario;

    public Gallina() {
    }

    @Ignore
    public Gallina(int id, String nombre, String pedigri, double peso, double altura, String nickUsuario){
        this.id = id;
        this.nombre = nombre;
        this.pedigri = pedigri;
        this.peso = peso;
        this.altura = altura;
        this.nickUsuario = nickUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPedigri() {
        return pedigri;
    }

    public void setPedigri(String pedigri) {
        this.pedigri = pedigri;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickUsuario() {
        return nickUsuario;
    }

    public void setNickUsuario(String nickUsuario) {
        this.nickUsuario = nickUsuario;
    }
}
