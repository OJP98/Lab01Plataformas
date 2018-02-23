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
     * Devuelve el nombre del contacto.
     * @return: el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el apellido del contacto.
     * @return: el apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Debuelve el numero del contacto
     * @return: El numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Retorna las lista de amigos
     * @return: lista tipo string de amigos
     */
    public List<String> getAmigos() { return amigos; }

    /**
     * Convierte el objeto en un string
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

    /**
     * Empaqueta la informacion del contacto como parcel
     * @param parcel: el parcel a evaluar
     * @param i: la posicion.
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(apellido);
        parcel.writeString(numero);
        parcel.writeStringList(amigos);
    }
}
