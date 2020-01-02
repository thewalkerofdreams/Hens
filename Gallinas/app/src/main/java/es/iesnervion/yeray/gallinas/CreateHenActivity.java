package es.iesnervion.yeray.gallinas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import es.iesnervion.yeray.gallinas.Validations.UserValidations;
import es.iesnervion.yeray.gallinas.ViewModels.CreateHenActivityVM;

public class CreateHenActivity extends AppCompatActivity {

    EditText name, pedigree, weight, height;
    CreateHenActivityVM createHenActivityVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hen);

        name = findViewById(R.id.EditTextNombreCrearGallina);
        pedigree = findViewById(R.id.EditTextPedigriCrearGallina);
        weight = findViewById(R.id.EditTextPesoCrearGallina);
        height = findViewById(R.id.EditTextAlturaCrearGallina);

        createHenActivityVM = ViewModelProviders.of(this).get(CreateHenActivityVM.class);

        createHenActivityVM.setNickUsuario(getIntent().getStringExtra("nombreUsuario"));

        //observers
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(String nick) {
                name.setText(createHenActivityVM.getName().getValue());
            }
        };

        final Observer<String> pedigreeObserver = new Observer<String>() {
            @Override
            public void onChanged(String pedigri) {
                pedigree.setText(createHenActivityVM.getPedigree().getValue());
            }
        };

        final Observer<String> weightObserver = new Observer<String>() {
            @Override
            public void onChanged(String peso) {
                weight.setText(createHenActivityVM.getWeight().getValue());
            }
        };

        final Observer<String> heightObserver = new Observer<String>() {
            @Override
            public void onChanged(String altura) {
                height.setText(createHenActivityVM.getHeight().getValue());
            }
        };

        //Observamos los livedata
        createHenActivityVM.getName().observe(this, nameObserver);
        createHenActivityVM.getPedigree().observe(this, pedigreeObserver);
        createHenActivityVM.getWeight().observe(this, weightObserver);
        createHenActivityVM.getHeight().observe(this, heightObserver);
    }

    /*
     * Intefaz
     * Nombre: tryMakeHen
     * Comentario: Este método nos permite intentar crear una gallina con los datos de la actividad
     * actual. Si alguno de los datos no es correcto, el método informará de ello al usuario. Si se
     * consigue crear una nueva cuenta, el mismo método lanzará la actividad MainActivity.
     * Cabecera: public void tryMakeHen(View v)
     * Entrada:
     *   -View v
     * Postcondiciones: Si la actividad tiene algún dato no válido el método informa de ello y en caso
     * contrario el método crea una nueva gallina en la base de datos y lanza la actividad MainActivity.
     * */
    public void tryMakeHen(View v){
        Intent i;
        createHenActivityVM.setName(name.getText().toString());
        createHenActivityVM.setPedigree(pedigree.getText().toString());
        createHenActivityVM.setWeight(weight.getText().toString());
        createHenActivityVM.setHeight(height.getText().toString());
        if(createHenActivityVM.getName().getValue() != null && !createHenActivityVM.getName().getValue().equals("")){
            if(createHenActivityVM.getPedigree().getValue() != null && !createHenActivityVM.getPedigree().getValue().equals("")){
                if(createHenActivityVM.getWeight().getValue() != null && !createHenActivityVM.getWeight().getValue().equals("") && Double.valueOf(createHenActivityVM.getWeight().getValue()) > 0 &&
                        Double.valueOf(createHenActivityVM.getWeight().getValue()) < 30){
                    if(createHenActivityVM.getHeight().getValue() != null && !createHenActivityVM.getHeight().getValue().equals("") && Double.valueOf(createHenActivityVM.getHeight().getValue()) > 0 &&
                            Double.valueOf(createHenActivityVM.getHeight().getValue()) < 3){
                            createHenActivityVM.insertarNuevaGallina();
                            setResult(RESULT_OK);
                            finish();
                            //i = new Intent(this, HenListActivity.class);
                            //i.putExtra("nombreUsuario", createHenActivityVM.getNickUsuario());
                            //startActivity(i);
                    }else{
                        Toast.makeText(this, "Debes escribir una altura mayor que 0 y menor que 3.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Debes escribir un peso mayor que 0 y menor que 30.", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "El pedigrí no puede estar vacío.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "El nombre no puede estar vacío.", Toast.LENGTH_SHORT).show();
        }
    }
}
