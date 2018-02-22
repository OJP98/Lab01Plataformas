package com.oscarjuarez.proyecto1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contacto> listaContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        listaContacto = new ArrayList<>();
        listaContacto.add(new Contacto("Oscar", "Juarez","12345678"));
        listaContacto.add(new Contacto("Javier", "Carpio","87654321"));
        listaContacto.add(new Contacto("Jose", "Cifuentes","45612378"));
        listaContacto.add(new Contacto("Mauricio", "Juarez","123789456"));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView lv = findViewById(R.id.listView_activity1);

        final ArrayAdapter<Contacto> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, listaContacto);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent nuevoIntent = new Intent(MainActivity.this, SecondActivity.class);
                nuevoIntent.putExtra("nombre",arrayAdapter.getItem(position).getNombre());
                nuevoIntent.putExtra("apellido",arrayAdapter.getItem(position).getApellido());
                nuevoIntent.putExtra("numero",arrayAdapter.getItem(position).getNumero());
                startActivityForResult(nuevoIntent, 1);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
