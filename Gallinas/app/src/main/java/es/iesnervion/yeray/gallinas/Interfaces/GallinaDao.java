package es.iesnervion.yeray.gallinas.Interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.iesnervion.yeray.gallinas.Entities.Gallina;

@Dao
public interface GallinaDao {
    @Query("SELECT * FROM gallina")
    List<Gallina> getAllHens();

    @Query("SELECT * FROM gallina WHERE nickUsuario = :nickUser")
    List<Gallina> getAllHens(String nickUser);

    @Query("SELECT * FROM gallina WHERE id = :id")
    Gallina getHen(int id);

    @Query("SELECT * FROM gallina WHERE nombre = :nombre")
    Gallina getHen(String nombre);

    @Insert(onConflict = OnConflictStrategy.REPLACE)//Si surge algún conflicto en la inserción se realizará un reemplazo
    void insertHen(Gallina gallina);

    @Update
    int updateHen(Gallina gallina);

    @Query("DELETE FROM gallina WHERE id = :id")
    int deleteHen(int id);
}
