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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicStampedReference;

public class MainActivity extends AppCompatActivity {

    //Se definen las listas con las que se estara manejando el programa
    private ArrayList<Contacto> listaContacto;
    private ArrayList<String> amigos;

    /**
     * Ejecuta una serie de instrucciones al crear el programa
     * @param savedInstanceState: El estado instanciado por el view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Crea una arraylist tipo amigos y agrega a 3 personas
        amigos = new ArrayList<>();
        amigos.add("Juan");
        amigos.add("Andres");
        amigos.add("Mario");

        //Crea 4 objetos tipo contacto, junto con la instancia de la lista. Agrega dichos objetos a la lista
        listaContacto = new ArrayList<>();
        listaContacto.add(new Contacto("Oscar", "Juarez","12345678", amigos));
        listaContacto.add(new Contacto("Javier", "Carpio","87654321", amigos));
        listaContacto.add(new Contacto("Jose", "Cifuentes","45612378", amigos));
        listaContacto.add(new Contacto("Mauricio", "Juarez","123789456", amigos));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Se le asigna un ListView al objeto
        final ListView lv = findViewById(R.id.listView_activity1);

        //Se imprime la lista tipo contacto en el listView
        final ArrayAdapter<Contacto> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, listaContacto);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Metodo que permite  ejecutar diferentes funciones conforme a la posicion en la que
             * haga click el usuario sobre la lista.
             * @param parent: El padre del view
             * @param view: El view a usar
             * @param position: La posicion que apreta el usario
             * @param id: El id del objeto
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Se crea un nuevo intent y se envian las variables conforme a la posicion de la lista
                Intent nuevoIntent = new Intent(MainActivity.this, ExtraActivity.class);
                nuevoIntent.putExtra("nombre",arrayAdapter.getItem(position).getNombre());
                nuevoIntent.putExtra("apellido",arrayAdapter.getItem(position).getApellido());
                nuevoIntent.putExtra("numero",arrayAdapter.getItem(position).getNumero());
                nuevoIntent.putExtra("amigos",amigos);

                //Se envian dichas variables.
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
