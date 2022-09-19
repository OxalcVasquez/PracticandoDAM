package com.vasquez.fernandez.jordan.appvehiculos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vasquez.fernandez.jordan.appvehiculos.R;
import com.vasquez.fernandez.jordan.appvehiculos.logica.Vehiculo;
import com.vasquez.fernandez.jordan.appvehiculos.utils.Helper;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Vehiculo> listaVehiculosAux;

    public VehiculoAdapter(Context context) {
        this.context = context;
        this.listaVehiculosAux = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_vehiculo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Vehiculo vehiculo = listaVehiculosAux.get(position);
        holder.txtChasis.setText(vehiculo.getNumeroChasis());
        holder.txtMotor.setText(vehiculo.getNumeroMotor());
        holder.txtMarcaTipoModelo.setText(vehiculo.getMarca() + "-" + vehiculo.getTipo() + "-" + vehiculo.getModelo());
        holder.txtPrecioDescuento.setText("Precio : " + vehiculo.getPrecio() + " - Descuento : " + vehiculo.getMontoDescuento());

        if (vehiculo.getFoto()==null){
            holder.imgVehiculo.setImageResource(R.drawable.fondo_carro);
        } else {
            //Mostrar la foto real grabada en la base de datos
            holder.imgVehiculo.setImageBitmap(Helper.base64ToImage(vehiculo.getFoto()));
        }
    }

    @Override
    public int getItemCount() {
        return listaVehiculosAux.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Declarar los controles del cardview
        ImageView imgVehiculo;
        TextView txtMotor,txtChasis,txtColor,txtMarcaTipoModelo,txtPrecioDescuento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgVehiculo = itemView.findViewById(R.id.img_auto);
            txtMotor = itemView.findViewById(R.id.txt_carro_numero_motor);
            txtChasis = itemView.findViewById(R.id.txt_carro_chasis);
            txtMarcaTipoModelo = itemView.findViewById(R.id.txt_carro_marca_modelo);
            txtColor = itemView.findViewById(R.id.txt_carro_color);
            txtPrecioDescuento = itemView.findViewById(R.id.txt_carro_precio_descuento);

        }
    }

    public void cargarDatosVehiculos(ArrayList<Vehiculo> vehiculos){
        listaVehiculosAux = vehiculos;
        notifyDataSetChanged();
    }

}
