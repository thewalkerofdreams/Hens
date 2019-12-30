package es.iesnervion.yeray.gallinas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import es.iesnervion.yeray.gallinas.Utilities.FuncionesCadenas;
import es.iesnervion.yeray.gallinas.Validations.UserValidations;
import es.iesnervion.yeray.gallinas.ViewModels.CreateCountActivityVM;

public class CreateCountActivity extends AppCompatActivity {

    EditText nickName, password01, password02, mail, firstName, lastName;
    CreateCountActivityVM createCountActivityVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_count);

        nickName = findViewById(R.id.EditTextNickCreateCount);
        password01 = findViewById(R.id.EditTextPasswordCreateCount01);
        password02 = findViewById(R.id.EditTextPasswordCreateCount02);
        mail = findViewById(R.id.EditTextMail);
        firstName = findViewById(R.id.EditTextUserFirstName);
        lastName = findViewById(R.id.EditTextUserLastName);

        createCountActivityVM = ViewModelProviders.of(this).get(CreateCountActivityVM.class);

        //observers
        final Observer<String> nickObserver = new Observer<String>() {
            @Override
            public void onChanged(String nick) {
                nickName.setText(createCountActivityVM.getNickName().getValue());
            }
        };

        final Observer<String> password01Observer = new Observer<String>() {
            @Override
            public void onChanged(String nick) {
                password01.setText(createCountActivityVM.getPassword01().getValue());
            }
        };

        final Observer<String> password02Observer = new Observer<String>() {
            @Override
            public void onChanged(String nick) {
                password02.setText(createCountActivityVM.getPassword02().getValue());
            }
        };

        final Observer<String> mailObserver = new Observer<String>() {
            @Override
            public void onChanged(String nick) {
                mail.setText(createCountActivityVM.getMail().getValue());
            }
        };

        final Observer<String> firstNameObserver = new Observer<String>() {
            @Override
            public void onChanged(String nick) {
                firstName.setText(createCountActivityVM.getFirstName().getValue());
            }
        };

        final Observer<String> lastNameObserver = new Observer<String>() {
            @Override
            public void onChanged(String nick) {
                lastName.setText(createCountActivityVM.getLastName().getValue());
            }
        };

        //Observamos los livedata
        createCountActivityVM.getNickName().observe(this, nickObserver);
        createCountActivityVM.getPassword01().observe(this, password01Observer);
        createCountActivityVM.getPassword02().observe(this, password02Observer);
        createCountActivityVM.getMail().observe(this, mailObserver);
        createCountActivityVM.getFirstName().observe(this, firstNameObserver);
        createCountActivityVM.getLastName().observe(this, lastNameObserver);
    }

    /*
     * Intefaz
     * Nombre: tryMakeCount
     * Comentario: Este método nos permite intentar crear una cuenta con los datos de la actividad
     * actual. Si alguno de los datos no es correcto, el método informará de ello al usuario. Si se
     * consigue crear una nueva cuenta, el mismo método lanzará la actividad MainActivity.
     * Cabecera: public void tryMakeCount(View v)
     * Entrada:
     *   -View v
     * Postcondiciones: Si la actividad tiene algún dato no válido el método informa de ello y en caso
     * contrario el método crea una nueva cuenta en la base de datos y lanza la actividad MainActivity.
     * */
    public void tryMakeCount(View v){
        UserValidations validations = new UserValidations();
        String uno = password01.getText().toString();
        String dos = password02.getText().toString();
        createCountActivityVM.setMail(mail.getText().toString());
        createCountActivityVM.setNickName(nickName.getText().toString());
        createCountActivityVM.setPassword01(password01.getText().toString());
        createCountActivityVM.setPassword02(password02.getText().toString());
        createCountActivityVM.setFirstName(firstName.getText().toString());
        createCountActivityVM.setLastName(lastName.getText().toString());
        if(createCountActivityVM.getMail().getValue() != null && validations.correoValido(createCountActivityVM.getMail().getValue())) {//Si el correo es válido
            if(!validations.existMail(createCountActivityVM.getMail().getValue(), this)) {
                if(createCountActivityVM.getNickName().getValue() != null && createCountActivityVM.getNickName().getValue().length() > 0 &&
                        FuncionesCadenas.numeroEspaciosEnBlanco(createCountActivityVM.getNickName().getValue()) == 0){//Si el nick no se encuentra vacío
                    if(!validations.existNickName(createCountActivityVM.getNickName().getValue(), this)){//Sino aún no esta registrado ese nick en la aplicación
                        if(createCountActivityVM.getPassword01().getValue() != null && validations.correctPassword(createCountActivityVM.getPassword01().getValue())){
                            if(createCountActivityVM.getPassword01().getValue().equals(createCountActivityVM.getPassword02().getValue())){
                                if(createCountActivityVM.getFirstName().getValue() != null && !createCountActivityVM.getFirstName().getValue().equals("")){
                                    if(createCountActivityVM.getLastName().getValue() != null && !createCountActivityVM.getLastName().getValue().equals("")){
                                        createCountActivityVM.insertarNuevaCuenta();
                                        Intent i = new Intent(this, MainActivity.class);
                                        startActivity(i);
                                    }else{
                                        Toast.makeText(this, "Los apellidos no deben estar vacío.", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(this, "El nombre no debe estar vacío.", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(this, "Las contraseñas deben ser iguales.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(this, "La contraseña debe contener como mínimo un carácter en minúscula, uno en mayúscula, un dígito y un carácter especial. El tamaño debe ser entre 5 y 20 caracteres.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "El nick "+createCountActivityVM.getNickName().getValue()+" ya existe en la aplicación.",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "El nick no puede estar vacío.", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Ya se ha registrado este correo en la aplicación", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "El correo es inválido.", Toast.LENGTH_SHORT).show();
        }
    }

}
