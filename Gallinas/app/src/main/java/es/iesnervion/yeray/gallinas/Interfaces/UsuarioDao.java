package es.iesnervion.yeray.gallinas.Interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.iesnervion.yeray.gallinas.Entities.Usuario;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM usuario")
    List<Usuario> getAllUsers();

    @Query("SELECT * FROM usuario WHERE nick = :nick")
    Usuario getUser(String nick);

    @Insert(onConflict = OnConflictStrategy.REPLACE)//Si surge algún conflicto en la inserción se realizará un reemplazo
    void insertUser(Usuario user);

    @Update
    int updateUser(Usuario user);

    @Query("DELETE FROM usuario WHERE nick = :nick")
    int deleteUser(String nick);
}
