package es.iesnervion.yeray.gallinas.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuario", primaryKeys = {"nick", "correo"})//Indicamos el nombre que tendrá la tabla, en este caso lo podríamos quitar
public class Usuario {
    @NonNull
    private String nick;
    @NonNull
    private String correo;
    @NonNull
    private String nombre;
    @NonNull
    private String apellidos;
    @NonNull
    private String password;

    public Usuario(){
    }

    @Ignore
    public Usuario(String nick, String password, String correo, String nombre, String apellidos){
        this.nick = nick;
        this.password = password;
        this.correo = correo;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
