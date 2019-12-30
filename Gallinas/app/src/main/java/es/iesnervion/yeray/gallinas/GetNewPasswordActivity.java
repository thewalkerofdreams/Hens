package es.iesnervion.yeray.gallinas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import es.iesnervion.yeray.gallinas.Validations.UserValidations;
import es.iesnervion.yeray.gallinas.ViewModels.CreateCountActivityVM;
import es.iesnervion.yeray.gallinas.ViewModels.GetNewPasswordActivityVM;

public class GetNewPasswordActivity extends AppCompatActivity {

    GetNewPasswordActivityVM getNewPasswordActivityVM;
    EditText mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail = findViewById(R.id.EditTextMailGetPassword);

        getNewPasswordActivityVM = ViewModelProviders.of(this).get(GetNewPasswordActivityVM.class);

        final Observer<String> mailObserver = new Observer<String>() {
            @Override
            public void onChanged(String nick) {
                mail.setText(getNewPasswordActivityVM.getMail().getValue());
            }
        };

        getNewPasswordActivityVM.getMail().observe(this, mailObserver);
    }

    /*
     * Intefaz
     * Nombre: sendNewPassword
     * Comentario: Este método nos permite pedir una nueva contraseña a la aplicación,
     * la nueva clave se enviará al correo que el usuario haya insertado.
     * Cabecera: public void throwCreateCountActivity(View v)
     * Entrada:
     *   -View v
     * Postcondiciones: El método envía una nueva clave a un correo.
     * */
    public void sendNewPassword(View v){
        UserValidations validations = new UserValidations();
        getNewPasswordActivityVM.setMail(mail.getText().toString());

        if(getNewPasswordActivityVM.getMail().getValue() != null && !getNewPasswordActivityVM.getMail().getValue().equals("")){
            if(validations.correoValido(getNewPasswordActivityVM.getMail().getValue())){
                if(validations.existMail(getNewPasswordActivityVM.getMail().getValue(), this)){
                    sendNewKeyByMail(getNewPasswordActivityVM.getMail().getValue());
                    Toast.makeText(this, "Se ha enviado la nueva clave al correo.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Este correo no pertenece a ninguna cuenta de usuario.", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "El correo no es válido.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Debes introducir un correo.", Toast.LENGTH_SHORT).show();
        }
        Intent i = new Intent(this, CreateCountActivity.class);
        startActivity(i);
    }

    /*
     * Interfaz
     * Nombre: sendNewKeyByMail
     * Comentario: Este método nos permite enviar una nueva clave a
     * un email.
     * Cabecera: public boolean sendNewKeyByMail(String mail)
     * Entrada:
     *   -String mail
     * Salida:
     *   -boolean send
     * Postcondiciones: El método devuelve un valor booleano asociado al nombre,
     * true si se ha conseguido enviar la nueva clave o falso en caso contrario.
     * */
    public boolean sendNewKeyByMail(String mail){
        boolean send = true;
        Log.e("Test email:", "enviando email");
        String[] TO = {mail};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "New key Gallinas app");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Aquí tiene su nueva clave : "+getNewPasswordActivityVM.claveAutogenerada());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.e("Test mail:", "End send mail");

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            send = false;
        }
        return send;
    }
}

