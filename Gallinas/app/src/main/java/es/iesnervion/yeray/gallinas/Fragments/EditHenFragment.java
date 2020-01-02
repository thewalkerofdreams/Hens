package es.iesnervion.yeray.gallinas.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import es.iesnervion.yeray.gallinas.DDBB.ManejadorBaseDeDatos;
import es.iesnervion.yeray.gallinas.Entities.Gallina;
import es.iesnervion.yeray.gallinas.HenListActivity;
import es.iesnervion.yeray.gallinas.MainActivity;
import es.iesnervion.yeray.gallinas.R;
import es.iesnervion.yeray.gallinas.ViewModels.HenListActivityVM;

public class EditHenFragment extends Fragment {

    EditText name, pedigree, weight, height;
    HenListActivityVM henListActivityVM;
    Button btnUpdate;
    //Constructor por defecto.
    public EditHenFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_edit_hen_fragment, container, false);
        henListActivityVM = ViewModelProviders.of(getActivity()).get(HenListActivityVM.class);

        name = view.findViewById(R.id.EditTextNombreGallina);
        pedigree = view.findViewById(R.id.EditTextPedigriGallina);
        weight = view.findViewById(R.id.EditTextPesoGallina);
        height = view.findViewById(R.id.EditTextAlturaGallina);
        btnUpdate = view.findViewById(R.id.btnModHen);

        if(henListActivityVM.getGallinaSeleccionada().getValue() != null){
            name.setText(henListActivityVM.getGallinaSeleccionada().getValue().getNombre());
            pedigree.setText(henListActivityVM.getGallinaSeleccionada().getValue().getPedigri());
            weight.setText(String.valueOf(henListActivityVM.getGallinaSeleccionada().getValue().getPeso()));
            height.setText(String.valueOf(henListActivityVM.getGallinaSeleccionada().getValue().getAltura()));
        }

        //Los observers
        final Observer<Gallina> contactObserver = new Observer<Gallina>() {
            @Override
            public void onChanged(Gallina gallina) {
                if(henListActivityVM.getGallinaSeleccionada().getValue() != null){
                    name.setText(henListActivityVM.getGallinaSeleccionada().getValue().getNombre());
                    pedigree.setText(henListActivityVM.getGallinaSeleccionada().getValue().getPedigri());
                    weight.setText(String.valueOf(henListActivityVM.getGallinaSeleccionada().getValue().getPeso()));
                    height.setText(String.valueOf(henListActivityVM.getGallinaSeleccionada().getValue().getAltura()));
                }
            }
        };

        //Observamos los LiveData
        henListActivityVM.getGallinaSeleccionada().observe(this, contactObserver);

        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!name.getText().toString().equals("")){
                    if(!pedigree.getText().toString().equals("")){
                        if(!weight.getText().toString().equals("") && Double.valueOf(weight.getText().toString()) > 0 &&
                                Double.valueOf(weight.getText().toString()) < 30){
                            if(!height.getText().toString().equals("") && Double.valueOf(height.getText().toString()) > 0 &&
                                    Double.valueOf(height.getText().toString()) < 3){
                                henListActivityVM.getGallinaSeleccionada().getValue().setNombre(name.getText().toString());//Modificamos la gallina seleccionada
                                henListActivityVM.getGallinaSeleccionada().getValue().setPedigri(pedigree.getText().toString());
                                henListActivityVM.getGallinaSeleccionada().getValue().setPeso(Double.valueOf(weight.getText().toString()));
                                henListActivityVM.getGallinaSeleccionada().getValue().setAltura(Double.valueOf(height.getText().toString()));

                                ManejadorBaseDeDatos manejador = new ManejadorBaseDeDatos(getContext());
                                manejador.updateHen(henListActivityVM.getGallinaSeleccionada().getValue());
                                ((HenListActivity) getActivity()).recargarLista();//Recargamos la lista del mainActivity.
                                Toast.makeText(getContext(), "Se ha modificado la gallina.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getContext(), "La altura debe ser entre 0 y 3.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getContext(), "El peso debe ser entre 0 y 30.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "El pedigrí se sencuentra vacío.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "El nombre se sencuentra vacío.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
