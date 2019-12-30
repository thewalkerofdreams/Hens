package es.iesnervion.yeray.gallinas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import es.iesnervion.yeray.gallinas.DDBB.ManejadorBaseDeDatos;
import es.iesnervion.yeray.gallinas.Entities.Usuario;
import es.iesnervion.yeray.gallinas.Validations.UserValidations;
import es.iesnervion.yeray.gallinas.ViewModels.CreateCountActivityVM;
import es.iesnervion.yeray.gallinas.ViewModels.MainActivityVM;

public class MainActivity extends AppCompatActivity {

    MainActivityVM mainActivityVM;
    EditText nick, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nick = findViewById(R.id.editTextNickName);
        password = findViewById(R.id.editTextPassword);

        mainActivityVM = ViewModelProviders.of(this).get(MainActivityVM.class);

        //observers
        final Observer<String> nickObserver = new Observer<String>() {
            @Override
            public void onChanged(String nickName01) {
                nick.setText(mainActivityVM.getNick().getValue());
            }
        };

        final Observer<String> passwordObserver = new Observer<String>() {
            @Override
            public void onChanged(String password01) {
                password.setText(mainActivityVM.getPassword().getValue());
            }
        };

        //Observamos los livedata
        mainActivityVM.getNick().observe(this, nickObserver);
        mainActivityVM.getPassword().observe(this, passwordObserver);
    }

    /*
     * Intefaz
     * Nombre: throwHenListActivity
     * Comentario: Este método nos permite lanzar la actividad HenListActivity.
     * Cabecera: public void throwHenListActivity(View v)
     * Entrada:
     *   -View v
     * Postcondiciones: El método lanza la actividad HenListActivity.
     * */
    public void throwHenListActivity(View v){
        ManejadorBaseDeDatos manejador = new ManejadorBaseDeDatos(this);
        Usuario usuario;
        Intent i;
        mainActivityVM.setNick(nick.getText().toString());
        mainActivityVM.setPassword(password.getText().toString());
        if(mainActivityVM.getNick().getValue() != null && !mainActivityVM.getNick().getValue().equals("")){
            if(mainActivityVM.getPassword().getValue() != null && !mainActivityVM.getPassword().getValue().equals("")){
                usuario = manejador.getUser(mainActivityVM.getNick().getValue());
                if(usuario != null && usuario.getPassword().equals(mainActivityVM.getPassword().getValue())){
                    Toast.makeText(this, "Perfecto.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "El usuario o contraseña son incorrectos.", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Debes introducir la contraseña.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Debes introducir el nick de usuario.", Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * Intefaz
     * Nombre: throwCreateCountActivity
     * Comentario: Este método nos permite lanzar la actividad CreateCountActivity.
     * Cabecera: public void throwCreateCountActivity(View v)
     * Entrada:
     *   -View v
     * Postcondiciones: El método lanza la actividad CreateCountActivity.
     * */
    public void throwCreateCountActivity(View v){
        Intent i = new Intent(this, CreateCountActivity.class);
        startActivity(i);
    }
}
