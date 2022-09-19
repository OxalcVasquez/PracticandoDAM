package com.vasquez.fernandez.jordan.appvehiculos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vasquez.fernandez.jordan.appvehiculos.adapter.VehiculoAdapter;
import com.vasquez.fernandez.jordan.appvehiculos.logica.Vehiculo;


public class VehiculosListadoFragment extends Fragment {

    RecyclerView rvVehiculos;
    VehiculoAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vehiculos_listado, container, false);

        rvVehiculos = view.findViewById(R.id.rv_vehiculos);
        rvVehiculos.setHasFixedSize(true);
        rvVehiculos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new VehiculoAdapter(this.getContext());
        rvVehiculos.setAdapter(adapter);
        listar();
        return view;
    }

    private void listar(){
        new Vehiculo().cargarDatos();
        adapter.cargarDatosVehiculos(Vehiculo.listaVehiculos);
    }
}