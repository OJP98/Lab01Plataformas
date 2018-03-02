package com.oscarjuarez.proyecto1;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import io.reactivex.annotations.NonNull;

/**
 * Created by Oscar Juarez on 19/02/2018.
 */

@Entity(tableName = "contactos")
public class Contacto implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int uid;

    @ColumnInfo(name = "first_name")
    private String nombre;

    @ColumnInfo(name = "last_name")
    private String apellido;

    @ColumnInfo(name = "user_number")
    private String numero;

    @Embedded
    private GeneroMusical generoMusical;

//    @Embedded
//    private List<Musica> musica;

    @Ignore
    public Contacto(){}

    public Contacto(String nombre, String apellido, String numero, GeneroMusical generoMusical) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero = numero;
        this.generoMusical = generoMusical;
    }

    /**
     * Connstruye el parcel en base al intent que se le envia de parametro.
     *
     * @param in: El intent recibido.
     */
    protected Contacto(Parcel in) {
        this.nombre = in.readString();
        this.apellido = in.readString();
        this.numero = in.readString();
        this.generoMusical = in.readParcelable(getClass().getClassLoader());
//        musica = new ArrayList<Musica>();
//        in.createTypedArrayList((Creator<Object>) musica);

    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        /**
         * Metodo que crea el parcel del objeto
         */
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

    /**
     * Convierte el objeto en un string
     *
     * @return: El string personalizado
     */
    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(apellido);
        parcel.writeString(numero);
        parcel.writeParcelable(generoMusical, i);

//        parcel.writeTypedList(musica);
    }

//    @Override
//    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
//        return null;
//    }
//
//    @Override
//    protected InvalidationTracker createInvalidationTracker() {
//        return null;
//    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusical generoMusical) {
        this.generoMusical = generoMusical;
    }

    //    public List<Musica> getMusica() {
//        return musica;
//    }
//
//    public void setMusica(List<Musica> musica) {
//        this.musica = musica;
//    }
}
