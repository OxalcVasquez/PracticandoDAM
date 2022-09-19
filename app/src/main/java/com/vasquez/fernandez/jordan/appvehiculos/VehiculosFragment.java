package com.vasquez.fernandez.jordan.appvehiculos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class VehiculosFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomMenu;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vehiculos, container, false);

        bottomMenu = view.findViewById(R.id.bottom_menu_vehiculos);

        bottomMenu.setOnNavigationItemSelectedListener(this);

        this.onNavigationItemSelected(bottomMenu.getMenu().getItem(0));

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = new Fragment();
        if (id == R.id.opcion_vehiculo_agregar){
            fragment = new VehiculoAgregarFragment();
        } else if (id == R.id.opcion_vehiculo_listado){
            fragment = new VehiculosListadoFragment();
        }

        FragmentTransaction fragmentTransaction = this.getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contenedor_vehiculos,fragment).commit();
        return true;
    }
}