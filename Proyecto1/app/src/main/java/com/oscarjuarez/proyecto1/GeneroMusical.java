package com.oscarjuarez.proyecto1;

import android.arch.persistence.room.ColumnInfo;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Oscar Juarez on 1/03/2018.
 */

public class GeneroMusical implements Parcelable {

    @ColumnInfo(name = "artistas")
    private String artista;

    @ColumnInfo(name = "generos")
    private String genero;

    public GeneroMusical(String artista, String genero) {
        this.artista = artista;
        this.genero = genero;
    }

    protected GeneroMusical(Parcel in) {
        artista = in.readString();
        genero = in.readString();
    }

    @Override
    public String toString() {
        return "El artista favorito es: " + artista + "\nEl genero del artista es: " + genero;
    }

    public static final Creator<GeneroMusical> CREATOR = new Creator<GeneroMusical>() {
        @Override
        public GeneroMusical createFromParcel(Parcel in) {
            return new GeneroMusical(in);
        }

        @Override
        public GeneroMusical[] newArray(int size) {
            return new GeneroMusical[size];
        }
    };

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(artista);
        parcel.writeString(genero);
    }
}
