package com.oscarjuarez.proyecto1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicStampedReference;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    //Se definen las listas con las que se estara manejando el programa

    private List<Contacto> listaContacto = new ArrayList<>();
    private ArrayList<Musica> musica;
    private ArrayAdapter<Contacto> arrayAdapter;
    private ListView lv;
    private FloatingActionButton fab;

    //Base de datos
    private CompositeDisposable compositeDisposable;
    private ContactRepository contactRepository;

    /**
     * Ejecuta una serie de instrucciones al crear el programa
     * @param savedInstanceState: El estado instanciado por el view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Inicializa la base de datos
        compositeDisposable = new CompositeDisposable();

        //Crea una arraylist tipo musica y agrega a 4 elementos
        musica = new ArrayList<>();

        musica.add(new Musica("Electro","Deadmau5"));
        musica.add(new Musica("Clasica","Bethoven"));
        musica.add(new Musica("Rock","Guns & Roses"));
        musica.add(new Musica("Pop","Lady Gaga"));

        //Se le asigna un ListView al objeto
        lv = findViewById(R.id.listView_activity1);
        fab = findViewById(R.id.fab);

        //Se imprime la lista tipo contacto en el listView
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaContacto);
        registerForContextMenu(lv);
        lv.setAdapter(arrayAdapter);

        //Base de datos
        AppDatabase appDatabase = AppDatabase.getInstance(this);
        contactRepository = ContactRepository.getInstance(ContactDataSource.getInstance(appDatabase.contactDao()));

        loadData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ingresarDatos();
                fab.hide();

            }
        });

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
                nuevoIntent.putExtra("musica", musica);

                //Se envian dichas variables.
                startActivityForResult(nuevoIntent, 1);

            }
        });

    }

    private void loadData() {

        Disposable disposable = contactRepository.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Contacto>>() {

            @Override
            public void accept(List<Contacto> contactoList) throws Exception {

                onGetAllContacts(contactoList);

            }

            }, new Consumer<Throwable>() {
                @Override
                public void accept (Throwable throwable) throws Exception {
                    Toast.makeText(MainActivity.this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
        });

        compositeDisposable.add(disposable);

    }

    private void onGetAllContacts(List<Contacto> contactoList) {

        listaContacto.clear();
        listaContacto.addAll(contactoList);
        arrayAdapter.notifyDataSetChanged();

    }

    private void ingresarDatos(){

        Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {

                //Crea 4 objetos tipo contacto, junto con la instancia de la lista. Agrega dichos objetos a la lista
                Contacto Oscar =  new Contacto("Oscar", "Juarez","12345678");
                Contacto Javier = new Contacto ("Javier", "Carpio","87654321");
                Contacto Jose = new Contacto("Jose", "Cifuentes","45612378");
                Contacto Mauricio = new Contacto("Mauricio", "Juarez","123789456");

                listaContacto.add(Oscar);
                listaContacto.add(Javier);
                listaContacto.add(Jose);
                listaContacto.add(Mauricio);
                contactRepository.insertContact(Oscar);
                contactRepository.insertContact(Javier);
                contactRepository.insertContact(Jose);
                contactRepository.insertContact(Mauricio);

                e.onComplete();

            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer() {

                    @Override
                    public void accept(Object o) throws Exception {
                        Toast.makeText(MainActivity.this, "Contacto(s) Agregado!", Toast.LENGTH_SHORT).show();
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        loadData();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.clear_menu:
                deleteAllContacts();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllContacts() {

        Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                contactRepository.deleteAllContacts();
                e.onComplete();

            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer() {

                    @Override
                    public void accept(Object o) throws Exception {
                        Toast.makeText(MainActivity.this, "Contacto(s) Eliminados!", Toast.LENGTH_SHORT).show();
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        loadData();
                    }
                });


    }

}
