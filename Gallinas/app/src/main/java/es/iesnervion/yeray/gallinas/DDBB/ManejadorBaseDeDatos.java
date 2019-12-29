package es.iesnervion.yeray.gallinas.DDBB;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import es.iesnervion.yeray.gallinas.Entities.Gallina;
import es.iesnervion.yeray.gallinas.Interfaces.GallinaDao;
import es.iesnervion.yeray.gallinas.Entities.Usuario;
import es.iesnervion.yeray.gallinas.Interfaces.UsuarioDao;

public class ManejadorBaseDeDatos {

    private GallinaDao gallinaDao;
    private UsuarioDao usuarioDao;

    public ManejadorBaseDeDatos(Context context) {
        Context appContext = context.getApplicationContext();
        MyBaseDeDatos database = Room.databaseBuilder(appContext, MyBaseDeDatos.class, "ConcursoGallinas_bd")
                .allowMainThreadQueries().build();
        gallinaDao = database.getGallinaDao();
        usuarioDao = database.getUsuarioDao();
    }

    //Interface GallinaDao
    public List<Gallina> getAllHens() {
        return gallinaDao.getAllHens();
    }

    public Gallina getHen(int id) {
        return gallinaDao.getHen(id);
    }

    public Gallina getHen(String nombre) {
        return gallinaDao.getHen(nombre);
    }

    public void insertHen(Gallina hen) {
        gallinaDao.insertHen(hen);
    }

    public int updateHen(Gallina gallina) {
        return gallinaDao.updateHen(gallina);
    }

    public int deleteHen(int id) {
        return gallinaDao.deleteHen(id);
    }

    //Interface UsuarioDao
    public List<Usuario> getAllUsers(){
        return usuarioDao.getAllUsers();
    }

    public Usuario getUser(String nick){
        return usuarioDao.getUser(nick);
    }

    public void insertUser(Usuario user){
        usuarioDao.insertUser(user);
    }

    public int updateUser(Usuario user){
        return usuarioDao.updateUser(user);
    }

    public int deleteUser(String nick){
        return usuarioDao.deleteUser(nick);
    }
}
