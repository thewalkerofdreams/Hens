package es.iesnervion.yeray.gallinas.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import es.iesnervion.yeray.gallinas.R;
import es.iesnervion.yeray.gallinas.ViewModels.HenListActivityVM;

public class EditHenFragment extends Fragment {

    HenListActivityVM henListActivityVM;
    //Constructor por defecto.
    public EditHenFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        henListActivityVM = ViewModelProviders.of(getActivity()).get(HenListActivityVM.class);

        View view = inflater.inflate(R.layout.activity_edit_hen_fragment, container, false);

        return view;
    }
}
