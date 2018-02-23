package com.oscarjuarez.proyecto1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Juarez on 19/02/2018.
 */

public class Contacto implements Parcelable{
    private String nombre, apellido, numero;
    private List<String> amigos;

    /**
     * El constructor del objeto tipo contacto.
     * @param nombre: El nombre del contacto
     * @param apellido: El apellido del contacto.
     * @param numero: El numero del contacto.
     * @param amigos: La lista de amigos del usuario.
     */
    public Contacto(String nombre, String apellido, String numero, List<String> amigos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero = numero;
        this.amigos = amigos;
    }

    /**
     * Connstruye el parcel en base al intent que se le envia de parametro.
     * @param in: El intent recibido.
     */
    protected Contacto(Parcel in){
        this.nombre = in.readString();
        this.apellido = in.readString();
        this.numero = in.readString();
        amigos = new ArrayList<>();
        in.readStringList(amigos);

    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNumero() {
        return numero;
    }

    public List<String> getAmigos() { return amigos; }

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
        parcel.writeStringList(amigos);
    }
}
