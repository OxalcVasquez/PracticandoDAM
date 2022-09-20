package com.vasquez.fernandez.jordan.appvehiculos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.vasquez.fernandez.jordan.appvehiculos.datos.Conexion;
import com.vasquez.fernandez.jordan.appvehiculos.logica.Marca;
import com.vasquez.fernandez.jordan.appvehiculos.logica.Modelo;
import com.vasquez.fernandez.jordan.appvehiculos.logica.Tipo;
import com.vasquez.fernandez.jordan.appvehiculos.logica.Vehiculo;
import com.vasquez.fernandez.jordan.appvehiculos.utils.Gallery;
import com.vasquez.fernandez.jordan.appvehiculos.utils.Helper;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


public class VehiculoAgregarFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    TextInputEditText txtNumeroChasis,txtNumeroMotor,txtPrecio,txtDescuento,txtColor;
    MaterialButton btnRegistrar,btnLimpiar;
    CircleImageView imgVehiculo;
    AutoCompleteTextView actvModelo,actvTipo,actvMarca;
    int posModeloSeleccionado;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vehiculo_agregar, container, false);

        Conexion.contextApp = getContext();

        txtNumeroChasis = view.findViewById(R.id.txt_carro_chasis);
        txtNumeroMotor = view.findViewById(R.id.txt_carro_numero_motor);
        txtPrecio = view.findViewById(R.id.txt_carro_precio);
        txtDescuento = view.findViewById(R.id.txt_carro_descuento);
        txtColor = view.findViewById(R.id.txt_carro_color);

        btnRegistrar = view.findViewById(R.id.btn_carro_registrar);
        btnLimpiar = view.findViewById(R.id.btn_limpiar);

        imgVehiculo = view.findViewById(R.id.img_auto);
        imgVehiculo.setTag("foto_fake");

        actvMarca = view.findViewById(R.id.actv_carro_marca);
        actvModelo = view.findViewById(R.id.actv_carro_modelo);
        actvTipo = view.findViewById(R.id.actv_carro_tipo);

        actvModelo.setOnItemClickListener(this);
        btnRegistrar.setOnClickListener(this);

        imgVehiculo.setOnClickListener(this);

        this.cargarMarca();
        this.cargarTipo();
        this.cargarModelo();

        return view;
    }

    private void cargarMarca(){
        String nombreMarcas[] = new Marca().obtenerNombresMarca();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1,nombreMarcas);
        actvMarca.setAdapter(adapter);
    }
    private void cargarTipo(){
        String nombresTipos[] = new Tipo().obtenerNombresTipos();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1,nombresTipos);
        actvTipo.setAdapter(adapter);
    }
    private void cargarModelo(){
        String nombresModelos[] = new Modelo().obtenerNombresModelos();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1,nombresModelos);
        actvModelo.setAdapter(adapter);
    }

    private void agregar(){
        Vehiculo vehiculo = new Vehiculo();
        if (vehiculo.verificarNumeroChasis(txtNumeroChasis.getText().toString())){
            vehiculo.setNumeroChasis(txtNumeroChasis.getText().toString());
            vehiculo.setNumeroMotor(txtNumeroMotor.getText().toString());
            vehiculo.setFoto(null);
            vehiculo.setMontoDescuento(Double.parseDouble(txtDescuento.getText().toString()));
            vehiculo.setPrecio(Double.parseDouble(txtPrecio.getText().toString()));
            vehiculo.setColor(txtColor.getText().toString());
            vehiculo.setModeloId(posModeloSeleccionado);
            vehiculo.setTipoId(new Tipo().obtenerIdPorNombre(actvTipo.getText().toString()));
            vehiculo.setMarcaId(new Marca().obtenerIdPorNombre(actvMarca.getText().toString()));
            if (imgVehiculo.getTag().equals("foto_real")){
                Log.d("IMAGEN","SI LLEGO");
                vehiculo.setFoto(
                                Helper.imageToBase64(
                                        ((BitmapDrawable) imgVehiculo.getDrawable()).getBitmap()
                                )
                );
            } else {
                vehiculo.setFoto(null);
            }

            if (vehiculo.agregar() > 0) {
                Helper.mensajeInformacion(getContext(),"Vehiculo","Registrado correctamente");
            }


        } else {
            Toast.makeText(this.getContext(),"EL NÚMERO DE CHASIS YA SE ENCUENTRA REGISTRADO",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_carro_registrar){
          agregar();
        } else if (view.getId() == R.id.img_auto){
            abrirGaleria();
        }
    }

    public static final int REQUEST_PICK = 1;

    private void abrirGaleria() {
        startActivityForResult(new Intent(Intent.ACTION_PICK).setType("image/*"),REQUEST_PICK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK){
            if (resultCode == Activity.RESULT_OK){
                try {
                    Uri rutaImage = data.getData();
                    Bitmap bitmap = Gallery.rotateImage(this.getActivity(),rutaImage,Gallery.getOrientation(this.getActivity(),rutaImage));
                    Bitmap bitmapCompress = Gallery.compress(bitmap);
                    imgVehiculo.setImageBitmap(bitmapCompress);
                    imgVehiculo.setTag("foto_real");
                } catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        posModeloSeleccionado = i ;
    }
}