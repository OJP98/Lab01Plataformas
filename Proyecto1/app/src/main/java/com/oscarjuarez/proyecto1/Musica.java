package com.oscarjuarez.proyecto1;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Oscar Juarez on 22/02/2018.
 */

public class Musica implements Parcelable {

    @ColumnInfo(name = "genero")
    private String genero;

    @ColumnInfo(name = "artista")
    private String artista;

    public Musica(String genero, String artista) {
        this.genero = genero;
        this.artista = artista;
    }


    public String getGenero() {
        return genero;
    }

    public String getartista() {
        return artista;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Genero de la playlist: " + genero + "\nArtista principal: " + artista;
    }

    protected Musica(Parcel in) {
        genero = in.readString();
        artista = in.readString();
    }

    public static final Creator<Musica> CREATOR = new Creator<Musica>() {
        @Override
        public Musica createFromParcel(Parcel in) {
            return new Musica(in);
        }

        @Override
        public Musica[] newArray(int size) {
            return new Musica[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(genero);
        parcel.writeString(artista);
    }
}
