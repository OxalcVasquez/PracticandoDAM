package com.vasquez.fernandez.jordan.appvehiculos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vasquez.fernandez.jordan.appvehiculos.adapter.VehiculoAdapter;
import com.vasquez.fernandez.jordan.appvehiculos.logica.Vehiculo;
import com.vasquez.fernandez.jordan.appvehiculos.utils.Helper;


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

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Helper.mensajeConfirmacion(getActivity(),"Desea eliminar el vehiculo?","OK","CANCELAR",new EliminarVehiculoTask());
                break;
            case 2:
                break;
        }

        return super.onContextItemSelected(item);
    }

    class EliminarVehiculoTask implements Runnable{

        @Override
        public void run() {
            eliminar();
        }
    }

    private void eliminar(){
        int pos = adapter.posVehiculoSeleccionadoRv;
        String numeroChasis = Vehiculo.listaVehiculos.get(pos).getNumeroChasis();
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setNumeroChasis(numeroChasis);
        try {
            if (vehiculo.eliminar()>0){
                listar();
                Helper.mensajeInformacion(getContext(),"Vehiculo","Eliminado correctamente");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}