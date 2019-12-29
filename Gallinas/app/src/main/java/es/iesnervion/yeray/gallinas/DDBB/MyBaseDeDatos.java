package es.iesnervion.yeray.gallinas.DDBB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import es.iesnervion.yeray.gallinas.Entities.Gallina;
import es.iesnervion.yeray.gallinas.Interfaces.GallinaDao;
import es.iesnervion.yeray.gallinas.Entities.Usuario;
import es.iesnervion.yeray.gallinas.Interfaces.UsuarioDao;

@Database(entities = {Gallina.class, Usuario.class}, version = 1)
public abstract class MyBaseDeDatos extends RoomDatabase {
    public abstract GallinaDao getGallinaDao();
    public abstract UsuarioDao getUsuarioDao();
}
