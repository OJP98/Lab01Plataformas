package com.oscarjuarez.proyecto1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExtraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        //Se declaran las variables que se enviaron en el activity previo con la ayuda del intent
        String getNombre = getIntent().getStringExtra("nombre");
        String getApellido = getIntent().getStringExtra("apellido");
        String getNumero = getIntent().getStringExtra("numero");
        ArrayList<String> getAmigos = getIntent().getStringArrayListExtra("amigos");

        //Se decalaran objetos tipo view, se les asigna un ID especifico y luego se imprime
        //los datos que se pasaron de la activity principal.
        TextView viewNombre = findViewById(R.id.nombre_extra);
        viewNombre.setText("El nombre del contacto es: " + getNombre);

        TextView viewApellido = findViewById(R.id.apellido_extra);
        viewApellido.setText("El apellido es: " + getApellido);

        TextView viewTelefono = findViewById(R.id.numero_extra);
        viewTelefono.setText("El numero es: " + getNumero);

        //Finalmente, se imprime el nombre de los 'amigos' de los contactos con la ayuda de
        //un arrayadapter
        ListView viewAmigos = findViewById(R.id.listview_extra);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, getAmigos);

        viewAmigos.setAdapter(arrayAdapter);
    }
}
